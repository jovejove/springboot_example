package com.panda.spring.life;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


/**
 * @author Administrator
 * BeanNameAware:希望在 bean 工厂中了解其 bean 名称的 bean 实现的接口
 * ApplicationContextAware：以编程方式操作ApplicationContext创建它们的 bean
 * InitializingBean：由BeanFactory设置所有属性后需要做出反应的 bean 实现的接口：例如执行自定义初始化，或仅检查所有强制属性是否已设置。
 */
@Data
@Service
//@Lazy
public class MyServiceImpl implements MyService, BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {

    /**
     * Value注释的实际处理是由BeanPostProcessor执行的，这反过来意味着不能在BeanPostProcessor或BeanFactoryPostProcessor类型中使用@Value
     */
    @Value("${server.port}")
    private String applicationContextAwareOperaBean;

    private String afterProperty;

    private String beanName;

    @JSONField(serialize = false)
    private ApplicationContext applicationContext;

    public MyServiceImpl() {
        System.out.println("1 默认无参构造实例化");
    }


    /**
     * 执行自定义初始化或仅检查所有强制属性是否已设置
     */
    @Override
    public void afterPropertiesSet() {
        MyServiceImpl myService = applicationContext.getBean(this.getBeanName(), MyServiceImpl.class);
        myService.setAfterProperty("由BeanFactory设置所有属性后需要做出反应的bean实现的接口：例如执行自定义初始化，或仅检查所有强制属性是否已设置");
        myService.setApplicationContextAwareOperaBean(null);
        System.out.println("4 InitializingBean.afterPropertiesSet():" + myService);
    }

    @Override
    public void destroy() {
        System.out.println("end 2  DisposableBean.destroy()");
    }


    public void initMethod() {
        // do some destruction work (like releasing pooled connections)
        System.out.println("5 BeanDefinition  initMethod()");
    }

    public void destroyMethod() {
        // do some destruction work (like releasing pooled connections)
        System.out.println("end 3 BeanDefinition  destroyMethod()");
    }


    /**
     * jsr-250 servlet生效 @postConstruct->afterPropertiesSet->bean.initMethod
     */
//    @PostConstruct
    public void populateMovieCache() {
        // populates the movie cache upon initialization...
        System.out.println("@PostConstruct populateMovieCache");
    }

    /**
     * jsr-250  servlet生效 PreDestroy->destroy->bean.destroyMethod
     */
//    @PreDestroy
    public void clearMovieCache() {
        System.out.println(" @PreDestroy clearMovieCache");
        // clears the movie cache upon destruction...
    }

    /**
     * 在创建此 bean 的 bean 工厂中设置 bean 的名称
     *
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("2 调用Aware相关接口BeanNameAware:" + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 给afterPropertiesSet使用 根据applicationContext获取bean
        this.applicationContext = applicationContext;
        MyServiceImpl myService = applicationContext.getBean(this.getBeanName(), MyServiceImpl.class);
        myService.setApplicationContextAwareOperaBean("以编程方式操作ApplicationContext创建它们的bean");
        System.out.println("3 setApplicationContext" + myService);
    }


    @Override
    public String sayHello() {
        return "hello beanPostProcessor";
    }
}