package com.wx.thread.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: wuxia
 * @Date: 2020/05/30/20:19
 * 通过futureTask实现线程
 * 固化思想：永远传参传接口,接口可以多实现。
 * 有的框架，只有生命，没有实现，只为了混个身份。
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       // futureMethod();

        try {
            forkJoin();
            System.out.println("forkJoin结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void futureMethod() throws InterruptedException, ExecutionException {
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

    public static void forkJoin() throws Exception{
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        Thread t1 = new Thread(futureTask,"wxThread");
        t1.start();
        int result02 = futureTask.get();
        System.out.println(Thread.currentThread().getName() + "**********");
        int result01 = 100;
        //int result02 = futureTask.get();
        System.out.println("*************result=" + (result01 + result02));
    }

}
class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println(Thread.currentThread().getName()+"\t come in");
        return 11;
    }
}

class MyThread2 implements Runnable {

    @Override
    public void run() {

    }
}
