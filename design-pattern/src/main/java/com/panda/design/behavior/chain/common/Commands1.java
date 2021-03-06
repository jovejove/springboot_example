package com.panda.design.behavior.chain.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Commands1 implements Command {

    private static final Logger logger = LoggerFactory.getLogger(Commands1.class);


    @Override
    public boolean execute(Context context) throws Exception {
        Object v1 = context.get("k1");
        logger.info("do something by Commands1... params is {}", JSONObject.toJSONString(context));
        if(v1 != null){
            int IntV1 = (Integer) v1;
            context.put("k1", IntV1+1);
        }
        return false;
    }
}