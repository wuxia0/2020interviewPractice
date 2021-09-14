package com.wx.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @Auther: wuxia
 * @Date: 2021/09/11/9:38
 * 强软弱虚引用值虚引用：形同虚设，任何时候都有可能被垃圾回收器回收；
 * 不能被单独使用，也不能通过它访问对象，虚引用必须和ReferenceQueue一起使用
 * 作用：
 * ps:创建引用的时候可以指定关联的队列，当GC释放对象内存的时候，会将引用加入引用队列。
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
       // referenceQueuePractice();

        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o,referenceQueue);

        System.out.println(o);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("===============");
        o = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }

    private static void referenceQueuePractice() throws InterruptedException {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o,referenceQueue);

        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("===============gc后");
        o = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
}
