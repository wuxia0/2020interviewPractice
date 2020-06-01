package com.wx.synchronizedWord;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: wuxia
 * @Date: 2020/05/30/11:02
 * 练习自旋锁:
 * 自旋锁：不用阻塞，不用上下文切换，坏处，循环会消耗cpu
 */
public class SpinLockDemo {
    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"AAA").start();

        try {TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}
        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        },"BBB").start();
    }
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in (*^▽^*)");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t invoke myUnLock");
    }
}
