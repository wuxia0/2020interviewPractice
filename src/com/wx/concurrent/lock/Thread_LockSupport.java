package com.wx.concurrent.lock;

import java.util.concurrent.Semaphore;

/**
 * @Auther: wuxia
 * @Date: 2020/08/22/14:57
 */
public class Thread_LockSupport {


    public static void main(String[] args) {
        Semaphore semaphore  = new Semaphore(3);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
