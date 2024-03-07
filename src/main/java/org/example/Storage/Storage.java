package org.example.Storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Storage {
    private static final Storage INSTANCE = new Storage();

    private Connection connection;

    private Storage (){
        String url = "jdbc:h2:./test";
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Storage getInstance(){
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }
}