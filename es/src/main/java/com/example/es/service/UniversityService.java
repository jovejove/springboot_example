package com.example.es.service;

import com.example.es.entity.TalentUniversity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @ClassName: UniversityService.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-02
 * @Version: 1.0
 */
public interface UniversityService extends ElasticsearchRepository<TalentUniversity, String> {
    Page<TalentUniversity> findTalentUniversityByNameIs(String name, Pageable pageable);

    Page<TalentUniversity> findTalentUniversityByNameEquals(String name, Pageable pageable);

    Page<TalentUniversity> findTalentUniversityByName(String name, Pageable pageable);

    Page<TalentUniversity> findTalentUniversityByNameMatches(String name, Pageable pageable);

}
