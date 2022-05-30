package com.panda.design.behavior.strategy;

/**
 意图：定义一系列的算法,把它们一个个封装起来, 并且使它们可相互替换。

 主要解决：在有多种算法相似的情况下，使用 if...else 所带来的复杂和难以维护。

 何时使用：一个系统有许多许多类，而区分它们的只是他们直接的行为。

 如何解决：将这些算法封装成一个一个的类，任意地替换。

 关键代码：实现同一个接口。

 注意事项：如果一个系统的策略多于四个，就需要考虑使用混合模式，解决策略类膨胀的问题。
 */
public class StrategyDemo {
    public static void main(String[] args) {
        StrategyContext strategyContext = new StrategyContext(new OperationAdd());
        System.out.println(strategyContext.executeStrategy(1,2));
    }
}
