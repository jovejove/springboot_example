package com.example.job.dataflow.config;

import com.example.job.dataflow.SpringBootDataflowJob;
import com.example.job.simple.SpringBootSimpleJob;
import org.apache.commons.lang3.time.TimeZones;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.dataflow.props.DataflowJobProperties;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.shardingsphere.elasticjob.tracing.api.TracingConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Administrator
 */
@Component
public class SpringBootDataflowJobConfig implements CommandLineRunner {

    @Resource
    private ZookeeperRegistryCenter registryCenter;

    @Resource
    private SpringBootDataflowJob springBootDataflowJob;

    @Resource
    TracingConfiguration<DataSource> tracingConfig;

    private static final String JOB_NAME = "数据流作业";
    private static final String CRON = "0/30 * * * * ?";
    private static final int SHARDING_TOTAL_COUNT = 3;

    @Override
    public void run(String... args) {
        new ScheduleJobBootstrap(registryCenter, springBootDataflowJob,
                JobConfiguration.newBuilder(JOB_NAME, SHARDING_TOTAL_COUNT)
                        .cron(CRON)
                        .shardingItemParameters("0=Beijing,1=Shanghai,2=Guangzhou")
                        .overwrite(true)
                        .addExtraConfigurations(tracingConfig)
                        .timeZone("GMT+08:00")
                        .jobParameter("作业参数").setProperty(DataflowJobProperties.STREAM_PROCESS_KEY, Boolean.TRUE.toString())
                        .disabled(true)
                        .build())
                .schedule();
    }
}
