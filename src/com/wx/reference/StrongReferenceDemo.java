package com.wx.reference;

/**
 * @Auther: wuxia
 * @Date: 2021/09/11/9:06
 * 强软弱虚之强引用练习
 * 强引用不会被回收
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj = new Object();//强引用
        Object obj2 =obj;
        obj = null;
        System.gc();
        System.out.println(obj2);
        System.out.println(obj);
    }
}
