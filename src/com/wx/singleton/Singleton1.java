package com.wx.singleton;

/**
 * 单例模式：懒汉式和饿汉式
 * 懒汉式：线程不安全，
 *          加锁，DCL,静态内部类
 * 饿汉式：线程安全，类初始化的时候就实例化
 *         直接实例化， 枚举值，静态代码块初始化
 */
public class Singleton1 {

    public static final Singleton1 SINGLETON_1 = new Singleton1();
    private  Singleton1(){};
}
