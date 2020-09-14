package com.company;

public class Comments {

    private int id_comments;
    private int rating;
    private String comment;
    private String date_time;
    private String creator_name;

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public Comments() {
    }

    public Comments(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public Comments(int id_comments, int rating, String comment) {
        this.id_comments = id_comments;
        this.rating = rating;
        this.comment = comment;
    }

    public int getId_comments() {
        return id_comments;
    }

    public void setId_comments(int id_comments) {
        this.id_comments = id_comments;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Отзыв #" + id_comments +
                ":\nПользователь: " + creator_name +
                "\nОценка: " + rating +
                "\nКомментарий: " + comment +
                "\nДата добавления: " + date_time;

    }
}
