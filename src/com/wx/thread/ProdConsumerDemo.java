package com.wx.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: wuxia
 * @Date: 2020/05/30/15:57
 * 题目：一个初始值为0的变量，2个线程交替操作，一个加1，一个减1，来5轮
 *  1. 线程    操作     资源类
 *  2. 判断     干活      通知
 *  3. 防止虚假唤醒机制:多线程的判断用循环while,不要用if判断
 *
 *  用if 判断会出现：
 *  AA	1
 * BB	0
 * CC	1
 * BB	0
 * AA	1
 * CC	2
 * DD	1
 * DD	0
 * BB	-1
 * BB	-2
 * BB	-3
 * AA	-2
 * CC	-1
 * DD	-2
 * DD	-3
 * DD	-4
 * AA	-3
 * CC	-2
 * AA	-1
 * CC	0
 */
public class ProdConsumerDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(()->{
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
}
}
class ShareData{//资源类
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception{
        lock.lock();
        try {
            //1.判断
            //if(number != 0){//不要用if判断
            while(number != 0){
                //等待，不能生产
                condition.await();
            }
            //2.干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3.通知唤醒
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void decrement() throws Exception{
        lock.lock();
        try {
            //1.判断
            //if(number == 0){//不要用if判断
            while(number == 0){
                //等待，不能生产
                condition.await();
            }
            //2.干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3.通知唤醒
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
