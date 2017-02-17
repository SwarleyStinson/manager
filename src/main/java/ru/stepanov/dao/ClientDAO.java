package ru.stepanov.dao;

import org.h2.tools.RunScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.stepanov.entity.Client;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Client> getLast15() {
        List<Client> result = getAll();

        int count = 0;
        int cycleEnd = result.size();

        while (count <= cycleEnd - 15) {
            result.remove(0);
            count++;
        }
        return result;
    }

    public List<Client> getAll() {
        String SQL_GET_ALL = "SELECT * FROM client";
        Statement statement;
        List<Client> result = null;

        try {
            statement = jdbcTemplate.getDataSource().getConnection().createStatement();

            result = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);

            while (resultSet.next()) {
                Client client = new Client();

                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setLogin(resultSet.getString("login"));
                client.setPassword(resultSet.getString("password"));
                client.setEmail(resultSet.getString("email"));
                client.setType(resultSet.getString("type"));
                result.add(client);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void setToDefaultState() {
        try {
            RunScript.execute(jdbcTemplate.getDataSource().getConnection(), new FileReader("schema.sql"));
            RunScript.execute(jdbcTemplate.getDataSource().getConnection(), new FileReader("data.sql"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("-------: поломался setToDefaultState");
            e.printStackTrace();
        }
    }

    public void deleteClientByID(int id) throws ClassNotFoundException, SQLException {
        String SQL_DELETE_BY_ID = "DELETE FROM client WHERE id=" + id;

        //Альтернативный способ получения connection
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/TestDatabase", "root", "root");
        Statement statement = conn.createStatement();
        statement.execute(SQL_DELETE_BY_ID);
        conn.close();

//        try {
//            Statement statement = jdbcTemplate.getDataSource().getConnection().createStatement();
//            statement.execute(SQL_DELETE_BY_ID);
//        } catch (SQLException e) {
//
//            System.out.println("Метод deleteClient поломался...");
//            e.printStackTrace();
//        }
    }

    public void addClient(Client client) {
        final String SQL_INSERT_CLIENT =
                "insert into CLIENT (name, login, password, email, type) values (?,?,?,?,?)";

        jdbcTemplate.update(SQL_INSERT_CLIENT,
                client.getName(),
                client.getLogin(),
                client.getPassword(),
                client.getEmail(),
                client.getType()
        );
    }

    public void setClientByID(Client client, int id) {
        final String SQL_UPDATE_CLIENT =
                "update CLIENT set name=?, login=?, password=?, email=?, type=? where id=" + id;

        jdbcTemplate.update(SQL_UPDATE_CLIENT,
                client.getName(),
                client.getLogin(),
                client.getPassword(),
                client.getEmail(),
                client.getType()
        );
    }
}
