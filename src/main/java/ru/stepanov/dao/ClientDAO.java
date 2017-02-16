package ru.stepanov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.stepanov.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Client> getLast20() throws SQLException {
        String SQL_GET_ALL = "SELECT * FROM client";
        Statement statement = jdbcTemplate.getDataSource().getConnection().createStatement();

        List<Client> result = new ArrayList<>();

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
        return result;
    }

    public String deleteClientByID(int id) {
        String SQL_DELETE_BY_ID = "DELETE FROM client WHERE id=" + id;
        try {
            Statement statement = jdbcTemplate.getDataSource().getConnection().createStatement();
            statement.execute(SQL_DELETE_BY_ID);
        } catch (SQLException e) {

            System.out.println("Метод deleteClient поломался...");
            e.printStackTrace();
        }
        return "Клиент c id: " + id + " успешно удален!";
    }

    public String addClient(Client client) {
        final String SQL_INSERT_CLIENT =
                "insert into CLIENT (name, login, password, email, type) values (?,?,?,?,?)";

        jdbcTemplate.update(SQL_INSERT_CLIENT,
                client.getName(),
                client.getLogin(),
                client.getPassword(),
                client.getEmail(),
                client.getType()
        );

        return "Клиент " + client.getName() + " успешно добавлен!";
    }

    public String setClientByID(Client client, int id) {
        final String SQL_UPDATE_CLIENT =
                "update CLIENT set name=?, login=?, password=?, email=?, type=? where id=" + id;

        jdbcTemplate.update(SQL_UPDATE_CLIENT,
                client.getName(),
                client.getLogin(),
                client.getPassword(),
                client.getEmail(),
                client.getType()
        );

        return "Клиент " + client.getName() + " успешно изменен!";
    }
}
