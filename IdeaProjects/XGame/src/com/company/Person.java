package com.company;

public class Person {
    private String name;
    private int health = 100;
    private int speed = 5;

    public Person(String name, int health, int speed){
        this.name = name;
        this.health = health;
        this.speed = speed;
    }
    public Person(){

    }
    public String GetName(){
        return name;
    }

    public int GetHealth(){
        return health;
    }

    public int GetSpeed(){
        return speed;
    }

    public void SetName(String name){
        this.name = name;
    }

}
