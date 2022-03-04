package com.panda.spring.entity.flow;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 通过标记接口等填充 bean 的后处理器将实现postProcessBeforeInitialization
 * 而使用代理包装 bean 的后处理器通常会实现postProcessAfterInitialization
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 在任何 bean 初始化回调（如 InitializingBean 的afterPropertiesSet或自定义 init 方法）之前将此BeanPostProcessor应用于给定的新 bean 实例。 bean 将已填充属性值。
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ExampleBean) {
            System.out.println("BeanPostProcessor.postProcessBeforeInitialization Bean '" + beanName + "' created : " + JSONObject.toJSONString(bean));
        }
        return bean;
    }

    /**
     * 在任何 bean 初始化回调（如 InitializingBean 的afterPropertiesSet或自定义 init 方法）之后，将此BeanPostProcessor应用于给定的新 bean 实例。 bean 将已填充属性值。
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ExampleBean) {
            System.out.println("BeanPostProcessor.postProcessAfterInitialization Bean '" + beanName + "' created : " + JSONObject.toJSONString(bean));
        }
        return bean;
    }
}
