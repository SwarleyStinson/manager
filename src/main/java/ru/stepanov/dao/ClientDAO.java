package ru.stepanov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.stepanov.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }





    public ArrayList<Client> refresh(ArrayList<Client> clients) throws SQLException {

        clients.clear();
        clients = this.getArrayOfClients(clients);
        return clients;
    }

    public ArrayList<Client> getArrayOfClients(ArrayList<Client> clients) throws SQLException {
        String SQL_GET_ALL = "SELECT * FROM client";
        Statement statement = jdbcTemplate.getDataSource().getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
        while (resultSet.next()) {
            Client client = new Client();

            client.setId(resultSet.getInt("id"));
            client.setName(resultSet.getString("name"));
            client.setLogin(resultSet.getString("login"));
            client.setPassword(resultSet.getString("password"));
            client.setEmail(resultSet.getString("email"));
            client.setType(resultSet.getString("type"));

            System.out.println(client);
            clients.add(client);
        }
        statement.close();
        return clients;
    }

    public String deleteClient(String name) {

        return "Клиент " + name + " успешно удален!";
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

    public String setClient(Client client) {
        final String SQL_UPDATE_CLIENT =
                "update CLIENT set name=?, login=?, password=?, email=?, type=? where id=?";

        jdbcTemplate.update(SQL_UPDATE_CLIENT,
                client.getName(),
                client.getLogin(),
                client.getPassword(),
                client.getEmail(),
                client.getType(),
                client.getId()
        );

        return "Клиент " + client.getName() + " успешно изменен!";
    }
}
