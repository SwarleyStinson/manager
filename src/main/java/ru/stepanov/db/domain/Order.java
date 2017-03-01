package ru.stepanov.db.domain;

import java.io.Serializable;

public class Order implements Serializable{

    private static final long serialVersionUID = 6324456442814635239L;

    private int id;
    private String firstcontragent;
    private String secondcontragent;
    private String dateofstart;
    private String dateoffinish;
    private String type;

    public Order() {
    }

    public Order(String firstcontragent, String secondcontragent, String dateofstart, String dateoffinish, String type) {
        this.id = id;
        this.firstcontragent = firstcontragent;
        this.secondcontragent = secondcontragent;
        this.dateofstart = dateofstart;
        this.dateoffinish = dateoffinish;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstcontragent() {
        return firstcontragent;
    }

    public void setFirstcontragent(String firstcontragent) {
        this.firstcontragent = firstcontragent;
    }

    public String getSecondcontragent() {
        return secondcontragent;
    }

    public void setSecondcontragent(String secondcontragent) {
        this.secondcontragent = secondcontragent;
    }

    public String getDateofstart() {
        return dateofstart;
    }

    public void setDateofstart(String dateofstart) {
        this.dateofstart = dateofstart;
    }

    public String getDateoffinish() {
        return dateoffinish;
    }

    public void setDateoffinish(String dateoffinish) {
        this.dateoffinish = dateoffinish;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", firstcontragent='" + firstcontragent + '\'' +
                ", secondcontragent='" + secondcontragent + '\'' +
                ", dateofstart='" + dateofstart + '\'' +
                ", dateoffinish='" + dateoffinish + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
