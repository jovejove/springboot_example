package com.example.mybatis;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import org.springframework.util.StringUtils;

import java.io.StringReader;
import java.util.List;

public class InsertWhereCondition {


    public static void main(String[] args) throws JSQLParserException {

        String sql =
// " SELECT A.code,COUNT(B.id) as value,A.value as name FROM sys_dict A\n" +
// " LEFT JOIN v_t_project B ON B.projectType=A.code\n" +
// " WHERE A.type='projectType' AND A.code IN (1,2)\n" +
// " GROUP BY A.code\n" +
// " UNION ALL\n" +
// " SELECT 3 AS code,COUNT(B.id) as value,'其他' as name FROM sys_dict A\n" +
// " LEFT JOIN v_t_project B ON B.projectType=A.code\n" +
// " WHERE A.type='projectType' AND A.code NOT IN (1,2)\n" +
// " GROUP BY A.code";
                " SELECT a.A FROM (" +
                        "SELECT b.B FROM " +
                        "(SELECT c.C FROM cc c) as b Left JOIN " +
                         "(SELECT * FROM F left join (select * from v) as x on x.id = F.id)d ON b.C = d.d" +
                        ") as a";


        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        StringReader stringReader = new StringReader(sql);
        Select select = (Select) parserManager.parse(stringReader);


        SelectBody selectBody = select.getSelectBody();
        String tableName = "v";
        if (StringUtils.isEmpty(tableName) && selectBody instanceof PlainSelect) {
            tableName = getTableName(selectBody);
        }

        processSelectBody(selectBody, tableName);
        System.out.println(selectBody.toString());
// FromItem fromItem = plain.getFromItem();
//有别名用别名，无别名用表名，防止字段冲突报错
        String fromItemName = "";
    }

    public static void processSelectBody(SelectBody selectBody, String tableName) throws JSQLParserException {
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

    public static void parsPlainSelect(SelectBody selectBody, String tableName) throws JSQLParserException {
        PlainSelect plain = (PlainSelect) selectBody;
        FromItem fromItem = plain.getFromItem();
        if (getTableName(fromItem, tableName)) {
            buildSubquery(plain, tableName);
            return;
        }
        List <Join> joinList = plain.getJoins();
        if (CollectionUtils.isNotEmpty(joinList)) {
            for (Join join : joinList) {
                if (getTableName(join.getRightItem(), tableName)) {
                    buildSubquery(plain, tableName);
                    return;
                }
            }
        }
    }

    public static boolean getTableName(FromItem fromItem, String tableName) throws JSQLParserException {
        String fromItemName = "";
        if (fromItem instanceof Table) {
            fromItemName = ((Table) fromItem).getName();
        }
        if (fromItem instanceof SubSelect) {
            SelectBody subSelectBody = ((SubSelect) fromItem).getSelectBody();
            processSelectBody(subSelectBody, tableName);
        }
        fromItemName = fromItem.getAlias() == null ? fromItemName : fromItem.getAlias().getName();
        if (fromItemName.equals(tableName)) {
            System.out.println("fromItemName -- " + tableName);
            return true;
        }
        return false;
    }

    public static String getTableName(SelectBody selectBody) {
        PlainSelect pain = (PlainSelect) selectBody;
        FromItem fromItem = pain.getFromItem();
        String fromItemName = "";
        if (fromItem instanceof Table) {
            fromItemName = ((Table) fromItem).getName();
        }
        return fromItem.getAlias() == null ? fromItemName : fromItem.getAlias().getName();
    }

    public static void buildSubquery(PlainSelect plain, String tableName) throws JSQLParserException {
        String dataAuthSql = " " + tableName + ".id in ('212','333')";

        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(new Column(tableName));
        
        
        if (StringUtils.hasLength(dataAuthSql)) {
            if (plain.getWhere() == null) {
                plain.setWhere(CCJSqlParserUtil.parseCondExpression(dataAuthSql));
            } else {
                plain.setWhere(new AndExpression(plain.getWhere(), CCJSqlParserUtil.parseCondExpression(dataAuthSql)));
            }
        }
    }
}
