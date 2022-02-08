package com.example.spock

import com.alibaba.fastjson.JSON
import com.example.spock.controller.DemoController
import com.example.spock.service.DemoService
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@WebMvcTest(controllers = [DemoController.class])
//@AutoConfigureMybatis
class DemoControllerSpec extends Specification {
    @Autowired
    MockMvc mockMvc

    @SpringBean
    DemoService demoService = Mock()

    def setup() {
        demoService.getDemo(_ as Integer) >> "2"
    }


    def "mvc test"() {
        given:

        def demoId = 2
        expect:
        mockMvc.perform(MockMvcRequestBuilders.post("/demo").
                contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(demoId)))
                .andExpect(MockMvcResultMatchers.status().isOk())
    }




//    @TestConfiguration
//    static class MockConfig{
//        def detachedMockFactory = new DetachedMockFactory()
//
//        @Bean
//        DemoService demoService(){
//            return detachedMockFactory.Mock(DemoService)
//        }
//    }
}
