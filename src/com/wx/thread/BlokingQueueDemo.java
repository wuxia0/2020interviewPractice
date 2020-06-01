package com.wx.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Auther: wuxia
 * @Date: 2020/05/30/15:25
 * 练习阻塞队列
 * 阻塞队列：
 *          当队列是空的时候，从队列中取数据时会阻塞
 *          当队列是满的时候，往队列放数据时会阻塞
 *阻塞：在多线程的
 * BlokingQueue的好处：我们不需要关心什么时候阻塞线程，什么时候唤醒线程。
 */
public class BlokingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
       // System.out.println(blockingQueue.add("d"));//java.lang.IllegalStateException: Queue full
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());//先进先出 a
        System.out.println(blockingQueue.remove());//b
        System.out.println(blockingQueue.remove());//c
        System.out.println(blockingQueue.remove());//java.util.NoSuchElementException

        //
    }
}
