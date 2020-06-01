package com.wx.chuancan;

import java.util.Arrays;

/**
 * @Auther: wuxia
 * @Date: 2020/05/28/22:26
 * java 传参：
 * 实参传给形参：
 *   基本类型：传递值
 *   引用类型：传递引用地址
 *
 * String,和包装类型（例如Integer）不可变性 !!!!
 */
public class chuancanTest {
    public static void main(String[] args) {
        int i=1;
        String str="hello";
        //Integer num =200;//不在-128-127范围内,值存在堆中
        Integer num =100;//在-128-127范围内，值存在方法区中
        Double d =22.33;
        int[] arr={1,2,3,4,5};
        MyData my = new MyData();
        change(i,str,num,arr,my,d);
        System.out.println("i="+i);
        System.out.println("str="+str);
        System.out.println("num="+num);
        System.out.println("arr="+ Arrays.toString(arr));
        System.out.println("my.a="+my.a);
        System.out.println("d="+d);
    }

    private static void change(int j, String s, Integer n, int[] a, MyData m,Double dd) {
        j+=1;
        s+="world";
        n+=1;
        a[0]+=1;
        m.a+=1;
        dd+=1;
    }
}
class MyData{
    int a =10;
}
