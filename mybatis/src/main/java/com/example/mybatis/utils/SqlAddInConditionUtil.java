package com.example.mybatis.utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author junjieLuo
 * <p>支持简单的in条件拼接</p>
 * <p>注意：请使用者先自行评估是否符合业务需求。如果不符合，那么请勿使用，同时请勿修改代码。</p>
 */
public class SqlAddInConditionUtil {
    private static final Logger log = LoggerFactory.getLogger(SqlAddInConditionUtil.class);

//    public static void main(String[] args) throws Exception {
//
//        String sql =
//                "SELECT ko.*, " +
//                        "       NULL        AS            academyId, " +
//                        "       NULL        AS            branchSchoolId, " +
//                        "       NULL        AS            academyName, " +
//                        "       NULL        AS            branchSchoolName, " +
//                        "       leaderuser.name           leaderName, " +
//                        "       leaderuser.job_phone      leaderPhone, " +
//                        "       koadmin.name              adminName, " +
//                        "       koadminaccount.login_name adminAccount, " +
//                        "       kor.role_id, " +
//                        "       p1.p_org_id AS            parent_org_id, " +
//                        "       p2.p_org_id AS            parent_org_id2, " +
//                        "       p3.p_org_id AS            parent_org_id3 " +
//                        "FROM kf_organization ko " +
//                        "         LEFT JOIN kf_org_relationship p1 ON ko.id = p1.org_id AND p1.RENT_ID = 449 " +
//                        "         LEFT JOIN kf_org_relationship p2 ON p1.p_org_id = p2.org_id AND p2.RENT_ID = 449 " +
//                        "         LEFT JOIN kf_org_relationship p3 ON p2.p_org_id = p3.org_id AND p3.RENT_ID = 449 " +
//                        "         LEFT JOIN kf_user koadmin ON ko.admin_id = koadmin.id AND koadmin.RENT_ID = 449 " +
//                        "         LEFT JOIN kf_account koadminaccount ON koadmin.id = koadminaccount.user_id AND koadminaccount.RENT_ID = 449 " +
//                        "         LEFT JOIN kf_user leaderuser ON ko.leader_id = leaderuser.id AND leaderuser.RENT_ID = 449 " +
//                        "         LEFT JOIN kf_organization orgp1 ON p1.p_org_id = orgp1.id AND orgp1.RENT_ID = 449 " +
//                        "         LEFT JOIN kf_organization orgp2 ON p2.p_org_id = orgp2.id AND orgp2.RENT_ID = 449 " +
//                        "         LEFT JOIN kf_organization orgp3 ON p3.p_org_id = orgp3.id AND orgp3.RENT_ID = 449 " +
//                        "         LEFT JOIN kf_org_role kor ON ko.id = kor.org_id AND kor.RENT_ID = 449 " +
//                        "WHERE ko.id  in(1,2,100) " +
//                        "  AND ko.RENT_ID = 449";
//        CCJSqlParserManager parserManager = new CCJSqlParserManager();
//        StringReader stringReader = new StringReader(sql);
//        Select select = (Select) parserManager.parse(stringReader);
//
//        SelectBody selectBody = select.getSelectBody();
//
//        String tableName = "kf_organization";
//        processSelectBody(selectBody, tableName, "id", new ArrayList<>());
//        log.info(selectBody.toString());
//    }

    /**
     * 处理sql语句
     *
     * @param selectBody
     * @param tableName
     * @param filterField
     * @param filterDataList
     * @throws JSQLParserException
     */
    public static void processSelectBody(SelectBody selectBody, String tableName, String filterField, List<String> filterDataList) throws JSQLParserException {

        if (!StringUtils.hasLength(tableName) || !StringUtils.hasLength(tableName) || CollectionUtils.isEmpty(filterDataList)) {
            return;
        }


        if (selectBody instanceof PlainSelect) {
            parsPlainSelect(selectBody, tableName, filterField, filterDataList);
        } else if (selectBody instanceof WithItem) {
            WithItem withItem = (WithItem) selectBody;
            if (withItem.getSelectBody() != null) {
                processSelectBody(withItem.getSelectBody(), tableName, filterField, filterDataList);
            }
        } else {
            SetOperationList operationList = (SetOperationList) selectBody;
            List<SelectBody> selects = operationList.getSelects();
            for (SelectBody select : selects) {
                processSelectBody(select, tableName, filterField, filterDataList);
            }
        }
    }

