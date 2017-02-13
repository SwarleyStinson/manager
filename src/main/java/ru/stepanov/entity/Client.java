package ru.stepanov.entity;

public class Client {
    private String name;
    private String password;
    private String email;
    private TypeOfClient type;

    public Client(String name, String password, String email, TypeOfClient type) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
