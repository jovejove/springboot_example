

package com.example.spock

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

@SpringBootTest(classes = SpockApplication)
class SimpleBootAppIntegrationSpec extends Specification {
  @Autowired
  ApplicationContext context

  def "test context loads"() {
    expect:
    context != null
    context.containsBean("helloWorldService")
    context.containsBean("simpleBootApp")
    context.containsBean("scopedHelloWorldService")
  }
}
