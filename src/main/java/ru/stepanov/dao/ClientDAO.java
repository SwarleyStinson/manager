package ru.stepanov.dao;

import ru.stepanov.entity.TypeOfClient;

public class ClientDAO {
    public String addClient (String name, String password, String email, TypeOfClient type){

        return "Клиент " + name + " успешно добавлен!";
    }

    public String deleteClient (String name){

        return "Клиент " + name + " успешно удален!";
    }

    public String setClientName(String name){

        return "Имя клиента успешно изменено!";
    }
    public String setClientPassword(String password){

        return "Пароль клиента успешно изменено!";
    }
    public String setClientEmail(String email){

        return "E-mail клиента успешно изменено!";
    }
    public String setClientType(TypeOfClient type){

        return "Ну и как вы себе это представляете? )))";
    }
}
