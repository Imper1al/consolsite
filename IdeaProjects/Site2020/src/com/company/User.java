package com.company;

public class User {

    private int id;
    private String userName;
    private String password;
    private int money;
    private String date_reg;

    @Override
    public String toString() {
        return "Профиль: " +
                "\nID: " + id +
                "\nЛогин: " + userName +
                "\nСчет: " + money +
                "\nДата регистрации: " + date_reg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_reg() {
        return date_reg;
    }

    public void setDate_reg(String date_reg) {
        this.date_reg = date_reg;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
