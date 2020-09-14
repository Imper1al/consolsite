package com.company;

import java.util.Scanner;

public class Registration{
    String gunnameCT;
    String gunnameTR;
    String firstName;
    String secondName;
    public void Reg(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Первый игрок делает выбор!\nCT - 1\nTR - 2");
        System.out.println();
        switch (sc.nextInt()) {
            case 1:
                System.out.println("Отличный выбор! Второй игрок продолжает за TR\nВведи ваше имя, затем желаемое оружие");
                CT person1 = new CT(firstName = sc.next(), gunnameCT = sc.next());
                System.out.println("\nИ так, второй игрок у нас за TR :)\nВведи имя и оружие: ");
                TR person2 = new TR(secondName = sc.next(), gunnameTR = sc.next());
                break;
            case 2:
                System.out.println("Отличный выбор! Второй игрок продолжает за CT\nВведи ваше имя, затем желаемое оружие");
                TR person3 = new TR(firstName = sc.next(), gunnameTR = sc.next());
                System.out.println("\nИ так, второй игрок у нас за CT :)\nВведи имя и оружие: ");
                CT person4 = new CT(secondName = sc.next(), gunnameCT = sc.next());
                break;
            default:
                System.out.println("Ты меня обманываешь! Будешь за CT\nВведи ваше имя, затем желаемое оружие");
                CT person5 = new CT(firstName = sc.next(), gunnameCT = sc.next());
                System.out.println("\nИ так, второй игрок у нас за TR :)\nВведи имя и оружие: ");
                TR person6 = new TR(secondName = sc.next(), gunnameTR = sc.next());
        }

    }
    public String GetgunCT(){
        return gunnameCT;
    }
    public String GetgubTR(){
        return gunnameTR;
    }
    public String GetfirstName(){
        return firstName;
    }
    public String GetsecondName(){
        return secondName;
    }

}
