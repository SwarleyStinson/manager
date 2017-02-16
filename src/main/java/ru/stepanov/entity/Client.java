package ru.stepanov.entity;

import java.io.Serializable;

public class Client implements Serializable {

    private static final long serialVersionUID = 6324496442814639361L;

    private int id;
    private String name;
    private String login;
    private String password;
    private String email;
    private String type;

    public Client(int id, String name, String login, String password, String email, String type) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='***'" +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
