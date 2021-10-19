package com.panda.spring.service;

import com.panda.spring.entity.Person;

/**
 * @author Administrator
 */
public class PersonService {

   public Person getPerson() {
        return new Person("panda",20);
    }
}
