package com.company;
import com.sun.jdi.IncompatibleThreadStateException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main {

    public static void main(String[] args) {
        BufferedReader bf = null;
        try{
            File file = new File("DataBase");
            if (!file.exists())
                file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            pw.println("Заголовок!");
            pw.println("Тело файла");
            pw.close();

            bf = new BufferedReader(new FileReader("DataBase"));
            String line;
            while((line = bf.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e){
            System.out.println("Ошибка: " + e);
        } finally {
            try {
                bf.close();
                System.out.println("Завершено!");
            } catch (IOException e){
                System.out.println("Error: " + e);
            }
        }
    }
}
