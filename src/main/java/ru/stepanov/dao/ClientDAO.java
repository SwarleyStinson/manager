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
    private int tableSize;
    private int currentPage ;

    @Autowired
    public ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        tableSize = getAll().size();
        currentPage = 0;
    }

    public int getCurrentPageNumber(int changePageCommand) {
        int result = result = 1;
        if (changePageCommand == 1) {
            result = 1;
            currentPage = 1;
        }
        if (changePageCommand == 2) result = --currentPage;
        if (changePageCommand == 3) result = ++currentPage;
        if (changePageCommand == 4) {
            if (tableSize % 14 != 0) {
                result = tableSize / 14 + 1;
                currentPage = result;
            } else {
                result = tableSize / 14;
                currentPage = result;
            }
        }

        return result;
    }

    public List<Client> getPage(int currentPageNumber) {
        List<Client> result = getAll();

        //удаление с начала до текущей страницы
        int count = 1;
        while (count < currentPage) {
            for (int i = 0; i < 14; i++) result.remove(i);
            count++;
        }
        //удаление до конца
        int cycleEnd = result.size();

        for (int i = cycleEnd; i >= 14; i--) result.remove(i);

        return result;
    }

    public List<Client> getLast14() {
        List<Client> result = getAll();

        int count = 0;
        int cycleEnd = result.size();

        while (count <= cycleEnd - 16) {
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

    public void deleteClientByID(int id) throws ClassNotFoundException, SQLException {
        String SQL_DELETE_BY_ID = "DELETE FROM client WHERE id=" + id;
        try {
            Statement statement = jdbcTemplate.getDataSource().getConnection().createStatement();
            statement.execute(SQL_DELETE_BY_ID);
        } catch (SQLException e) {

            System.out.println("Метод deleteClient поломался...");
            e.printStackTrace();
        }
        tableSize--;
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
        tableSize++;
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
//Альтернативный способ получения connection
//        Class.forName("org.h2.Driver");
//        Connection conn = DriverManager.getConnection("jdbc:h2:~/TestDatabase", "root", "root");
//        Statement statement = conn.createStatement();
//        statement.execute(SQL_DELETE_BY_ID);
//        conn.close();