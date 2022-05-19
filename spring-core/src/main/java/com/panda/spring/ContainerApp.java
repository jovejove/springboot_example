package com.panda.spring;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.panda.spring.life.ExampleBeanXml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 */
@Slf4j
public class ContainerApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // retrieve configured instance
        ExampleBeanXml exampleBean = context.getBean("exampleBeanXml", ExampleBeanXml.class);

        // use configured instance
        log.info(JSONObject.toJSONString(exampleBean, SerializerFeature.WriteMapNullValue));
    }
}
