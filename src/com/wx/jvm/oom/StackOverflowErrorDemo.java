package com.wx.jvm.oom;

/**
 * @Auther: wuxia
 * @Date: 2021/09/11/10:55
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        stackOverflowError();//Exception in thread "main" java.lang.StackOverflowError
    }
    public static void stackOverflowError(){
        stackOverflowError();
    }
}
