package ru.stepanov.db.dao.OldDaoWithResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class OrderDAO {
    private JdbcTemplate jdbcTemplate;
    private int tableSize;
    private int currentPage;
    private int maxPage;

    @Autowired
    public OrderDAO() {
        this.jdbcTemplate = jdbcTemplate;
        this.tableSize = tableSize;
        this.currentPage = currentPage;
        this.maxPage = maxPage;
    }

    public OrderDAO(JdbcTemplate jdbcTemplate) {
    }

    public void getCurrentPageNumber() {

    }

    public void getPage() {

    }

    public void getLast() {

    }

    public void getAll() {

    }

    public void setOrderByID() {

    }

    public void addOrder(int number, String firstContrAgent, String firstContrAgentType,
                         String secondContrAgent, String secondContrAgentType, Date dateOfStart, Date dateOfFinish) {


    }

    public void deleteOrderByID(int number) {


    }
}
