package ru.stepanov.dao;

import java.util.Date;

public class OrderDAO {
    public String addOrder(int number, String firstContrAgent, String firstContrAgentType,
                           String secondContrAgent, String secondContrAgentType, Date dateOfStart, Date dateOfFinish) {

        return "Заказ успешно добавлен!";
    }

    public String deleteOrder(int number) {

        return "Заказ с номером " + number + " удален!";
    }
}
