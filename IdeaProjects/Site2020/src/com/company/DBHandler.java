package com.company;

import java.sql.*;

public class DBHandler extends Configs{

    Connection dbConnection;
    User user = new User();
    Orders orders = new Orders();

    public Connection getDbConnection() throws ClassNotFoundException, SQLException { //Конект в БД
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName+"?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public int getLogin(String login){ //Проверка на уникальность логина

        ResultSet resultSet = null;
        int stat = 0;

        String select = "SELECT COUNT(*) FROM " + Const.USER_TABLE + " WHERE " + Const.USER_NAME
        + "='" + login + "'";

        try {

            PreparedStatement psDb = getDbConnection().prepareStatement(select);
            resultSet = psDb.executeQuery(select);
            while(resultSet.next()) {
                stat = resultSet.getInt(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return stat;

    }

    public void singUP(User user){ //Регистрация пользователя

        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_NAME + "," + Const.USER_PASS + ")" +
                "VALUES(?,?)";

        try {
            PreparedStatement psDb = getDbConnection().prepareStatement(insert);
            psDb.setString(1, user.getUserName());
            psDb.setString(2, user.getPassword());

            psDb.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createProduct(Product product){ //Добавление продукта

        String insert = "INSERT INTO " + Const.PRODUCTS_TABLE + "(" + Const.PRODUCTS_CATEGORY + ","
                + Const.PRODUCTS_TITLE + "," + Const.PRODUCTS_PRICE + "," + Const.PRODUCTS_DESCRIPTION
                + "," + Const.PRODUCTS_USER_NAME + ")" +
                "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement psDb = getDbConnection().prepareStatement(insert);
            psDb.setString(1, product.getCategory());
            psDb.setString(2, product.getTitle());
            psDb.setInt(3, product.getPrice());
            psDb.setString(4, product.getDescription());
            psDb.setString(5, user.getUserName());

            psDb.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void createComment(Comments comments){ //Добавление комментария

        String insert = "INSERT INTO " + Const.COMMENTS_TABLE + "(" + Const.COMMENTS_RATING + ","
                + Const.COMMENTS_COMMENT + "," +  Const.COMMENTS_USER_NAME + ")" +
                "VALUES(?,?,?)";

        try {
            PreparedStatement psDb = getDbConnection().prepareStatement(insert);
            psDb.setInt(1, comments.getRating());
            psDb.setString(2, comments.getComment());
            psDb.setString(3, user.getUserName());

            psDb.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getUser(User user){ //Проверка на наличие пользователя при входе

        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE "
                + Const.USER_NAME + "=? AND " + Const.USER_PASS + "=?";

        try {
            PreparedStatement psDb = getDbConnection().prepareStatement(select);
            psDb.setString(1, user.getUserName());
            psDb.setString(2, user.getPassword());

            resultSet = psDb.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet infoUser(String userName){ //Вывод информации о пользователе

        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE "
                + Const.USER_NAME + "=?";

        try {
            PreparedStatement psDb = getDbConnection().prepareStatement(select);
            psDb.setString(1, userName);
            resultSet = psDb.executeQuery();

            while (resultSet.next()){
                user.setId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                user.setMoney(resultSet.getInt(4));
                user.setDate_reg(resultSet.getString(5));
                System.out.println(user + "\n");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void AddMoney(int money){ //Пополнение счета

        String update = "UPDATE " + Const.USER_TABLE + " SET "
                + Const.USER_MONEY + "=?" + " WHERE " + Const.USER_NAME + "=?";


        try {
            PreparedStatement psDb = getDbConnection().prepareStatement(update);
            psDb.setString(2, user.getUserName());
            psDb.setInt(1, user.getMoney() + money);
            psDb.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getComments(){ //Вывод комментариев

        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.COMMENTS_TABLE;

        try {
            PreparedStatement psDb = getDbConnection().prepareStatement(select);
            resultSet = psDb.executeQuery();

            while (resultSet.next()){
                Comments comments = new Comments();
                comments.setId_comments(resultSet.getInt(1));
                comments.setRating(resultSet.getInt(2));
                comments.setComment(resultSet.getString(3));
                comments.setDate_time(resultSet.getString(4));
                comments.setCreator_name(resultSet.getString(5));
                System.out.println(comments + "\n");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getCategory(){ //Вывод категорий товаров

        ResultSet resultSet = null;
        String select = "SELECT DISTINCT " + Const.PRODUCTS_CATEGORY + " FROM " + Const.PRODUCTS_TABLE;

        try {
            PreparedStatement psDb = getDbConnection().prepareStatement(select);
            resultSet = psDb.executeQuery();

            while (resultSet.next()){
                Product product = new Product();
                product.setCategory(resultSet.getString(1));
                System.out.println(product.getCategory());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getProduct(String category){ //Вывод всех товаров из данной категории

        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.PRODUCTS_TABLE + " WHERE " + Const.PRODUCTS_CATEGORY + "=?";
        try {
            PreparedStatement psDb = getDbConnection().prepareStatement(select);
            psDb.setString(1, category);
            resultSet = psDb.executeQuery();

            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setTitle(resultSet.getString(3));
                product.setPrice(resultSet.getInt(4));
                product.setDescription(resultSet.getString(5));
                product.setDate_time(resultSet.getString(6));
                product.setCreator_name(resultSet.getString(7));
                System.out.println(product + "\n");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getProductID(int id_product){ //Проверка на наличия товара с введенным ID

        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.PRODUCTS_TABLE + " WHERE " + Const.PRODUCTS_ID + "=?";
        try {
            PreparedStatement psDb = getDbConnection().prepareStatement(select);
            psDb.setInt(1, id_product);
            resultSet = psDb.executeQuery();
            Product product = new Product();

            while (resultSet.next()){
                product.setId(resultSet.getInt(1));
            }
            if(product.getId() == 0){
                System.out.println("Товар не найден!");
                return;
            }else {
                setOrders(user.getId(), id_product);
                System.out.println("Товар добавлен в корзину!");
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setOrders(int user_id, int product_id){ // Запись в БД в таблицу order, ID пользователя и ID товара
        String insert = "INSERT INTO " + Const.ORDERS_TABLE + "(" + Const.ORDERS_USER_ID + ","
                + Const.ORDERS_PRODUCT_ID  + ")" +
                "VALUES(?,?)";

        try {
            PreparedStatement psDb = getDbConnection().prepareStatement(insert);
            psDb.setInt(1, user_id);
            psDb.setInt(2, product_id);

            psDb.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getOrder(){ //Выводим корзину

        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.PRODUCTS_TABLE + " WHERE " + Const.PRODUCTS_ID + "=?";
        try {
            PreparedStatement psDb = getDbConnection().prepareStatement(select);
            psDb.setInt(1, orders.getProduct_id());
            resultSet = psDb.executeQuery();

            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setTitle(resultSet.getString(3));
                product.setPrice(resultSet.getInt(4));
                orders.mixSum(resultSet.getInt(4));
                product.setDescription(resultSet.getString(5));
                product.setDate_time(resultSet.getString(6));
                product.setCreator_name(resultSet.getString(7));
                System.out.println(product + "\n");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getOrderID(){ //Берем с корзины все ID продуктов данного пользователя и выводим ее методом GetOrders

        ResultSet resultSet = null;
        orders.setSum(0);
        String select = "SELECT " + Const.ORDERS_PRODUCT_ID + " FROM " + Const.ORDERS_TABLE + " WHERE " + Const.ORDERS_USER_ID + "=?";
        try {
            PreparedStatement psDb = getDbConnection().prepareStatement(select);
            psDb.setInt(1, user.getId());
            resultSet = psDb.executeQuery();

            while (resultSet.next()){
                orders.setProduct_id(resultSet.getInt(1));;
                getOrder();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Сумма к оплате: " + orders.getSum());
    }

    public void deleteOrder(int productID){ //Удалить товар с корзины

        String delete = "DELETE FROM " + Const.ORDERS_TABLE + " WHERE " + Const.ORDERS_USER_ID + "=" + user.getId() + " AND "
                + Const.ORDERS_PRODUCT_ID + "=" + productID ;
        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(delete);
            System.out.println("Указаный товар удален!");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void Pay(){ //Оплата корзины

        String delete = "DELETE FROM " + Const.ORDERS_TABLE + " WHERE " + Const.ORDERS_USER_ID + "=" + user.getId();
        try {
            Statement statement = dbConnection.createStatement();
            if(user.getMoney() - orders.getSum() >= 0) {
                AddMoney(-orders.getSum());
                System.out.println("Оплата прошла успешно! Ожидайте доставку!");
                statement.executeUpdate(delete);
            }else
                System.out.println("Не хватает денег для оплаты! Пожалуйста пополните счет!");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteComment(int commentID){ //Удалить комментарий

        String delete = "DELETE FROM " + Const.COMMENTS_TABLE + " WHERE " + Const.COMMENTS_ID + "=" + commentID
                + " AND " + Const.COMMENTS_USER_NAME + "='" + user.getUserName() + "'";;

        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(delete);
            System.out.println("Комментарий удален!");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getMyProducts(){ //Вывод продаваемых товаров

        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.PRODUCTS_TABLE + " WHERE " + Const.PRODUCTS_USER_NAME + "=?";

        try {
            PreparedStatement psDb = getDbConnection().prepareStatement(select);
            psDb.setString(1, user.getUserName());
            resultSet = psDb.executeQuery();

            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setCategory(resultSet.getString(2));
                product.setTitle(resultSet.getString(3));
                product.setPrice(resultSet.getInt(4));
                product.setDescription(resultSet.getString(5));
                product.setDate_time(resultSet.getString(6));
                product.setCreator_name(resultSet.getString(7));
                System.out.println(product.toString() + "\n");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productID){ //Удалить товар

        String delete = "DELETE FROM " + Const.PRODUCTS_TABLE + " WHERE " + Const.PRODUCTS_ID + "=" + productID
                + " AND " + Const.PRODUCTS_USER_NAME + "='" + user.getUserName() + "'";

        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(delete);
            System.out.println("Товар удален!");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getMyComments(){ //Вывод личных отзывов

        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.COMMENTS_TABLE + " WHERE " + Const.COMMENTS_USER_NAME + "=?";

        try {
            PreparedStatement psDb = getDbConnection().prepareStatement(select);
            psDb.setString(1, user.getUserName());
            resultSet = psDb.executeQuery();

            while (resultSet.next()){
                Comments comments = new Comments();
                comments.setId_comments(resultSet.getInt(1));
                comments.setRating(resultSet.getInt(2));
                comments.setComment(resultSet.getString(3));
                comments.setDate_time(resultSet.getString(4));
                comments.setCreator_name(resultSet.getString(5));
                System.out.println(comments.toString() + "\n");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
