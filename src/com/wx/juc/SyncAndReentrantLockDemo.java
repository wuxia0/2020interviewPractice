package com.wx.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: wuxia
 * @Date: 2021/09/05/8:56
 * 题目：多线程间按顺序调用，实现A->B->C三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * 来10轮
 */
class ShareResouce{
    private int numbr = 1; // A :1; B:2  ; C:3
    private Lock lock = new ReentrantLock();
}
public class SyncAndReentrantLockDemo {
}
