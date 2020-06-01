package com.wx.executors;

import java.util.concurrent.Callable;

public class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("通过实现callable接口 创建线程");
        return 1002;
    }
}
