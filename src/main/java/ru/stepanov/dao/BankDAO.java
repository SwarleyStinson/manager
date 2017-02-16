package ru.stepanov.dao;

public class BankDAO {

    public String addBank(String name, String address, String paySystem) {

        return "Банк " + name + " успешно добавлен!";
    }

    public String deleteBank(String name) {

        return "Банк " + name + " успешно удален!";
    }

    public String setBank(String name) {

        return "Сведения о банке" + name + " успешно изменены!";
    }
}
