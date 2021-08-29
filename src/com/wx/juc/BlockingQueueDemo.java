package com.wx.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: wuxia
 * @Date: 2021/08/28/22:03
 * 1.对列
 *
 * 2.阻塞队列
 * 为什么需要阻塞队列？
 * 我们不需要再关心什么时候阻塞，什么时候不阻塞
 *   2.1 阻塞队列有没有好的一面
 *
 *   2,2 不得不堵塞，如何管理
 */
public class BlockingQueueDemo{
    public static void main(String[] args) throws Exception{
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
//        System.out.println(blockingQueue.add("温良恭俭让"));
//        System.out.println(blockingQueue.add("仁义礼智信"));
//        System.out.println(blockingQueue.add("忠孝廉耻勇"));
//        System.out.println(blockingQueue.element());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.add("忠孝廉耻勇1"));

//        System.out.println(blockingQueue.offer("a"));
//        System.out.println(blockingQueue.offer("b"));
//        System.out.println(blockingQueue.offer("c"));
//        System.out.println(blockingQueue.offer("d"));//false
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());//null

//          blockingQueue.put("a");
//          blockingQueue.put("c");
//          blockingQueue.put("e");
//          System.out.println("==============");
//          //blockingQueue.put("r");
//          blockingQueue.take();
//          blockingQueue.take();
//          blockingQueue.take();
//          blockingQueue.take();

           blockingQueue.poll(2, TimeUnit.SECONDS);

    }
}
