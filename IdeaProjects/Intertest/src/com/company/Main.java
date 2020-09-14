package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		InterArray a = new Array();
		System.out.println("Попробуй угадать одно из 3-х чисел (от 1 до 10)");
		int x = in.nextInt();
		int[] mass = new int[3];
		for (int i = 0; i < mass.length; i++) {
			mass[i] = a.Add();
			System.out.print(a.Get(i) + " ");
		}
		System.out.println();
		if (x != a.Get(0) && x != a.Get(1) && x != a.Get(2) && x >= 0 && x <= 10) {
			System.out.println("Вы не угадали :(");
		} else if (x == a.Get(0) || x == a.Get(1) || x == a.Get(2))
			System.out.println("Вы угадали!!!");
		else
			System.out.println("Вы ввели цыфру не от 0 до 10");
	}
}
