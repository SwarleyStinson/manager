package ru.stepanov.entity;

import java.util.Date;

public class Order {
    private int number;
    private String firstContrAgent;
    private String firstContrAgentType;
    private String secondContrAgent;
    private String secondContrAgentType;
    private Date dateOfStart;
    private Date dateOfFinish;
    private boolean overdue;


    public Order(int number, String firstContrAgent, String firstContrAgentType, String secondContrAgent, String secondContrAgentType, Date dateOfStart, Date dateOfFinish) {
        this.number = number;
        this.firstContrAgent = firstContrAgent;
        this.firstContrAgentType = firstContrAgentType;
        this.secondContrAgent = secondContrAgent;
        this.secondContrAgentType = secondContrAgentType;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
        this.overdue = false;
    }

    public void setDateOfFinish(Date dateOfFinish) {
        this.dateOfFinish = dateOfFinish;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    public int getNumber() {
        return number;
    }

    public String getFirstContrAgent() {
        return firstContrAgent;
    }

    public String getSecondContrAgent() {
        return secondContrAgent;
    }

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public Date getDateOfFinish() {
        return dateOfFinish;
    }

    public boolean isOverdue() {
        return overdue;
    }
}
