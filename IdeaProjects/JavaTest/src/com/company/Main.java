package com.company;
import java.lang.String;
import java.util.Scanner;
import java.util.Random;


public class Main {

    public static void main(String[] args) {
        Student firststudent = new Student("vlad", 93, 179, 6);
        firststudent.tell();
        Person man = new Person();
        System.out.println("Стандартные значения: \n" + man.name +"\n"+ man.weight +"\n"+ man.height);
    }
}
