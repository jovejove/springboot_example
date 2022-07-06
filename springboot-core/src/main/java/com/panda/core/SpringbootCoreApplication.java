package com.panda.core;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Administrator
 */
@EnableAsync
@SpringBootApplication
@ConfigurationPropertiesScan({"com.panda.core.cfg"})
public class SpringbootCoreApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringbootCoreApplication.class);
        // disabled banner
        application.setBannerMode(Banner.Mode.OFF);
        // 禁止命令行参数加入到环境变量
        application.setAddCommandLineProperties(false);
        application.run(args);
    }

}
