package ru.stepanov.dao;

import ru.stepanov.entity.PaySystem;

public class BankDAO {

    public String addBank(String name, String address, PaySystem[] paySystem) {


        return "Банк " + name + " успешно добавлен!";
    }

    public String deleteBank(String name) {

        return "Банк " + name + " успешно удален!";
    }

    public String setBank (String name){

        return "Сведения о банке" + name + " успешно изменены!";
    }
}
