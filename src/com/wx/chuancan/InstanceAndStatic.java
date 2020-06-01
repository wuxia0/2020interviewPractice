package com.wx.chuancan;

/**
 * @Auther: wuxia
 * @Date: 2020/05/29/11:11
 * 方法区：存类信息，静态变量，常量
 * 局部变量：存放在栈中,局部变量没有默认值
 * 成员变量：成员变量有默认值
 *      类变量：存放在方法区中，static修饰；随着类的初始化而初始化，类的销毁而销毁
 *      实例变量：存放在堆中，非static修饰；随着对象的初始化而初始化，对象的销毁而销毁
 *
 * 实例化执行的是<init>()方法，包括非静态代码块
 */
public class InstanceAndStatic {
    static int s;
    int i;
    int j;
    {
        int i=1;
        j++;
        s++;
    }
    public void test(int j){
        i++;
        j++;
        s++;
    }

    public static void main(String[] args) {
        InstanceAndStatic obj1 =new InstanceAndStatic();
        InstanceAndStatic obj2 =new InstanceAndStatic();
        obj1.test(10);
        obj1.test(20);
        obj2.test(30);
        System.out.println(obj1.i+","+obj1.j+","+obj1.s);
        System.out.println(obj2.i+","+obj2.j+","+obj2.s);
    }
}

