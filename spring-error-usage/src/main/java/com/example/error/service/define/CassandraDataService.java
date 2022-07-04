package com.example.error.service.define;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class CassandraDataService implements DataService{
    @Override
    public void deleteStudent(int id) {
        log.info("delete student info maintained by cassandra");
    }
}