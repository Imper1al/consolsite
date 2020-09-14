package com.company;
import java.util.*;

public class Array implements InterArray {
    int[] a = new int[3];
    int val = 0;

    @Override
    public int Get (int i){
        return a[i];
    }

    @Override
    public int Add (){
        Random ran = new Random();
        val = ran.nextInt(10);
        for (int j = 0; j < a.length; j++){
            a[j] = val;
        }
        return val;
    }
}
