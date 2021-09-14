package com.wx.reference;

import java.lang.ref.WeakReference;

/**
 * @Auther: wuxia
 * @Date: 2021/09/11/9:31
 * 强软弱虚应用之弱引用：不管内存够不够用，只要垃圾回收发生，就会回收
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println("===========");

        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
