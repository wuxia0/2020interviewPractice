package com.wx.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: wuxia
 * @Date: 2021/09/11/11:07
 * JVM参数配置演示：
 * -Xms10m  -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * GC回收过长时会抛出OutofMemeoryError.过长的定义是，超过98%的时间来做GC并且回收了不到2%的堆内存
 * 连续多次GC清理都只回收了不到2%的极端情况下才会抛出。假如不抛出Gcoverhead limit错误会发生什么情况？
 * 那就是GC清理的这么点内存很快就会再次填满，迫使GC再次执行，这样就形成了恶性循环，
 * CPU使用率一直是100%，而GC却没有任何效果。
 *
 * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("*********************i="+ i);
            e.printStackTrace();
        }
    }
}
