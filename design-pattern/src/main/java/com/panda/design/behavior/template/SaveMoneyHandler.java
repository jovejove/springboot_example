package com.panda.design.behavior.template;

/**
 * @author Administrator
 */
public class SaveMoneyHandler extends AbstractBusinessHandler {

    @Override
    public void handle() {
        System.out.println("save 1000");
    }


    public static void main(String []args){
        SaveMoneyHandler saveMoneyHandler = new SaveMoneyHandler();
        saveMoneyHandler.execute();
    }
}