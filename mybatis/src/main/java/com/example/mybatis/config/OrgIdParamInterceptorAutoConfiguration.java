package com.example.mybatis.config;

import com.example.mybatis.interceptor.QueryOrderInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 *
 * @description 机构参数插件自动配置
 * @author Kennor
 *
 */
@Configuration
public class OrgIdParamInterceptorAutoConfiguration {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;


    /**
     *  利用Spring容器的注入的依赖特性，先注入PageHelperAutoConfiguration对象，确保分页插件优先加载到拦截器链路中
     */
//    @Autowired
//    private PageHelperAutoConfiguration pageHelperAutoConfiguration;

    @PostConstruct
    public void addInterceptor(){
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(new QueryOrderInterceptor());
        }
    }
}
