package com.example.mybatis.interceptor;

import com.example.mybatis.utils.SqlAddInConditionUtil;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author junjieLuo
 */
@Component
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
public class QueryOrderInterceptor implements Interceptor {

    private final Logger logger = LoggerFactory.getLogger(QueryOrderInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];
        Executor executor = (Executor) invocation.getTarget();
        CacheKey cacheKey;
        BoundSql boundSql;
        //由于逻辑关系，只会进入一次
        if (args.length == 4) {
            //4 个参数时
            boundSql = mappedStatement.getBoundSql(parameter);
            cacheKey = executor.createCacheKey(mappedStatement, parameter, rowBounds, boundSql);
        } else {
            //6 个参数时
            cacheKey = (CacheKey) args[4];
            boundSql = (BoundSql) args[5];
        }
        //TODO 自己要进行的各种处理
        handleOriginSqlAddAuthCondition(mappedStatement, boundSql);
        //注：下面的方法可以根据自己的逻辑调用多次，在分页插件中，count 和 page 各调用了一次
        return executor.query(mappedStatement, parameter, rowBounds, resultHandler, cacheKey, boundSql);
    }

    private void handleOriginSqlAddAuthCondition(MappedStatement mappedStatement, BoundSql boundSql) throws Exception {
        Class<?> classType = Class.forName(mappedStatement.getId().substring(0, mappedStatement.getId().lastIndexOf(".")));
        String mName = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".") + 1, mappedStatement.getId().length());
        String count = "_COUNT";
        for (Method method : classType.getDeclaredMethods()) {
            //注解逻辑判断  添加注解了才拦截
            if (method.isAnnotationPresent(FillAuthCondition.class) && (mName.equals(method.getName()) || mName.equals(method.getName() + count))) {
                FillAuthCondition interceptorAnnotation = method.getAnnotation(FillAuthCondition.class);
                if (interceptorAnnotation.intercept()) {
                    // todo 处理list数据权限列表
                    LinkedList<String> list = new LinkedList<>();
                    list.add("1");
                    list.add("2");
                    list.add("3");
                    generateSqlWithAuthCondition(boundSql, interceptorAnnotation.tableName(), interceptorAnnotation.filterField(), list);
                }
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }


    private void generateSqlWithAuthCondition(BoundSql boundSql, String tableName, String filterField, List<String> filterDataList) throws Exception {
        String originSql = boundSql.getSql();

        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select;
        try {
            select = (Select) parserManager.parse(new StringReader(originSql));
            SelectBody selectBody = select.getSelectBody();

            // sql语句加in条件
            SqlAddInConditionUtil.processSelectBody(selectBody, tableName, filterField, filterDataList);

            logger.info(selectBody.toString());
        } catch (JSQLParserException e) {
            logger.error("CCJSqlParserUtil in interceptor parse error , originSql :\r\n {}", originSql);
            throw e;
        }
        resetBoundSql(boundSql, select.toString());

    }


    /**
     * 更新sql
     *
     * @param boundSql 原始sql对象
     * @param newSql   新sql
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private void resetBoundSql(BoundSql boundSql, String newSql) throws NoSuchFieldException, IllegalAccessException {
        Field sqlField = boundSql.getClass().getDeclaredField("sql");
        sqlField.setAccessible(true);
        sqlField.set(boundSql, newSql);
    }

}
