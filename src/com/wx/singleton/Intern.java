package com.wx.singleton;

/**
 * 2020-03-27 String的intern 方法
 */
public class Intern {
    public static void main(String[] args) {

        String s = "1" + "23";//
        String s1 = "123";
        System.out.println(s == s1);//true

        //最多创建4个对象，最少2个，堆中23,123 常量池1,23（如果常量池有，就不创建）
        String s2 = "1" + new String("23");
        System.out.println(s == s2);//false
       // System.out.println(s == s2.intern());//true

        //最多创建5个对象，最少3个，堆中：1,23,123，常量池1,23（如果常量池中有就不创建）
        String s3 = new String("1")+new String("23");
        System.out.println(s==s3);//false
       // System.out.println(s == s3.intern());//true

        System.out.println("====================");
        //区分下面2种情况
        String s4 = new String("1")+new String("23");
        s4.intern();
        String s5="123";
        System.out.println(s4==s5);//false !!!!! 重点思考

        //
        String s7 = new String("1")+new String("23");
        String s6 ="123";
        s7.intern();
        System.out.println(s6 == s7);//false  !!!!!! 重点思考
        System.out.println(s6 == s7.intern());//true

        System.out.println("--------------------");
        int i = 5;
        String i1 = "" + i;//相当于  String i1 =(new StringBuilder()).append(i).toString();
        String i2 = String.valueOf(i);//调的是 Integer.toString(i); toString(i) 是new 的一个新对象
        String i3 = Integer.toString(i);
        System.out.println( i2 == i1);//false
        System.out.println( i2 == i3);//false
        System.out.println(i1==i3);//false


    }
}
