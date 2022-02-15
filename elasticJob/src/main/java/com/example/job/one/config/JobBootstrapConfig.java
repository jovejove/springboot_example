package com.example.job.one.config;

import com.example.job.one.OneOffJobBootstrapJob;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.OneOffJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.shardingsphere.elasticjob.tracing.api.TracingConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Administrator
 */
@Component
public class JobBootstrapConfig {

    @Resource
    private ZookeeperRegistryCenter registryCenter;

    @Resource
    private OneOffJobBootstrapJob oneOffJobBootstrapJob;

    @Resource
    TracingConfiguration<DataSource> tracingConfig;

    @Bean
    public OneOffJobBootstrap oneOffJobBootstrap() {
        // 通过http一次性调用
        return new OneOffJobBootstrap(registryCenter, oneOffJobBootstrapJob,
                JobConfiguration.newBuilder("一次性作业", 2)
                        .addExtraConfigurations(tracingConfig)
                        .overwrite(true)
//                        .disabled(true)
                        .build());
    }

//    @Override
//    public void run(String... args) {
//        oneOffJobBootstrap().execute();
//    }
}
