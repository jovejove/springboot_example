package com.panda.design.creative.builder;

/**
 意图：将一个复杂的构建与其表示相分离，使得同样的构建过程可以创建不同的表示。<br/>

 主要解决：主要解决在软件系统中，有时候面临着"一个复杂对象"的创建工作，其通常由各个部分的子对象用一定的算法构成；
         由于需求的变化，这个复杂对象的各个部分经常面临着剧烈的变化，但是将它们组合在一起的算法却相对稳定。<br/>

 何时使用：一些基本部件不会变，而其组合经常变化的时候。<br/>

 如何解决：将变与不变分离开。
 */
public class MealBuilder {
 
   public Meal prepareVegMeal (){
      Meal meal = new Meal();
      meal.addItem(new VegBurger());
      meal.addItem(new Coke());
      return meal;
   }   
 
   public Meal prepareNonVegMeal (){
      Meal meal = new Meal();
      meal.addItem(new ChickenBurger());
      meal.addItem(new Pepsi());
      return meal;
   }
}