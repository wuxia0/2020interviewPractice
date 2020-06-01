package com.wx.executors;

import java.util.concurrent.*;

/**
 * 2020-03-27 线程池练习
 *
 */
public class ThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException{
        //ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // ExecutorService threadPool = Executors.newCachedThreadPool();
        //自己手写线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 1L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        try{
            for (int i=0;i<22;i++){
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }catch(Exception e){
        }finally{
            threadPool.shutdown();
        }
    }
}
