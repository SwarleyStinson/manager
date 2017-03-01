package ru.stepanov.db.domain;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{

    private static final long serialVersionUID = 6324456442814635239L;

    private int id;
    private String firstcontragent;
    private String secondcontragent;
    private Date dateofstart;
    private Date dateoffinish;
    private String type;

    public Order() {
    }

    public Order(int id, String firstcontragent, String secondcontragent, Date dateofstart, Date dateoffinish, String type) {
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

    public void setFirstContrAgent(String firstcontragent) {
        this.firstcontragent = firstcontragent;
    }

    public String getSecondcontragent() {
        return secondcontragent;
    }

    public void setSecondcontragent(String secondcontragent) {
        this.secondcontragent = secondcontragent;
    }

    public Date getDateofstart() {
        return dateofstart;
    }

    public void setDateofstart(Date dateofstart) {
        this.dateofstart = dateofstart;
    }

    public Date getDateoffinish() {
        return dateoffinish;
    }

    public void setDateoffinish(Date dateoffinish) {
        this.dateoffinish = dateoffinish;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
