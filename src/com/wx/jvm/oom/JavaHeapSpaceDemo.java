package com.wx.jvm.oom;

import java.util.Random;

/**
 * @Auther: wuxia
 * @Date: 2021/09/11/10:57
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
       // javaHeapSpaceError();
        byte[] bytes = new byte[80 * 1024 * 1024];
    }

    private static void javaHeapSpaceError() {
        String str = "wx";
        while (true) {
            str += str + new Random().nextInt(1111) + new Random(2222);
            str.intern();
            //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        }
    }
}
