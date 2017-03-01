package ru.stepanov.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.stepanov.db.domain.Bank;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BankDAO {
    private JdbcTemplate jdbcTemplate;
    private int tableSize;
    private int currentPage;
    private int maxPage;

    @Autowired
    public BankDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.tableSize = getAll().size();
        this.currentPage = getCurrentPageNumber(4);
        this.maxPage = currentPage;
    }

    public int getCurrentPage() {
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

    public List<Bank> getPage(int currentPageNumber) {
        List<Bank> result = getAll();

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

    public List<Bank> getLast() {
        List<Bank> result = getAll();

        int count = 0;
        int cycleEnd = result.size();

        while (count <= cycleEnd - 11) {
            result.remove(0);
            count++;
        }
        return result;
    }

    public List<Bank> getAll() {
        String SQL_GET_ALL = "SELECT * FROM bank";
        Statement statement;
        List<Bank> result = new ArrayList<Bank>();

        try {
            statement = jdbcTemplate.getDataSource().getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);

            while (resultSet.next()) {
                Bank bank = new Bank();

                bank.setId(resultSet.getInt("id"));
                bank.setName(resultSet.getString("name"));
                bank.setAddress(resultSet.getString("address"));
                bank.setPaySystem(resultSet.getString("paysystem"));
                result.add(bank);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addBank(Bank bank) {
        final String SQL_INSERT_BANK =
                "insert into BANK (name, address, paysystem) values (?,?,?)";

        jdbcTemplate.update(SQL_INSERT_BANK,
                bank.getName(),
                bank.getAddress(),
                bank.getPaySystem()
        );
        tableSize++;
        maxPage = getCurrentPageNumber(4);
    }

    public void deleteBankByID(int id) {
        String SQL_DELETE_BY_ID = "DELETE FROM bank WHERE id=" + id;
        try {
            Statement statement = jdbcTemplate.getDataSource().getConnection().createStatement();
            statement.execute(SQL_DELETE_BY_ID);
        } catch (SQLException e) {
            System.out.println("Метод deleteBank поломался...");
            e.printStackTrace();
        }
        tableSize--;
        maxPage = getCurrentPageNumber(4);
    }

    public void setBankByID(Bank bank, int id) {
        final String SQL_UPDATE_BANK =
                "update bank set name=?, address=?, paysystem=? where id=" + id;

        jdbcTemplate.update(SQL_UPDATE_BANK,
                bank.getName(),
                bank.getAddress(),
                bank.getPaySystem()
        );
    }
}
