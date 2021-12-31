package com.panda.core.cfg;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
@Slf4j
public class MyService {

    private final MyProperties properties;

    public MyService(MyProperties properties) {
        this.properties = properties;
    }

    public void openConnection() {
        log.info(JSON.toJSONString(properties));
        MyProperties.Security security = properties.getSecurity();
        log.info(JSON.toJSONString(security));
    }

}

