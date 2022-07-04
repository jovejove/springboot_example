package com.example.error.service.circle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * doCreateBean 管理了 Bean 的整个生命周期中几乎所有的关键节点
 * Bean 实例的创建；
 * Bean 对象依赖的注入；
 * 定制类初始化方法的回调；
 * Disposable 方法的注册。
 * org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean
 * (java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])
 */

@Configuration
public class BeanConfiguration {

    @Bean(destroyMethod="")
    public LightService getTransmission(){
        return new LightService();
    }
}