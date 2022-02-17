package com.panda.design.behaviorType.Chain.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Commands2 implements Command {

    private static final Logger logger = LoggerFactory.getLogger(Commands2.class);


    @Override
    public boolean execute(Context context) throws Exception {
        Object v1 = context.get("k1");
        logger.info("do something by Commands2... params is {}", JSONObject.toJSONString(context));
        if (v1 != null) {
            int IntV1 = (Integer) v1;
            context.put("k1", IntV1 + 1);
            // 执行后续Command
            return false;
        }
        // 不执行后续Command
        return true;
    }
}
