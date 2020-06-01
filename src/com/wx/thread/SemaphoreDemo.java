package com.wx.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: wuxia
 * @Date: 2020/05/30/12:36
 * 练习信号量类 ：Semaphore
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位
        for (int i = 1; i <= 6; i++) {//模拟6部汽车
            new Thread(()->{
                try {
                    semaphore.acquire();//值可以伸缩
                    System.out.println(Thread.currentThread().getName()+"\t抢到车位");
                    try {TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) {e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName()+"\t停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
