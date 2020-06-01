package com.wx.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: wuxia
 * @Date: 2020/05/30/17:06
 * //高手都传接口
 * //线程通信之生产者消费者阻塞队列版  重点掌握：将线程交互/volatile/原子类/BlockingQueue等结合在一起了
 * 需求：条件为true 那么则生产一个消费一个
 *       条件为false,则停止生产和消费
 */
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) throws Exception{
        BlockingQueue blockingQueue = new ArrayBlockingQueue(5);
        MyResource myResource = new MyResource(blockingQueue);
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
            System.out.println();
            System.out.println();
            try {
                myResource.myConsumer();
                System.out.println();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName()+"\t5秒时间到，大老板叫停了，活动结束");
        myResource.stop();
    }
}
class MyResource{
    private volatile boolean FLAG= true;//默认开启，进行生产和消费
    private AtomicInteger atomicInteger= new AtomicInteger();//不需要在写i++或者++i,用原子类
    BlockingQueue<String> blockingQueue =null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }
    public void myProd() throws Exception{
        String data =null;
        boolean retValue;
        while (FLAG){//true 表示需要生产
            data =atomicInteger.getAndIncrement()+"";
            retValue =blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t 生产者生产\t"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t 生产者生产\t"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 大老板叫停。表示flag=false,生产动作结束");
    }

    public void myConsumer() throws Exception{
        String result =null;
        boolean retValue;
        while (FLAG){//true 表示需要消费
            result=blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(result == null || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过2秒没有取到蛋糕\t消费失败");
                return;//这里必须返回。表示结束
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费者消费\t"+result+"成功");
        }

    }
    public void stop() throws Exception{
        this.FLAG = false;
    }
}