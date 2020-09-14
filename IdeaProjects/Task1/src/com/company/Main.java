package com.company;
import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] argv) throws IOException{
        new Main().run();
    }
    PrintWriter pw;
    Scanner sc;
    public void run() throws IOException{
        sc = new Scanner(new File("input.txt"));
        int a  = sc.nextInt();
        int x = 0;
        if(a < 0){
            for(int i = 0; i >= a; i--){
                x = x + i;
            }
        }else if(a < 0) {
            for (int i = 0; i <= a; i++) {
                x = x + i;
            }
        }
        pw = new PrintWriter(new File("output.txt"));
        pw.print(x);
        pw.close();
    }
}
