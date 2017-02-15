package ru.stepanov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.stepanov.entity.Client;

public class ClientDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_CLIENT =
            "insert into CLIENT (name, login, password, email, type) values (?,?,?,?,?)";

    private static final String SQL_UPDATE_CLIENT =
            "update CLIENT set name=?, login=?, password=?, email=?, type=? where id=?";

       public String addClient(Client client) {

        jdbcTemplate.update(SQL_INSERT_CLIENT,
                client.getName(),
                client.getLogin(),
                client.getPassword(),
                client.getEmail(),
                client.getType()
        );

        return "Клиент " + client.getName() + " успешно добавлен!";
    }

    public String deleteClient(String name) {

        return "Клиент " + name + " успешно удален!";
    }

    public String setClient(Client client) {

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
