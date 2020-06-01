package com.wx.executors;

import java.util.concurrent.*;

/**
 * 2020-03-29 多线程练习
 * 多线程的实现方式：继承Thread,实现runnable接口，callable接口，线程池
 *  callable接口有返回值，抛出异常
 *  runnable没有返回值，不会抛出异常
 *
 */
public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        Thread t = new Thread(futureTask,"aa");
        t.start();
        while(!futureTask.isDone()){

        }
        int a =futureTask.get();
        System.out.println(a);
    }

}
