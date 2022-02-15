package com.example.job.simple.config;

import com.alibaba.fastjson.JSON;
import com.example.entity.JobStatusTraceLog;
import com.example.job.one.OneOffJobBootstrapJob;
import com.example.job.simple.SpringBootSimpleJob;
import com.example.service.impl.JobStatusTraceLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.OneOffJobBootstrap;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.shardingsphere.elasticjob.tracing.api.TracingConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Administrator
 */
@Slf4j
@Component
public class SpringBootSimpleJobConfig implements CommandLineRunner {
    @Resource
    private ZookeeperRegistryCenter registryCenter;
    @Resource
    private SpringBootSimpleJob springBootSimpleJob;
    @Resource
    TracingConfiguration<DataSource> tracingConfig;
    private static final String JOB_NAME = "简单作业";
    private static final String CRON = "0/5 * * * * ?";
    private static final int SHARDING_TOTAL_COUNT = 2;

    @Resource
    private JobStatusTraceLogService jobStatusTraceLogService;

    @Override
    public void run(String... args) {

        int count = jobStatusTraceLogService.count();
        log.info("jobStatusTraceLog count:{}", JSON.toJSONString(count));


        for (int i = 0; i < 3; i++) {
            new ScheduleJobBootstrap(registryCenter, springBootSimpleJob,
                    JobConfiguration.newBuilder(JOB_NAME + i, SHARDING_TOTAL_COUNT)
                            .cron(CRON)
                            .overwrite(true)
                            .shardingItemParameters("0=Beijing,1=Shanghai,2=Guangzhou")
                            .addExtraConfigurations(tracingConfig)
                            .timeZone("GMT+08:00")
                            .jobParameter("作业参数" + i)
                            .jobErrorHandlerType("LOG")
                            .disabled(true)
//                            .jobListenerTypes("MyJobListener")
                            .build())
                    .schedule();
        }

    }
}
