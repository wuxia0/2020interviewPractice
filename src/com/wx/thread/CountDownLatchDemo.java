package com.wx.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: wuxia
 * @Date: 2020/05/30/11:54
 *  CountDownLatch 做减法
 * 齐	国，被灭
 * 魏	国，被灭
 * 韩	国，被灭
 * 赵	国，被灭
 * 楚	国，被灭
 * 燕	国，被灭
 * main	**********秦帝国，一统华夏
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <=6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t国，被灭");
                countDownLatch.countDown();
            },ContryEnum.forEach_contryEnum(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t**********秦帝国，一统华夏");
    }
    public static void closeDoor() throws InterruptedException{
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <=6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t上完自习，离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t**********班长最后关门走人");
    }
}
