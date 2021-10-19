package com.panda.spring.service;

/**
 * @author Administrator
 */
public class ClientService {
    private static ClientService clientService = new ClientService();
    private ClientService() {}

    public static ClientService createInstance() {
        return clientService;
    }
}