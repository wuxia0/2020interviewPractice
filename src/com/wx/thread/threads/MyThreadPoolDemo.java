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
        threadPoolExcetorsByWriter();
        //threadPoolInit();
    }

    private static void threadPoolExcetorsByWriter() {
        //手写线程池
        ExecutorService mythreadPool = new ThreadPoolExecutor
                (2,
                4,
                0L, TimeUnit.MILLISECONDS,
                 new LinkedBlockingDeque(2),
                 Executors.defaultThreadFactory(),
                 new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 1; i <= 10; i++) {
            mythreadPool.execute(()->{
                System.out.println(Thread.currentThread().getName() + "\t办理业务");
            });
        }
    }

    public static void threadPoolInit(){
        //Excetors一般不使用，容易耗完资源，默认用的是LikedBlockQueue
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

       // Object d=threadPool.invokeAll(List.of(task,task1,task2));
       // System.out.println( d.get(0).g);
    }
}
