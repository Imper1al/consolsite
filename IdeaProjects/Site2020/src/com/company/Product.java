package com.company;

public class Product {

    private int id;
    private String category;
    private String title;
    private int price;
    private String description;
    private String date_time;
    private String creator_name;

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public Product() {
    }

    public Product(String category, String title, int price, String description) {
        this.category = category;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Номер товара: " + id +
                "\nПродавец: " + creator_name +
                "\nНазвание: " + title +
                "\nЦена: " + price +
                "\nОписание: " + description +
                "\nДата добавления: " + date_time;
    }

    public String toString2(){
        return "Номер товара: " + id +
                "\nПродавец: " + creator_name +
                "\nКатегория: " + category +
                "\nНазвание: " + title +
                "\nЦена: " + price +
                "\nОписание: " + description +
                "\nДата добавления: " + date_time;
    }
}
