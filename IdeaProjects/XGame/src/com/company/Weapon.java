package com.company;

import java.util.Random;

public class Weapon extends Person{

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    String name;
    int health = GetHealth();
    Random rn = new Random();
    int HitChance;
    int damage;
    boolean gameend = false;

    public synchronized void Hit(String name, String gunname){
        this.name = name;
        while(health > 0 && gameend == false) {
            if(gameend == true)
                return;
            System.out.println(ANSI_GREEN);
            switch (gunname) {
                case "M4":
                    damage = rn.nextInt(20);
                    HitChance = rn.nextInt(100);
                    if (HitChance >= 25) {
                        health -= damage;
                        if(health < 0) {
                            health = 0;
                        }
                        System.out.println(ANSI_GREEN + "Игрок " + name + " нанес " + damage + " урона!" +ANSI_BLUE+ " -->" + ANSI_RED + " У противника осталось: " + health + " HP");
                    }else
                        System.out.println(ANSI_YELLOW + "Игрок " + name + " промахнулся!");
                    break;
                case "AK47":
                    damage = rn.nextInt(30);
                    HitChance = rn.nextInt(100);
                    if (HitChance >= 35) {
                        health -= damage;
                        if(health < 0) {
                            health = 0;
                        }
                        System.out.println(ANSI_GREEN + "Игрок " + name + " нанес " + damage + " урона!" +ANSI_BLUE+ " -->" + ANSI_RED + " У противника осталось: " + health + " HP");
                    }else
                        System.out.println(ANSI_YELLOW + "Игрок " + name + " промахнулся!");
                    break;
                case "AWP":
                    damage = rn.nextInt(100);
                    HitChance = rn.nextInt(100);
                    if (HitChance >= 75) {
                        health -= damage;
                        if(health < 0) {
                            health = 0;
                        }
                        System.out.println(ANSI_GREEN + "Игрок " + name + " нанес " + damage + " урона!" +ANSI_BLUE+ " -->" + ANSI_RED + " У противника осталось: " + health + " HP");
                    }else
                        System.out.println(ANSI_YELLOW + "Игрок " + name + " промахнулся!");
                    break;
                default:
                    System.out.println("Игрок " + name + " взял какой-то " + gunname + ", но забыл взять оружие!");
                    return;
            }
            if(health <= 0 && gameend == false) {
                gameend = true;
            }
        }
    }

    public Weapon(){

    }
}
