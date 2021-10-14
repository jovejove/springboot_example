package com.example.mybatis.interceptor;

import com.example.mybatis.utils.InsertInConditionUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Component
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
@Slf4j
public class QueryOrderInterceptor implements Interceptor {
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
            if (method.isAnnotationPresent(InterceptAnnotation.class) && (mName.equals(method.getName()) || mName.equals(method.getName() + count))) {
                InterceptAnnotation interceptorAnnotation = method.getAnnotation(InterceptAnnotation.class);
                if (interceptorAnnotation.intercept()) {
                    // todo 数据列表参数需要校验
                    generateSqlWithAuthCondition(boundSql, interceptorAnnotation.tableName(), interceptorAnnotation.filterField(),new ArrayList<>());
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


    private void generateSqlWithAuthCondition(BoundSql boundSql, String tableName, String filterField, List<String > filterDataList) throws Exception {
        String originSql = boundSql.getSql();

        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select;
        try {
            select = (Select) parserManager.parse(new StringReader(originSql));
            SelectBody selectBody = select.getSelectBody();
            InsertInConditionUtil.processSelectBody(selectBody, tableName,filterField,filterDataList);

            log.info(selectBody.toString());
        } catch (JSQLParserException e) {
            log.error("CCJSqlParserUtil in interceptor parse error , originSql :\r\n {}", originSql);
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
