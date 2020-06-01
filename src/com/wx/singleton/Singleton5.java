package com.wx.singleton;

/**
 * 单例模式之懒加载DCL，双检测模式，对于例4来说，性能较好，代码复杂
 */
public class Singleton5 {
    private volatile static Singleton5 instance;//如果不加volatile,多线程下会因为指令重排下，会出现空指针异常
    private Singleton5(){}

    public static Singleton5 getInstance(){
        if(instance == null) {
            synchronized (Singleton5.class){
                if (instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
