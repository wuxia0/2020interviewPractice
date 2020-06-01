package com.wx.thread.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: wuxia
 * @Date: 2020/05/30/20:19
 * 通过futureTask实现线程
 * 固化思想：永远传参传接口
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // FutureTask(Callable<V> callable) FutureTask 实现了Runnable接口，传入了Callable接口
        FutureTask<Integer> futureTask = new FutureTask(new MyThread());
        FutureTask<Integer> futureTask1 = new FutureTask(new MyThread());
         new Thread(futureTask,"AA").start();
         new Thread(futureTask,"BB").start();//AA和BB线程只会执行一次
        new Thread(futureTask1,"CC").start();
        futureTask.get();
        System.out.println(Thread.currentThread().getName()+"\t"+33);
        System.out.println("*****result:"+futureTask.get());
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
class MyThread implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName()+"\t come in");
        return 11;
    }
}
