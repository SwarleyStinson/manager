package ru.stepanov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.stepanov.entity.Client;
import ru.stepanov.mapper.ClientService;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClientDAO {
    private JdbcTemplate jdbcTemplate;
    private int tableSize;
    private int currentPage;
    private int maxPage;

    @Autowired
    public ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        tableSize = getAll().size();
        currentPage = getCurrentPageNumber(4);
        maxPage = currentPage;
    }

    public List<Client> getAll() {
        ClientService clientService = new ClientService();
        System.out.println("-----------: MyBatis: getAll()");
        return clientService.getAll();

//        String SQL_GET_ALL = "SELECT * FROM client";
//        Statement statement;
//        List<Client> result = null;
//
//        try {
//            statement = jdbcTemplate.getDataSource().getConnection().createStatement();
//
//            result = new ArrayList<Client>();
//
//            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
//
//            while (resultSet.next()) {
//                Client client = new Client();
//
//                client.setId(resultSet.getInt("id"));
//                client.setName(resultSet.getString("name"));
//                client.setLogin(resultSet.getString("login"));
//                client.setPassword(resultSet.getString("password"));
//                client.setEmail(resultSet.getString("email"));
//                client.setType(resultSet.getString("type"));
//                result.add(client);
//            }
//            statement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
    }

    public int getCurrentPageNumber(int changePageCommand) {
        if (changePageCommand == 1) currentPage = 1;
        if (changePageCommand == 2)
            if (currentPage > 1) --currentPage;
        if (changePageCommand == 3)
            if (currentPage < maxPage) ++currentPage;
        if (changePageCommand == 4) {
            if (tableSize % 10 != 0) {
                currentPage = tableSize / 10 + 1;
            } else currentPage = tableSize / 10;
        }
        return currentPage;
    }

    public List<Client> getPage(int currentPageNumber) {
        List<Client> result = getAll();

        //удаление с начала до текущей страницы
        int count = 1;
        while (count < currentPage) {
            for (int i = 0; i < 10; i++) result.remove(0);
            count++;
        }
        //удаление до конца
        int cycleEnd = result.size() - 1;

        for (int i = cycleEnd; i >= 10; i--)
            result.remove(i);

        return result;
    }

    public List<Client> getLast() {
        List<Client> result = getAll();

        int count = 0;
        int cycleEnd = result.size();

        while (count <= cycleEnd - 11) {
            result.remove(0);
            count++;
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
        maxPage = getCurrentPageNumber(4);
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
        maxPage = getCurrentPageNumber(4);
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
