package com.company;

public class TR extends Person {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    boolean grenade = true;
    boolean bomb = true;
    boolean pistol = false;
    boolean knife = true;
    int health = GetHealth();
    int speed = GetSpeed();
    String gunname;

    TR(){

    }
    TR(String name, String gunname){
        this.gunname = gunname;
        SetName(name);
        System.out.println(ANSI_RED + "Вы выбрали персонажа Terrorist: \nВаше имя: " + GetName() + "\nВаше здоровье = " + health + "\nСкорость = " + speed + "\nОружие: " + gunname);
        if(grenade == true)
            System.out.println("Гранаты");
        if(bomb == true)
            System.out.println("Бомба");
        if(pistol == true)
            System.out.println("Пистолет");
        if(knife == true)
            System.out.println("Нож" + ANSI_RESET);
    }
    public String GetTR(){
        return gunname;
    }

}
