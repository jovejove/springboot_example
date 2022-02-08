package com.example.spock

import com.example.spock.entity.DemoEntity
import com.example.spock.repository.DemoRepository
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification
@ActiveProfiles("qa")
@MybatisTest
@Rollback(false)
class DemoRepositorySpec extends Specification{

    @Autowired
    DemoRepository demoRepository
// expect  when-then精简

    def "create entity test"(){
        given:
        DemoEntity demoEntity = new DemoEntity()
        demoEntity.demoId = demoId
        demoEntity.demoStr = demoStr
        expect:
        demoRepository.createDemo(demoEntity) == 1

        where:
        demoId | demoStr
        1 | "str1"
        2 | "str2"

    }

    def "get entity test"(){
        expect:
        demoRepository.getDemo(1) != null
    }
}
