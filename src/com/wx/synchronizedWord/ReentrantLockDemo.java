package com.wx.synchronizedWord;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: wuxia
 * @Date: 2020/05/30/9:58
 * 测试 ReentrantLock是可重入锁
 */
public class ReentrantLockDemo implements Runnable{
    Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        Thread t3 = new Thread(reentrantLockDemo,"t3");
        Thread t4 = new Thread(reentrantLockDemo,"t4");
        t3.start();
        t4.start();
    }

    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoke get");
            set();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoke set");
        } finally {
            lock.unlock();
        }
    }
}
