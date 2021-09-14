package com.wx.jvm.oom;

/**
 * @Auther: wuxia
 * @Date: 2021/09/11/11:46
 *
 * 一个应用创建太多线程了，一个应用进程创建太多多个线程，超过系统承载极限
 * 服务器不允许你的应用程序创建太多线程
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 1; ;i++) {
            System.out.println("*********i=" + i);
            new Thread(()->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }
    }
}
