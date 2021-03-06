package com.example.mybatis;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import org.springframework.util.StringUtils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class InsertInCondition {


    public static void main(String[] args) throws Exception {

        String sql =
                "SELECT ko.*, " +
                        "       NULL        AS            academyId, " +
                        "       NULL        AS            branchSchoolId, " +
                        "       NULL        AS            academyName, " +
                        "       NULL        AS            branchSchoolName, " +
                        "       leaderuser.name           leaderName, " +
                        "       leaderuser.job_phone      leaderPhone, " +
                        "       koadmin.name              adminName, " +
                        "       koadminaccount.login_name adminAccount, " +
                        "       kor.role_id, " +
                        "       p1.p_org_id AS            parent_org_id, " +
                        "       p2.p_org_id AS            parent_org_id2, " +
                        "       p3.p_org_id AS            parent_org_id3 " +
                        "FROM kf_organization ko " +
                        "         LEFT JOIN kf_org_relationship p1 ON ko.id = p1.org_id AND p1.RENT_ID = 449 " +
                        "         LEFT JOIN kf_org_relationship p2 ON p1.p_org_id = p2.org_id AND p2.RENT_ID = 449 " +
                        "         LEFT JOIN kf_org_relationship p3 ON p2.p_org_id = p3.org_id AND p3.RENT_ID = 449 " +
                        "         LEFT JOIN kf_user koadmin ON ko.admin_id = koadmin.id AND koadmin.RENT_ID = 449 " +
                        "         LEFT JOIN kf_account koadminaccount ON koadmin.id = koadminaccount.user_id AND koadminaccount.RENT_ID = 449 " +
                        "         LEFT JOIN kf_user leaderuser ON ko.leader_id = leaderuser.id AND leaderuser.RENT_ID = 449 " +
                        "         LEFT JOIN kf_organization orgp1 ON p1.p_org_id = orgp1.id AND orgp1.RENT_ID = 449 " +
                        "         LEFT JOIN kf_organization orgp2 ON p2.p_org_id = orgp2.id AND orgp2.RENT_ID = 449 " +
                        "         LEFT JOIN kf_organization orgp3 ON p3.p_org_id = orgp3.id AND orgp3.RENT_ID = 449 " +
                        "         LEFT JOIN kf_org_role kor ON ko.id = kor.org_id AND kor.RENT_ID = 449 " +
                        "WHERE ko.id  in(1,2,100) " +
                        "  AND ko.RENT_ID = 449";
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        StringReader stringReader = new StringReader(sql);
        Select select = (Select) parserManager.parse(stringReader);


        SelectBody selectBody = select.getSelectBody();

//        String tableName = "kf_org_relationship";
        String tableName = "kf_organization";
//        if (StringUtils.isEmpty(tableName) && selectBody instanceof PlainSelect) {
//            tableName = getTableName(selectBody);
//
        processSelectBody(selectBody, tableName);
        log.info(selectBody.toString());
    }

    public static void processSelectBody(SelectBody selectBody, String tableName) throws Exception {
        if (selectBody instanceof PlainSelect) {
            parsPlainSelect(selectBody, tableName);
        } else if (selectBody instanceof WithItem) {
            WithItem withItem = (WithItem) selectBody;
            if (withItem.getSelectBody() != null) {
                processSelectBody(withItem.getSelectBody(), tableName);
            }
        } else {
            SetOperationList operationList = (SetOperationList) selectBody;
            List<SelectBody> selects = operationList.getSelects();
            for (SelectBody select : selects) {
                processSelectBody(select, tableName);
            }
        }
    }

    /**
     * ??????select??????
     *
     * @param selectBody
     * @param tableName
     * @throws JSQLParserException
     */
    public static void parsPlainSelect(SelectBody selectBody, String tableName) throws Exception {
        PlainSelect plain = (PlainSelect) selectBody;

        FromItem fromItem = plain.getFromItem();
        if (isMatchTableName(fromItem, tableName)) {
            String tableAlias = getTableAlias(fromItem);
            buildSubQuery(plain, tableAlias);
        }

        List<Join> joinList = plain.getJoins();
        if (CollectionUtils.isNotEmpty(joinList)) {
            for (Join join : joinList) {
                FromItem rightItem = join.getRightItem();
                if (isMatchTableName(rightItem, tableName)) {
                    buildJoinQueryWithInExpression(join, getTableAlias(rightItem));
                }
            }
        }
    }

    /**
     * @param fromItem  ??????
     * @param tableName ??????
     * @return ????????????????????????fromItem????????????tableName
     * @throws JSQLParserException
     */
    public static boolean isMatchTableName(FromItem fromItem, String tableName) throws Exception {
        String fromItemName = "";

        if (fromItem instanceof Table) {
            fromItemName = ((Table) fromItem).getName();
        }

        if (fromItem instanceof SubSelect) {
            SelectBody subSelectBody = ((SubSelect) fromItem).getSelectBody();
            // ???????????????
            processSelectBody(subSelectBody, tableName);
        }

        if (fromItemName.equals(tableName)) {
            log.info("fromItemName -- " + fromItem);
            return true;
        }
        return false;
    }

    /**
     * @param selectBody
     * @return ??????/?????????
     */
    public static String getTableName(SelectBody selectBody) {
        PlainSelect pain = (PlainSelect) selectBody;
        FromItem fromItem = pain.getFromItem();
        String fromItemName = "";
        if (fromItem instanceof Table) {
            fromItemName = ((Table) fromItem).getName();
        }
        return fromItem.getAlias() == null ? fromItemName : fromItem.getAlias().getName();
    }

    /**
     * ???where??????
     *
     * @param plain
     * @param tableName
     * @throws JSQLParserException
     */
    public static void buildSubQuery(PlainSelect plain, String tableName) throws JSQLParserException {
        // todo  ??????????????????????????????
        String fieldName = "id";
        InExpression inExpression = new InExpression();
        inExpression.setLeftExpression(new Column(tableName + "." + fieldName));
        List<Expression> expressionList = new ArrayList<Expression>();

        // todo  ????????????????????????????????????
        List<String> orgList = new LinkedList<>();
        orgList.add("apple");
        orgList.add("panda");
        orgList.add("orange");
        for (String item : orgList) {
            expressionList.add(CCJSqlParserUtil.parseCondExpression(String.format("%s%s%s", "'", item, "'")));
        }
        inExpression.setRightItemsList(new ExpressionList(expressionList));

        Expression plainWhere = plain.getWhere();
        if (plainWhere == null) {
            plain.setWhere(inExpression);
        } else {
            plain.setWhere(new AndExpression(plainWhere, inExpression));
        }

    }

    /**
     * ??????join??????
     *
     * @param join
     * @param tableName
     * @throws JSQLParserException
     */
    public static void buildJoinQueryWithInExpression(Join join, String tableName) throws Exception {
        // todo  ??????????????????????????????
        String fieldName = "id";
        InExpression inExpression = new InExpression();
        inExpression.setLeftExpression(new Column(tableName + "." + fieldName));
        List<Expression> expressionList = new ArrayList<Expression>();

        // todo  ?????????????????????????????????
        List<String> orgList = new LinkedList<>();
        orgList.add("apple");
        orgList.add("orange");
        for (String item : orgList) {
            expressionList.add(CCJSqlParserUtil.parseCondExpression(String.format("%s%s%s", "'", item, "'")));
        }

        inExpression.setRightItemsList(new ExpressionList(expressionList));
        join.setOnExpression(new AndExpression(join.getOnExpression(), inExpression));
    }


    /**
     * @param fromItem
     * @return ????????????????????????????????????????????????
     */
    static String getTableAlias(FromItem fromItem) {
        String fromItemName = "";
        if (fromItem instanceof Table) {
            fromItemName = ((Table) fromItem).getName();
        }
        return fromItem.getAlias() == null ? fromItemName : fromItem.getAlias().getName();
    }
}
