package com.wx.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Auther: wuxia
 * @Date: 2020/05/30/12:26
 * CyclicBarrier 做加法
 *
 * Thread-0	收集到第：1龙珠
 * Thread-5	收集到第：6龙珠
 * Thread-2	收集到第：3龙珠
 * Thread-1	收集到第：2龙珠
 * Thread-3	收集到第：4龙珠
 * Thread-4	收集到第：5龙珠
 * Thread-6	收集到第：7龙珠
 * Thread-6	集齐七颗龙珠，召唤神龙
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws Exception{
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println(Thread.currentThread().getName()+"\t集齐七颗龙珠，召唤神龙");});
        for (int i = 1; i <=7 ; i++) {
            final int temp=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t收集到第："+temp+""+"龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
