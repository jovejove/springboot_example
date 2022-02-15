/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.job.script;

import com.example.ElasticJobType;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.shardingsphere.elasticjob.script.props.ScriptJobProperties;
import org.apache.shardingsphere.elasticjob.tracing.api.TracingConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;

/**
 * @author Administrator
 */
@Component
public class ScriptJob implements CommandLineRunner {
    @Resource
    private ZookeeperRegistryCenter regCenter;

    @Resource
    TracingConfiguration<DataSource> tracingConfig;

    private static final String JOB_NAME = "执行脚本任务";
    private static final String CRON = "0/10 * * * * ?";
    private static final int SHARDING_TOTAL_COUNT = 2;

    @Override
    public void run(String... args) throws Exception {
        new ScheduleJobBootstrap(regCenter, ElasticJobType.SCRIPT.getId(), JobConfiguration.newBuilder(JOB_NAME, SHARDING_TOTAL_COUNT)
                .cron(CRON)
                .setProperty(ScriptJobProperties.SCRIPT_KEY, buildScriptCommandLine())
                .addExtraConfigurations(tracingConfig)
                .overwrite(true)
                .disabled(true)
                .build())
                .schedule();

    }

    private String buildScriptCommandLine() throws IOException {
        if (System.getProperties().getProperty("os.name").contains("Windows")) {
            return Paths.get(ScriptJob.class.getResource("/script/demo.bat").getPath().substring(1)).toString();
        }
        Path result = Paths.get(ScriptJob.class.getResource("/script/demo.sh").getPath());
        Files.setPosixFilePermissions(result, PosixFilePermissions.fromString("rwxr-xr-x"));
        return result.toString();
    }
}
