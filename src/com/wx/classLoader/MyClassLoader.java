package com.wx.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 * @Auther: wuxia
 * @Date: 2020/05/02/13:50
 * 类加载机制练习：
 * 1.启动类加载机制BootStrapClassLoader（c语言实现的）
 * 2.扩展类加载机制ExtClassLoader
 * 3.应用类加载机制AppClassLoader
 * 4.自定义类加载机制：实现findClass方法
 */
public class MyClassLoader extends ClassLoader{
    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader();
        System.out.println(myClassLoader.getClass().getClassLoader().getClass().getName());//sun.misc.Launcher$AppClassLoader
        System.out.println(myClassLoader.getClass().getClassLoader().getParent());//sun.misc.Launcher$ExtClassLoader@74a14482
        System.out.println(myClassLoader.getClass().getClassLoader().getParent().getParent());//null
        System.out.println("============");

    }

    //实现自定义类加载器，只要继承ClassLoader类，并重写findClass方法，为什么只要重写findCLass方法
    //因为已经在classLoader方法中实现了类搜索算法，当找不到的时候会调用findclass方法，所以只要重写findclass方法即可
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        throw new ClassNotFoundException(name);
    }

}
