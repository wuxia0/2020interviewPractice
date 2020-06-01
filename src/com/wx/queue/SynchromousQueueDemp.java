package com.wx.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**\
 * 同步队列:单个元素的队列，产生一个，消费一个.0库存队列
 */
public class SynchromousQueueDemp {
    public static void main(String[] args)throws InterruptedException {
        BlockingQueue<String> bq = new SynchronousQueue<>();//非公平锁
        new Thread(()->{
            try{
                System.out.println(Thread.currentThread().getName()+"  t1");
                bq.put("1");
                System.out.println(Thread.currentThread().getName()+"  t2");
                bq.put("2");
                System.out.println(Thread.currentThread().getName()+"  t3");
                bq.put("3");
            }catch(InterruptedException e){
            }
        },"aaa").start();

        new Thread(()->{
            try{
               // TimeUnit.SECONDS.sleep(5L);//5s之后来消费
                System.out.println(Thread.currentThread().getName()+"t1");
                bq.take();
               // TimeUnit.SECONDS.sleep(5L);
                System.out.println(Thread.currentThread().getName()+"t2");
                bq.take();
               // TimeUnit.SECONDS.sleep(5L);
                System.out.println(Thread.currentThread().getName()+"t3");
                bq.take();
            }catch(InterruptedException e){
            }
        },"bbb").start();
    }
}
