package ru.stepanov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.stepanov.entity.Client;

public class ClientDAO {
    private static final String SQL_INSERT_CLIENT =
            "insert into CLIENT (id, name, login, password, email, type) values (?,?,?,?,?)";
    private static final String SQL_UPDATE_CLIENT =
            "update CLIENT (id, name, login, password, email, type) values (?,?,?,?,?)";


    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    public String addClient(Client client) {

        jdbcTemplate.update(SQL_INSERT_CLIENT,
                client.getId(),
                client.getName(),
                client.getLogin(),
                client.getPassword(),
                client.getEmail(),
                client.getType());

        return "Клиент " + client.getName() + " успешно добавлен!";
    }

    public String deleteClient(String name) {

        return "Клиент " + name + " успешно удален!";
    }

    public String setClient(Client client) {


        return "Клиент " + client.getName() + " успешно изменен!";
    }
}
