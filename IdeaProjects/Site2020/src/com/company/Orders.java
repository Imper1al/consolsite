package com.company;

public class Orders {

    private int order_id;
    private int user_id;
    private int product_id;
    private int sum;

    public int getSum() {
        return sum;
    }

    public void mixSum(int sum) {
        this.sum += sum;
    }

    public void setSum(int sum){
        this.sum = sum;
    }

    public Orders() {
    }


    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Сумма к оплате: " + sum;
    }
}
