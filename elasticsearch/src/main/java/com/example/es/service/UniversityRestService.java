package com.example.es.service;

import com.alibaba.fastjson.JSONObject;
import com.example.es.entity.TalentUniversity;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UniversityRestService.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-02
 * @Version: 1.0
 */
@Service
public class UniversityRestService {
    @Resource
    RestHighLevelClient client;

    public Object getTermName(String name) throws IOException {
        String index = "talent_university";

        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name.keyword", name));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(50);
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHit[] hits = response.getHits().getHits();


        List<TalentUniversity> playerList = new ArrayList<>();
        for (SearchHit hit : hits) {

            TalentUniversity player = JSONObject.parseObject(hit.getSourceAsString(), TalentUniversity.class);
            playerList.add(player);
        }
//        return playerList;

        return hits;
    }
}
