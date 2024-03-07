package org.example.ClientDAO;

import org.example.Storage.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService implements ClientDAO {
    private final Connection connection = Storage.getInstance().getConnection();
    private static final String NAMEEXCEPTIONMESSAGE =
            "Кількість знаків імені не може дорівнювати нулю, має перевищувати 2 та бути не більше за 1000";
    private static final String IDEXCEPTIONMESSAGE = "Ідентифікатор не може дорівнювати нулю";

    @Override
    public long create(String name) {
        PreparedStatement ps = null;
        Exception e = new Exception(NAMEEXCEPTIONMESSAGE);

        long id = 0L;
        String createClientSql = "INSERT INTO client (name) values (?)";

        try{
            ps = connection.prepareStatement(createClientSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            if (name.length()<3 || name.length()>1000) {
                throw e;
            } else ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()){
                id = rs.getLong("id");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            closePreparedStatement(ps);
        }
        return id;
    }

    @Override
    public String getById(long id) {
        PreparedStatement ps = null;
        Exception ex = new Exception(IDEXCEPTIONMESSAGE);

        String name = "";
        String sql = "SELECT name FROM client WHERE id = ?";

        try {
            ps = connection.prepareStatement(sql);
            if (id == 0L){
                throw ex;
            }else ps.setString(1, String.valueOf(id));
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                name = resultSet.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closePreparedStatement(ps);
        }
        return name;
    }

    @Override
    public void setName(long id, String name) {
        PreparedStatement ps = null;
        Exception idException = new Exception(IDEXCEPTIONMESSAGE);
        Exception nameException = new Exception(NAMEEXCEPTIONMESSAGE);

        String sql = "UPDATE client SET name = ? WHERE id = ?";

        try{
            ps = connection.prepareStatement(sql);
            if (name.length()<3 || name.length()>1000) {
                throw nameException;
            } else ps.setString(1, name);
            if (id == 0L){
                throw idException;
            }else ps.setLong(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closePreparedStatement(ps);
        }
    }

    @Override
    public void deleteById(long id) {
        PreparedStatement ps = null;
        Exception ex = new Exception(IDEXCEPTIONMESSAGE);

        String sql = "DELETE FROM client WHERE id = ?";

        try {
            ps = connection.prepareStatement(sql);
            if (id == 0L){
                throw ex;
            }else ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closePreparedStatement(ps);
        }
    }

    @Override
    public List<Client> listAll() {
        Client client = new Client();
        List<Client> list = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));
                list.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}