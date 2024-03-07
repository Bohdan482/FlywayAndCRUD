package org.example.Flyway;

import org.flywaydb.core.Flyway;


public class FlywayImplementation {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:./test", null, null)
                .load();
        flyway.migrate();
    }
}