package com.wx.thread.threads;

import java.util.List;
import java.util.concurrent.*;

/**
 * @Auther: wuxia
 * @Date: 2020/05/30/20:54
 * 线程池练习ThreadPoolExecutor
 * 第四种获得多线程的方式，线程池
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //手写线程池
//        ExecutorService mythreadPool = new ThreadPoolExecutor
//                (2,
//                5,
//                0L, TimeUnit.MILLISECONDS,
//                 new LinkedBlockingDeque(18),
//                 Executors.defaultThreadFactory(),
//                 new ThreadPoolExecutor.DiscardOldestPolicy());
        threadPoolInit();
    }

    public static void threadPoolInit(){
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        Callable task=()->"Task ss";
        Callable task1=()->{
            throw new RuntimeException();
        };
        Callable task2=()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        };

        Object d=threadPool.invokeAll(List.of(task,task1,task2));
        System.out.println( d.get(0).g);
    }
}
