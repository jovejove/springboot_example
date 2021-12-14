package com.panda.design.context;

import com.panda.design.service.Strategy;

public class StrategyContext {
    private Strategy strategy;

    public StrategyContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}
