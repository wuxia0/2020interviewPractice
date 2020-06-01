package com.wx.refect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 2020-04-11
 * 通过反射获取类对象的成员变量对象（主要是获取值，和设置值），
 * 构造方法对象（主要用来创建对象），成员方法对象（主要用来执行方法），以及获取之后的一些操作，
 * 重点就是获取后的一些操作
 */
public class RefectGetFieldDemo {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.wx.refect.Student");
        Student student1 =new Student();
        Field f = clazz.getDeclaredField("name");
        //Object name = f.get(new Student());// 会报这个错误，IllegalAccessException，因为成员变量是私有的，需要设置下
        //忽略访问权限修饰符的安全检测
        f.setAccessible(true);//即暴力反射，不管是不是私有的都能访问
        f.set(student1, "wuxia");//给成员变量赋值
        Object name = f.get(student1);//可以获取成员变量的值
        System.out.println("通过反射获取到了类对象的成员变量，并对其做一些操作："+name);

        //获取构造方法
        //构造方法是用来创建对象
        Constructor constuct = clazz.getConstructor(String.class);
        Constructor constuct1 = clazz.getConstructor();
        System.out.println(constuct);
        //创建对象
        Object o = constuct.newInstance("wuxia");
        System.out.println(o);
        Object o1 = constuct1.newInstance();
        System.out.println(o1);
        System.out.println(clazz.newInstance());

        System.out.println("======通过反射获取方法对象，然后执行方法===========");

        Method method= clazz.getDeclaredMethod("eat",String.class);
        method.setAccessible(true);//忽略访问权限修饰符的安全检测
        method.invoke(student1,"wuxia");

        Modifier.toString(f.getModifiers());
        f.getModifiers();
        constuct.getModifiers();
        method.getModifiers();
        System.out.println(Modifier.toString(f.getModifiers()));
        System.out.println(Modifier.toString(constuct.getModifiers()));
        System.out.println(Modifier.toString(method.getModifiers()));
    }

}
