package com.wx.singleton;

public class Intern2 {
    public static void main(String args[]){
        int i = 5;
        String i1 = "" + i;//
        String i2 = String.valueOf(i);//
        String i3 = Integer.toString(i);
        System.out.println( i2 == i1);//false
        System.out.println( i2 == i3);//false
        System.out.println(i1==i3);//false
    }
}
