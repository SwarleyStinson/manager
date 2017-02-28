package ru.stepanov.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.stepanov.db.AbstractMyBatisDao;
import ru.stepanov.db.domain.Client;
import ru.stepanov.db.mapper.ClientService;

import java.sql.SQLException;
import java.util.List;

public class ClientDAO extends AbstractMyBatisDao{
    private JdbcTemplate jdbcTemplate;
    private int tableSize;
    private int currentPage;
    private int maxPage;
    private ClientService clientService;


    @Autowired
    public ClientDAO(JdbcTemplate jdbcTemplate, ClientService clientService) {
        this.jdbcTemplate = jdbcTemplate;
        this.clientService = clientService;
        tableSize = getAll().size();
        currentPage = getCurrentPageNumber(4);
        maxPage = currentPage;
    }

    public List<Client> getAll() {

//        return this.sqlSession.selectList("getAll");

       return clientService.getAll();
    }

    public void deleteByID(int id) throws ClassNotFoundException, SQLException {
        clientService.deleteById(id);
        tableSize--;
        maxPage = getCurrentPageNumber(4);
    }

    public void addClient(Client client) {
        clientService.insertClient(client);
        tableSize++;
        maxPage = getCurrentPageNumber(4);
    }

    public void updateClient(Client client) {

        clientService.updateById(client);
    }

    public int getCurrentPage(){
        return currentPage;
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
}
