package com.example.job.one;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Slf4j
@Component
public class OneOffJobBootstrapJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("只调用一次作业:{}", JSON.toJSONString(shardingContext));
    }
}
