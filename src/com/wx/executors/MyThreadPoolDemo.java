package com.wx.executors;

import java.util.concurrent.*;

/**
 * 2020-03-27 手写线程池ThreadPoolExecutor方法
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor tp = new ThreadPoolExecutor
                (2, 5, 1L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),//有界队列
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
        System.out.println(Runtime.getRuntime().availableProcessors());//看电脑是几核的

        //线程池提交线程的3种方式：
        tp.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        tp.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
        tp.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
    }
}
