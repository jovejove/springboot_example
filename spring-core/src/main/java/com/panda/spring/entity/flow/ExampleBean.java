package com.panda.spring.entity.flow;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * @author Administrator
 * BeanNameAware:希望在 bean 工厂中了解其 bean 名称的 bean 实现的接口
 * ApplicationContextAware：以编程方式操作ApplicationContext创建它们的 bean
 * InitializingBean：由BeanFactory设置所有属性后需要做出反应的 bean 实现的接口：例如执行自定义初始化，或仅检查所有强制属性是否已设置。
 */
@Data
//@Component
public class ExampleBean implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {

//    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
//    public ExampleBean getExampleBean() {
//        return new ExampleBean();
//    }

    private int years;

    /**
     * Value注释的实际处理是由BeanPostProcessor执行的，这反过来意味着不能在BeanPostProcessor或BeanFactoryPostProcessor类型中使用@Value
     */
    @Value("port:${server.port}")
    private String applicationContextAware;

    private String afterProperty;

    private String beanName;

    @JSONField(serialize = false)
    private ApplicationContext applicationContext;

    public ExampleBean() {
        System.out.println("1 ExampleBean 无参构造");
    }

    public ExampleBean(int years) {
        System.out.println("1 ExampleBean 有参构造years:" + years);
        this.years = years;
    }

    public ExampleBean(int years, String applicationContextAware) {
        this.years = years;
        this.applicationContextAware = applicationContextAware;
        System.out.println("1 ExampleBean 有参构造");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("4 ExampleBean InitializingBean.afterPropertiesSet()");
        ExampleBean exampleBean = applicationContext.getBean(this.getBeanName(), ExampleBean.class);
        exampleBean.setAfterProperty("setAfterProperty");
    }

    @Override
    public void destroy() {
        System.out.println("end 2 ExampleBean DisposableBean.destroy()");
    }


    public void initMethod() {
        // do some destruction work (like releasing pooled connections)
        System.out.println("5 ExampleBean initMethod()");
    }

    public void destroyMethod() {
        // do some destruction work (like releasing pooled connections)
        System.out.println("end 3 ExampleBean destroyMethod()");
    }


    /**
     * jsr-250 servlet生效 @postConstruct->afterPropertiesSet->bean.initMethod
     */
//    @PostConstruct
    public void populateMovieCache() {
        // populates the movie cache upon initialization...
        System.out.println("ExampleBean PostConstruct populateMovieCache");
    }

    /**
     * jsr-250  servlet生效 PreDestroy->destroy->bean.destroyMethod
     */
//    @PreDestroy
    public void clearMovieCache() {
        System.out.println("ExampleBean PreDestroy clearMovieCache");
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
        System.out.println("2 BeanNameAware:" + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        ExampleBean exampleBean = applicationContext.getBean(this.getBeanName(), ExampleBean.class);
        System.out.println("3 ApplicationContextAware:" + exampleBean);
        exampleBean.setApplicationContextAware("applicationContextAware");
    }


}