package com.wx.test;

import java.util.Scanner;

/**
 * 实现加减乘除
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            double a = scanner.nextDouble();
            String oper=scanner.next();
            double b =scanner.nextDouble();
            if(oper.equals("+")){
                System.out.println(a+b);
            }else if(oper.equals("-")){
                System.out.println(a-b);
            }else if(oper.equals("*")){
                System.out.println(a*b);
            }else if(oper.equals("/")){
                if(b==0){
                    System.out.println("除数不能为0");
                    continue;
                }
                System.out.println(a/b);
            }else{
                System.out.println("第二位数请输入加减乘除四种运算符！");
                continue;
            }
        }
    }
}
