package com.wx.singleton;

/**
 * 通过静态内部类 实现饿汉式单例模式
 */
public class Singleton6 {

    private Singleton6(){};
    private static class Inner{
        private static final Singleton6 S6 = new Singleton6();
    }
    public static Singleton6 getInstance(){
        return Inner.S6;
    }
}
