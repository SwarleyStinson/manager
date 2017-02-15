package ru.stepanov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.stepanov.entity.Client;

@Component
public class ClientDAO {
    private static final String SQL_INSERT_CLIENT =
            "insert into CLIENT (id, name, login, password, email, type) values (?,?,?,?,?)";
    private static final String SQL_UPDATE_CLIENT =
            "update CLIENT (id, name, login, password, email, type) values (?,?,?,?,?)";


    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

//    @Transactional
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

    @Transactional
    public String deleteClient(String name) {

        return "Клиент " + name + " успешно удален!";
    }

    @Transactional
    public String setClient(Client client) {


        return "Клиент " + client.getName() + " успешно изменен!";
    }
}
