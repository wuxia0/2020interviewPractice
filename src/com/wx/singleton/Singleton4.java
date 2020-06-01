package com.wx.singleton;

/**
 * 单例模式之懒汉式
 * 线程不安全，节省内存空间
 */
public class Singleton4 {
    private static Singleton4 s4;
    private Singleton4() {
    }
    public static Singleton4 getInstance() {
        synchronized (Singleton4.class){//类锁
            if (s4 == null) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {

                }
                s4 = new Singleton4();
            }
        }
        return s4;
    }
}
