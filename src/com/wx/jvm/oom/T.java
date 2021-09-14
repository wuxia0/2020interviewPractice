package com.wx.jvm.oom;

/**
 * @Auther: wuxia
 * @Date: 2021/09/12/8:47
 * Exception in thread "main" java.lang.IllegalThreadStateException
 */
public class T {
    public static void main(String[] args) {
        Thread t = new Thread();
        t.start();
        t.start();
    }
}
