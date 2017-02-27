package ru.stepanov.db.domain;

import java.io.Serializable;

public class Bank implements Serializable {

    private static final long serialVersionUID = -1202107502710444636L;

    private int id;
    private String name;
    private String address;
    private String paySystem;

    public Bank(int id, String name, String address, String paySystem) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.paySystem = paySystem;
    }

    public Bank() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaySystem() {
        return paySystem;
    }

    public void setPaySystem(String paySystem) {
        this.paySystem = paySystem;
    }
}
