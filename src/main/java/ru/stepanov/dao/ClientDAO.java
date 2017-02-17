package ru.stepanov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.stepanov.entity.Client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public List<Client> getAll() throws SQLException {
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

    public void setToDefaultState() throws IOException {
        List<String> lines = null;
        List<String> lines2 = null;
        try {
            lines = Files.readAllLines(Paths.get("C:/Users/p.stepanov", "sql/schema.sql"));
            lines2 = Files.readAllLines(Paths.get("C:/Users/p.stepanov", "sql/data.sql"));
        } catch (IOException e) {
            System.out.println("-------: Files поломался");
        }

        try {
            Statement statement = jdbcTemplate.getDataSource().getConnection().createStatement();
            int count = 0;
            StringBuilder query = null;
            while (count <= lines.size()) {
                query.append(lines.get(count));
                count++;
            }
            statement.execute(query.toString());

            count = 0;
            query.delete(0, query.length());

            System.out.println("--------: " + query.toString());

            while (count <= lines2.size()) {
                query.append(lines.get(count));
                count++;
            }
            statement.execute(query.toString());

        } catch (SQLException e) {

            System.out.println("Метод setToDefaultState поломался...");
            e.printStackTrace();
        }
    }

    public void deleteClientByID(int id) {
        String SQL_DELETE_BY_ID = "DELETE FROM client WHERE id=" + id;
        try {
            Statement statement = jdbcTemplate.getDataSource().getConnection().createStatement();
            statement.execute(SQL_DELETE_BY_ID);
        } catch (SQLException e) {

            System.out.println("Метод deleteClient поломался...");
            e.printStackTrace();
        }
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