    /**
     * 解析select语句
     *
     * @param selectBody
     * @param tableName
     * @throws JSQLParserException
     */
    private static void parsPlainSelect(SelectBody selectBody, String tableName, String filterField, List<String> filterDataList) throws JSQLParserException {
        PlainSelect plain = (PlainSelect) selectBody;

        FromItem fromItem = plain.getFromItem();
        if (isMatchTableName(fromItem, tableName, filterField, filterDataList)) {
            String tableAlias = getTableAlias(fromItem);
            buildSubQuery(plain, tableAlias, filterField, filterDataList);
        }

        List<Join> joinList = plain.getJoins();
        if (CollectionUtils.isNotEmpty(joinList)) {
            for (Join join : joinList) {
                FromItem rightItem = join.getRightItem();
                if (isMatchTableName(rightItem, tableName, filterField, filterDataList)) {
                    buildJoinQueryWithInExpression(join, getTableAlias(rightItem), filterField, filterDataList);
                }
            }
        }
    }

    /**
     * @param fromItem  表名
     * @param tableName 表名
     * @return 递归查询表并验证fromItem是否匹配tableName
     * @throws JSQLParserException
     */
    private static boolean isMatchTableName(FromItem fromItem, String tableName, String filterField, List<String> filterDataList) throws JSQLParserException {
        String fromItemName = "";

        if (fromItem instanceof Table) {
            fromItemName = ((Table) fromItem).getName();
        }

        if (fromItem instanceof SubSelect) {
            SelectBody subSelectBody = ((SubSelect) fromItem).getSelectBody();
            // 递归查询表
            processSelectBody(subSelectBody, tableName, filterField, filterDataList);
        }

        if (fromItemName.equals(tableName)) {
            return true;
        }
        return false;
    }


    /**
     * 加where条件
     *
     * @param plain
     * @param tableName
     * @throws JSQLParserException
     */
    private static void buildSubQuery(PlainSelect plain, String tableName, String filterField, List<String> filterDataList) throws JSQLParserException {

        InExpression inExpression = getInExpression(tableName, filterField, filterDataList);

        Expression plainWhere = plain.getWhere();
        if (plainWhere == null) {
            plain.setWhere(inExpression);
        } else {
            plain.setWhere(new AndExpression(plainWhere, inExpression));
        }
    }


    /**
     * 处理join语句
     *
     * @param join
     * @param tableName
     * @throws JSQLParserException
     */
    private static void buildJoinQueryWithInExpression(Join join, String tableName, String filterField, List<String> filterDataList) throws JSQLParserException {
        InExpression inExpression = getInExpression(tableName, filterField, filterDataList);
        join.setOnExpression(new AndExpression(join.getOnExpression(), inExpression));
    }


    /**
     * 处理in语句
     *
     * @param tableName
     * @param filterField
     * @param filterDataList
     * @return InExpression
     * @throws JSQLParserException
     */
    private static InExpression getInExpression(String tableName, String filterField, List<String> filterDataList) throws JSQLParserException {
        InExpression inExpression = new InExpression();
        inExpression.setLeftExpression(new Column(tableName + "." + filterField));
        List<Expression> expressionList = new ArrayList<Expression>();
        try {
            for (String item : filterDataList) {
                expressionList.add(CCJSqlParserUtil.parseExpression(String.format("%s%s%s", "'", item, "'")));
//                expressionList.add(CCJSqlParserUtil.parseExpression(item));
            }
        } catch (JSQLParserException e) {
            log.error("SqlAddInConditionUtil.getInExpression CCJSqlParserUtil in interceptor parseCondExpression error , filterDataList :\r\n {}", JSONObject.toJSONString(filterDataList));
            throw e;
        }
        inExpression.setRightItemsList(new ExpressionList(expressionList));
        return inExpression;
    }


    /**
     * @param fromItem
     * @return 若表有别名返回别名，否则返回表名
     */
    private static String getTableAlias(FromItem fromItem) {
        String fromItemName = "";
        if (fromItem instanceof Table) {
            fromItemName = ((Table) fromItem).getName();
        }
        return fromItem.getAlias() == null ? fromItemName : fromItem.getAlias().getName();
    }

}
