package com.wx.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: wuxia
 * @Date: 2020/05/30/16:39
 * 练习：reentrantLock 可以精确唤醒
 * 题目：多线程之间顺序调用，实现A->B->C三个线程启动，需求如下：
 * AA打印5次，BB打印10ci,CC打印15次
 * 紧接着
 * AA打印5次，BB打印10ci,CC打印15次
 * ...
 * 来10 轮
 */
public class SynAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareData1 shareData1 = new ShareData1();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                shareData1.print5();
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                shareData1.print10();
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                shareData1.print15();
            }
        },"CC").start();
    }
}
class ShareData1{//资源类
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();
    private int number = 1;//信号量，用来控制是哪个线程
    //1.判断
    //2.干活
    //3.通知
    public void print5(){
        lock.lock();
        try {
            //1.判断
            while(number !=1){
                condition1.await();
            }
            //2.干活
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3.通知
            number=2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void print10(){
        lock.lock();
        try {
            while(number !=2){
                condition2.await();
            }
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void print15(){
        lock.lock();
        try {
            while(number !=3){
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
