package com.company;

public class CT extends Person {

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    boolean knife = true;
    boolean pistol = true;
    boolean grenade = false;
    boolean diffuse = true;
    int health = GetHealth();
    int speed = GetSpeed();
    String gunname;

    CT(){

    }

    CT(String name, String gunname){
        SetName(name);
        this.gunname = gunname;
        System.out.println(ANSI_BLUE + "Вы выбрали персонажа Counter-Terrorist: \nВаше имя: " + GetName() + "\nВаше здоровье = " + health + "\nСкорость = " + speed + "\nОружие: " + gunname);
        System.out.println("Так же в наличии есть: ");
        if(knife == true)
            System.out.println("Нож");
        if(pistol == true)
            System.out.println("Пистолет");
        if(grenade == true)
            System.out.println("Гранаты");
        if(diffuse == true)
            System.out.println("Щипцы" + ANSI_RESET);
    }
    public String GetCT(){
        return gunname;
    }
}
