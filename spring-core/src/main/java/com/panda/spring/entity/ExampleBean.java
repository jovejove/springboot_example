package com.panda.spring.entity;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Data
public class ExampleBean implements InitializingBean, DisposableBean {

    private final int years;

    private final String ultimateAnswer;

    public ExampleBean(int years, String ultimateAnswer) {
        this.years = years;
        this.ultimateAnswer = ultimateAnswer;
        System.out.println("ExampleBean construction");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ExampleBean InitializingBean.afterPropertiesSet()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("ExampleBean DisposableBean.destroy()");
    }


    public void init() {
        // do some destruction work (like releasing pooled connections)
        System.out.println("ExampleBean init()");
    }


    // jsr-250 postConstruct->afterPropertiesSet->init
    @PostConstruct
    public void populateMovieCache() {
        // populates the movie cache upon initialization...
        System.out.println("ExampleBean PostConstruct populateMovieCache");
    }

    // jsr-250  PreDestroy->destroy
    @PreDestroy
    public void clearMovieCache() {
        System.out.println("ExampleBean PreDestroy clearMovieCache");
        // clears the movie cache upon destruction...
    }
}