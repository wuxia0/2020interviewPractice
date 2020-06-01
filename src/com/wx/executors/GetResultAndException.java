package com.wx.executors;

import java.util.List;
import java.util.concurrent.*;

/**
 * 2020-04-11
 * 获取线程池处理结果和异常
 */
public class GetResultAndException {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<Object> future = es.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                throw new RuntimeException("该异常会在调用future.get()时传递给调用者~~");
            }
        });
        //ctrl+alt+t
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {//捕获异常
            e.printStackTrace();
        }

    }
    public void testLatch(ExecutorService es, List<Runnable> takes){

    }
}
