package com.example.spock

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
@SpringBootTest(classes = [SpockApplicationTest.class])
class SpockApplicationTest extends Specification  {
    def "test spock"() {
        expect:
        1 == 1
        System.out.println("hello word")
    }

}
