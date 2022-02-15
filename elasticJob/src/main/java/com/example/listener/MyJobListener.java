package com.example.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.infra.listener.ElasticJobListener;
import org.apache.shardingsphere.elasticjob.infra.listener.ShardingContexts;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Slf4j
@Component
public class MyJobListener implements ElasticJobListener {
    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        log.info("beforeJobExecuted shardingContexts:{}", JSON.toJSONString(shardingContexts));
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        log.info("afterJobExecuted shardingContexts:{}", JSON.toJSONString(shardingContexts));
    }

    @Override
    public String getType() {
        return "MyJobListener";
    }
}
