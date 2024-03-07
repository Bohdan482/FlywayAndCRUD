package org.example;

import org.example.ClientDAO.ClientService;

public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        System.out.println(clientService.getById(0));
    }
    }