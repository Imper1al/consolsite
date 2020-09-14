package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Control {

    DBHandler dbHandler = new DBHandler();
    Scanner sc = new Scanner(System.in);
    Orders orders = new Orders();
    private boolean process = true;
    private boolean login = true;
    private String loginName;

    public Control() {

    }

    private void setUser(){ //Регистрация
        System.out.print("Веедите логин: ");
        String login = sc.nextLine();
        if(dbHandler.getLogin(login) == 0){ //Проверка на уникальность
            System.out.print("Веедите пароль: ");
            String pass = sc.nextLine();
            User user = new User(login, pass);
            dbHandler.singUP(user);
            System.out.println("Регистрация завершена!");
        }
        else
            System.out.println("Такой логин уже существует!");
    }

    private void loginUser(){ //Вход в аккаунт
        System.out.print("Логин: ");
        loginName = sc.nextLine();
        System.out.print("Пароль: ");
        String loginPass = sc.nextLine();
        DBHandler dbHandler = new DBHandler();
        User user = new User();
        user.setUserName(loginName);
        user.setPassword(loginPass);
        ResultSet result = dbHandler.getUser(user); //Проверка логина и пароля

        int couter = 0;

        while(true){
            try {
                if (!result.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            couter++;
        }

        if(couter >= 1){
            System.out.println("Вход успешно выполнен!");
            login = false;
            process = true;
            System.out.println("Добро пожаловать в магазин IMPERIALTV!\n");
            Process();
        }
        else
            System.out.println("Неверный логин или пароль!");
    }

    private void getProducts(){ //Вывод всех существующих категорий товаров
        System.out.println("\nВсе существующие категории: ");
        dbHandler.getCategory();
        System.out.println("\nВведите одну из перечисленных категорий: " +
                "\nВыйти в ГЛАВНОЕ МЕНЮ - введите \"/exit\"");
        String category = sc.nextLine();
        if (category == "/exit"){
            return;
        }
        else {
            dbHandler.getProduct(category); //Вывод товаров по заданной категории
            toOrders();
        }

    }

    private void setProduct(){ //Добавление товара
        System.out.print("Введите категорию продукта: ");
        String category = sc.nextLine();
        System.out.print("Введите название продукта: ");
        String title = sc.nextLine();
        System.out.print("Введите цену продукта: ");
        int price = 0;
        String notprice;
        if(sc.hasNextInt()) {
            price = Integer.parseInt(sc.nextLine());
        }else {
            notprice = sc.nextLine();
            System.out.println("Такой цены не может быть! Попробуйте в другой раз!");
            return;
        }
        System.out.println("Введите описание продукта:" );
        String description = sc.nextLine();
        Product product = new Product(category, title, price, description);
        dbHandler.createProduct(product);
        System.out.println("Товар опубликован!");
    }


    private void myProducts(){ // Вывод всех опубликованых вами товаров
        System.out.println("Ваши опубликованые товары: \n");
        dbHandler.getMyProducts();
        System.out.println("Доступные команды:\n\"/create_product\" - создать новое обьявление!\n\"/delete_product\" - удалить товар!"
                + "\nНажмите ENTER чтобы выйти в главное меню!");
        String command = sc.nextLine();
        if(command.equals("/create_product")){
            setProduct(); // добавление нового товара
        }else if(command.equals("/delete_product")){
            System.out.println("Введите ID вашего продукта: ");
            if(sc.hasNextInt()){
                dbHandler.deleteProduct(Integer.parseInt(sc.nextLine())); // удаление товара
            }else {
                System.out.println("Ошибка ввода! Попробуйте еще раз!");
                String com = sc.nextLine();
                return;
            }
        }else return;
    }

    private void getComment(){ //Вывод всех отзывов
        System.out.println();
        dbHandler.getComments();
    }

    private void setComment(){ //Добавление отзыва
        System.out.print("Оцените сайт от 1 до 5: ");
        int rating = 0;
        String notrating;
        if(sc.hasNextInt()) {
            rating = Integer.parseInt(sc.nextLine());
            while (rating <= 0 || rating > 5) {
                if (rating <= 0 || rating > 5) {
                    System.out.println("Не верно введина оценка! Повторите попытку:");
                    rating = Integer.parseInt(sc.nextLine());
                } else return;
            }
        }else {
            notrating = sc.nextLine();
            System.out.println("Оценка введена не  верно! Попробуйте в другой раз!");
            return;
        }
        System.out.println("Напишите отзыв о нас:");
        String comment = sc.nextLine();
        Comments comments = new Comments(rating, comment);
        dbHandler.createComment(comments);
        System.out.println("Отзыв опубликован!");
    }

    private void myComments(){ // вывод ваших отзывов о сайте
        System.out.println("Ваши опубликованые отзывы: \n");
        dbHandler.getMyComments();
        System.out.println("Доступные команды:\n\"/create_comment\" - создать новый отзыв!\n\"/delete_comment\" - удалить отзыв!"
                + "\nНажмите ENTER чтобы выйти в главное меню!");
        String command = sc.nextLine();
        if(command.equals("/create_comment")){
            setComment(); // добавление отзыва
        }else if(command.equals("/delete_comment")){
            deleteComment(); // удаление отзыва
        }else return;
    }

    private void deleteComment(){ //Удалить комментарий
        System.out.println("Чтобы удалить ваш отзыв, введите его номер: ");
        int commentNum;
        String comment;
        if(sc.hasNextInt()) {
            commentNum = Integer.parseInt(sc.nextLine());
            dbHandler.deleteComment(commentNum);
        }else{
            comment = sc.nextLine();
            System.out.println("Не верно указан номер отзыва");
        }
    }

    private void AddMoney(){ //Пополнение счета
        System.out.println("Введите сумму пополнения: ");
        int money = 0;
        String notmoney;
        if(sc.hasNextInt()) {
            money = Integer.parseInt(sc.nextLine());
            if (money > 0) {
                dbHandler.AddMoney(money);
                System.out.println("Операция прошла успешно!\n");
                dbHandler.infoUser(loginName).toString();
            } else if (money < 0) {
                System.out.println("Сумма не коректна!");
            }
        }
        else {
            notmoney = sc.nextLine();
            System.out.println("Что-то пошло не так!");
        }
    }

    private void toOrders(){ //Добавление товара в корзину

        System.out.println("Добавить товар в корзину - \"/to_order\"" + "\n/// Чтобы выйти в основное меню нажмите ENTER");
        String command = sc.nextLine();
        if(command.equals("/to_order")){
            System.out.println("Введите номер товара: ");
            orders.setProduct_id(Integer.parseInt(sc.nextLine()));
            dbHandler.getProductID(orders.getProduct_id());
        }

    }

    private void Orders(){ // Вывод корзины
        System.out.println("Корзина: \n");
        orders.setSum(0);
        dbHandler.getOrderID();
        System.out.println("\nДоступные команды:\n\"/delete\" - удалить продукт из корзины\n\"/pay\" - оплатить заказ"
                + "\nНажмите ENTER чтобы выйти в главное меню!");
        String command = sc.nextLine();
        if(command.equals("/delete")){
            System.out.println("Введите номер продукта, который хотите удалить: ");
            dbHandler.deleteOrder(Integer.parseInt(sc.nextLine())); // удаление товара из корзины
        }else if(command.equals("/pay")){
            dbHandler.Pay(); // Оплата товаров в корзине
        }else return;
    }

    private void commands(){ //Список команд
        System.out.println("Возможные команды:\n/main - профиль!" +
                "\n/get_comments - посмотреть все отзывы о сайте!" +
                "\n/get_products - посмотреть категории товаров и сами товары!" +
                "\n/my_comments - посмотреть мои отзывы!" +
                "\n/my_products - посмотреть мои опубликованые товары!" +
                "\n/add_money - пополнить счет!" +
                "\n/orders - корзина!" +
                "\n/exit - выйти из аккаунта!");
    }

    public void RegLog(){ //МЕНЮ #1 - регистрация и авторизация

        System.out.println("Добро пожаловать в магазин IMPERIALTV!" +
                "\nЧтобы воспользоваться сайтом, Вам необходимо зарегистрироваться и войти!" +
                "\nДоступные команды: \n/registration - регистрация\n/login - войти в аккаунт");
        do{
            System.out.println("Введите команду: ");
            switch (sc.nextLine()){
                case "/registration":
                    setUser();
                    break;
                case "/login":
                    loginUser();
                    break;
                default:
                    System.out.println("Команда не найдена!");
            }
        }while (login == true);

    }

    public void Process(){ //МЕНЮ #2 - основное меню сайта
        dbHandler.infoUser(loginName).toString();
        do{
            commands();
            System.out.println("Введите команду: ");
            switch (sc.nextLine()){
                case "/main":
                    dbHandler.infoUser(loginName).toString();
                    break;
                case "/get_comments":
                    getComment();
                    break;
                case "/my_comments":
                    myComments();
                    break;
                case "/get_products":
                    getProducts();
                    break;
                case "/my_products":
                    myProducts();
                    break;
                case "/add_money":
                    AddMoney();
                    break;
                case "/orders":
                    Orders();
                    break;
                case "/exit":
                    process = false;
                    login = true;
                    RegLog();
                    System.out.println("Вы вышли из сайта");
                    break;
                default:
                    System.out.println("Команда не найдена!");
            }
        }while (process == true);
    }

    public void Start(){ // Запуск программы
        RegLog();
    }

}
