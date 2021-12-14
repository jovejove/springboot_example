package com.panda.design;

import com.panda.design.context.StrategyContext;
import com.panda.design.service.impl.OperationAdd;

public class Main {
    public static void main(String[] args) {
        StrategyContext strategyContext = new StrategyContext(new OperationAdd());
        System.out.println(strategyContext.executeStrategy(1,2));
    }
}
