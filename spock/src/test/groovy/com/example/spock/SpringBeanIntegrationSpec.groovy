
package com.example.spock

import com.example.spock.service.HelloWorldService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * Integration tests for ensuring compatibility with Spring-Boot's {@link WebMvcTest} annotation
 * in conjunction with {@link SpringBean}.
 */
//tag::include[]
@WebMvcTest
class SpringBeanIntegrationSpec extends Specification {

  @Autowired
  MockMvc mvc

  @SpringBean
  HelloWorldService helloWorldService = Stub()

  def "spring context loads for web mvc slice"() {
    given:
    helloWorldService.getHelloMessage() >> 'hello world'

    expect: "controller is available"
    mvc.perform(MockMvcRequestBuilders.get("/"))
      .andExpect(status().isOk())
      .andExpect(content().string("hello world"))
  }
}
//end::include[]
