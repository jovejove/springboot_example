package com.example.error.entity;

public class User {
    private String payNum;
    public User(String payNum) {
        this.payNum = payNum;
    }
    public String getPayNum() {
        return payNum;
    }
    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }
}