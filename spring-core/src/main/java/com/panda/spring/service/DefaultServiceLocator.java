package com.panda.spring.service;

public class DefaultServiceLocator {

    private static ClientService clientService = ClientService.createInstance();

    public ClientService createClientServiceInstance() {
        return clientService;
    }
}