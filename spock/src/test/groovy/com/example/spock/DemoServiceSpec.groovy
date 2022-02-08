package com.example.spock

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.example.spock.entity.DemoEntity
import com.example.spock.repository.DemoRepository
import com.example.spock.service.impl.DemoServiceImpl
import spock.lang.Specification

class DemoServiceSpec extends Specification{

    @Collaborator
    DemoRepository demoRepository = Mock(DemoRepository) {
        DemoEntity demoEntity = new DemoEntity()
        demoEntity.setDemoId(1)
        demoEntity.setDemoStr("panda")
        getDemo(_ as Integer) >> demoEntity
    }

    @Subject
    DemoServiceImpl demoService

    def "service test"(){
        expect:
        DemoEntity demoEntity = new DemoEntity()
        demoEntity.setDemoId(1)
        demoEntity.setDemoStr("panda")
        demoService.getDemo(2) == demoEntity
    }
}
