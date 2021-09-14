package com.wx.jvm.oom;

import java.nio.ByteBuffer;

/**
 * @Auther: wuxia
 * @Date: 2021/09/11/11:29
 * JVM配置参数示例：
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * 故障现象：Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 *
 * 导致原因：
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory:"
                + (sun.misc.VM.maxDirectMemory() / (double)1024 / 1024) + "MB");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //MaxDirectMemorySize=5m  配置5m,实际使用6m 故意使坏
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);

    }
}
