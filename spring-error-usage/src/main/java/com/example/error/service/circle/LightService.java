package com.example.error.service.circle;

//@Service
public class LightService {
    public void start() {
        System.out.println("turn on all lights");
    }

    public void shutdown() {
        System.out.println("shutting down all lights");
    }
    public void check() {
        System.out.println("check all lights");
    }
}