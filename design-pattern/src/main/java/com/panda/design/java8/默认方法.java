package com.panda.design.java8;

/**
 * 默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法
 * 引进的默认方法的目的是为了解决接口的修改与现有的实现不兼容的问题
 */
public class 默认方法 {
    public static void main(String args[]) {
        Vehicle vehicle = new Car();
        vehicle.print();
    }
}

interface Vehicle {
    default void print() {
        System.out.println("我是一辆车!");
    }

    /**
     * 静态方法
     */
    static void blowHorn() {
        System.out.println("按喇叭!!!");
    }
}

interface FourWheeler {
    default void print() {
        System.out.println("我是一辆四轮车!");
    }
}

class Car implements Vehicle, FourWheeler {
    @Override
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();

        Vehicle.blowHorn();
        System.out.println("我是一辆汽车!");
    }
}
