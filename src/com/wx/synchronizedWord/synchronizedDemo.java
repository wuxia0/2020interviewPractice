package com.wx.synchronizedWord;

import java.util.concurrent.locks.ReentrantLock;

public class synchronizedDemo {
    static  L l = new L();
    static ReentrantLock reentrantLock = new ReentrantLock();
    public static void main(String[] args) {
        lockTest();

    }
    public static void lockTest(){
        reentrantLock.lock();
        System.out.println("wuxuuuuu");
        reentrantLock.unlock();

        synchronized (l) {  //锁住代码块还是锁住l对象？？？
            System.out.println("XXXXX");
        }

    }
}
