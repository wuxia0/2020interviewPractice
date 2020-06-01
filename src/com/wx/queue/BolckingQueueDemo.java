package com.wx.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2020-03-27 队列
 *
 */
public class BolckingQueueDemo {
    public static void main(String[] args) throws Exception{
        BlockingQueue<String> bq = new ArrayBlockingQueue<String>(3);
//        System.out.println(bq.add("wuxia"));
//        System.out.println(bq.add("wuxia"));
//        System.out.println(bq.add("wuxia"));
//      //  System.out.println(bq.add("wuxia"));//add 加不进去会抛出异常  java.lang.IllegalStateException: Queue full
//        System.out.println(bq.remove());
//        System.out.println(bq.remove());
//        System.out.println(bq.remove());
      //  System.out.println(bq.remove());//java.util.NoSuchElementException
        //System.out.println(bq.element());// java.util.NoSuchElementException

        //不抛出异常的api
//        System.out.println(bq.offer("wuxia"));
//        System.out.println(bq.offer("wuxai"));
//        System.out.println(bq.offer("wuxia"));//true
//        System.out.println(bq.offer("wuxia"));//false
//
//        System.out.println(bq.poll());
//        System.out.println(bq.poll());
//        System.out.println(bq.poll());//wuxia
//        System.out.println(bq.poll());//null
//        System.out.println(bq.peek());//null

//          bq.put("wuxai");
//          bq.put("wuxai");
//          bq.put("wuxai");
//          System.out.println("==================");
//          //bq.put("wuxai");//一直等待，不放进去不走  最好不要采取这种方式
//          bq.take();
//          bq.take();
//          bq.take();
//          System.out.println("==================");
//          bq.take();//一直等待，拿不到不走

        //
        System.out.println(bq.offer("wuxai",2L,TimeUnit.SECONDS));
        System.out.println(bq.offer("wuxia",2L,TimeUnit.SECONDS));
        System.out.println(bq.offer("wuxia",2L,TimeUnit.SECONDS));
        System.out.println(bq.offer("wuxia",2L,TimeUnit.SECONDS));//2s之后返回false
        System.out.println(bq.poll(2L,TimeUnit.SECONDS));
        System.out.println(bq.poll(2L,TimeUnit.SECONDS));
        System.out.println(bq.poll(2L,TimeUnit.SECONDS));
        System.out.println(bq.poll(2L,TimeUnit.SECONDS));//null
        //AtomicInteger
    }


}
