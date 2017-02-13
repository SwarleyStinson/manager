package ru.stepanov.entity;

public class Bank {
    private String name;
    private String address;
    private PaySystem[] paySystem;

    public Bank(String name, String address, PaySystem[] paySystem) {
        this.name = name;
        this.address = address;
        this.paySystem = paySystem;
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

    public PaySystem[] getPaySystem() {
        return paySystem;
    }

    public void setPaySystem(PaySystem[] paySystem) {
        this.paySystem = paySystem;
    }
}
