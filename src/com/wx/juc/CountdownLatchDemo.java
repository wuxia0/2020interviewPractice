package com.wx.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: wuxia
 * @Date: 2021/08/28/11:41
 * CountdownLatch 练习
 */
public class CountdownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
       // pickCard();
        emotionTried();
    }

    private static void emotionTried() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t 人生七苦，得偿");
                countDownLatch.countDown();
            }, EmotionEnum.forEach_EmotionEnum(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t*********人生七苦都偿，苦不堪言。");
    }

    private static void pickCard() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "\t 下班，打卡");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t*********最后下班的人，关灯。");
    }
}
