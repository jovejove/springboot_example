package com.panda.design.behavior.chain.common;

import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ChainBase;
import org.apache.commons.chain.impl.ContextBase;

public class CommandTest {

    public static void main(String[] args) throws Exception {

        ChainBase chainBase = new ChainBase();
        chainBase.addCommand(new Commands1());
        chainBase.addCommand(new Commands2());
        chainBase.addCommand(new Commands3());
        Context context = new ContextBase();
        context.put("k12",1);
        chainBase.execute(context);

    }
}
