package org.example.Controller;

import org.example.ClientDAO.Client;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface ClientController {
    long create(String name);
    String getById(long id);
    void setName(long id, String name);
    void deleteById(long id);
    List<Client> listAll();
    default void closePreparedStatement(PreparedStatement ps){
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}