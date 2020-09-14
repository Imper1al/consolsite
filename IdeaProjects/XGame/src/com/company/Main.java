package com.company;

public class Main {

    public static void main(String[] args) {
        Battle battle = new Battle();
        battle.GetInfo(); //Регистрация игроков и вывод полной информации о сторонах
        battle.Whait(); //Ожидания начала боя (отчет 5 секунд)
        battle.tr1.start(); //Поток стрельбы первого игрока
        battle.tr2.start(); //Поток стрельбы второго игрока
    }
}
