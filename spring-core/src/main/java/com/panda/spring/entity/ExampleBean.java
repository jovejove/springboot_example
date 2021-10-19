package com.panda.spring.entity;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Data
public class ExampleBean implements InitializingBean, DisposableBean {

    // Number of years to calculate the Ultimate Answer
    private final int years;

    // The Answer to Life, the Universe, and Everything
    private final String ultimateAnswer;

    public ExampleBean(int years, String ultimateAnswer) {
        this.years = years;
        this.ultimateAnswer = ultimateAnswer;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean.afterPropertiesSet()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean.destroy()");
    }


    public void init() {
        // do some destruction work (like releasing pooled connections)
        System.out.println("init()");
    }


    // postConstruct->afterPropertiesSet->init
    @PostConstruct
    public void populateMovieCache() {
        // populates the movie cache upon initialization...
        System.out.println("PostConstruct populateMovieCache");
    }

    @PreDestroy
    public void clearMovieCache() {
        System.out.println("PreDestroy clearMovieCache");
        // clears the movie cache upon destruction...
    }
}