package org.example;

import org.example.ClientDAO.ClientService;
import org.flywaydb.core.Flyway;

public class Main {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:./test", null, null)
                .load();
        flyway.migrate();

        ClientService clientService = new ClientService();
        System.out.println(clientService.create("Elein"));
    }
    }