package com.wx.reference;

import java.lang.ref.SoftReference;

/**
 * @Auther: wuxia
 * @Date: 2021/09/11/9:13
 *强软弱虚之软引用练习
 * 内存够用不会被回收，内存不够用会被回收
 *
 * 例子：
 * 假如有一个应用需要读取大量的本地图片
 *  *如果每次读取图片都从硬盘读取则会严重影响性能
 *  *如果一次性全部加载到内存中又可能造成内存溢出
 *
 *  此时，使用软引用可以解决这个问题
 *  设计思路：用一个HashMap来保存图片的路径和相应图片对象关联的软引用之间的映射
 *  关系，在内存不足时，JVM会自动回收这些缓存图片对象所占用的空间，从而有效的避免了OOM问题
 *  Map<String,Softrefrence<Bitmap>> imageCache = new HashMap<String,SoftReference<BitMap>>();
 *
 */
public class SoftReferenceDemo {
    public static void main(String[] args) {
        softRef_Memory_Enough();
        //softRef_Memory_NotEnough();
    }

    /**
     * 内存够用，不会被回收
     */
    private static void softRef_Memory_Enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        System.gc();//手动GC

        System.out.println(o1);
        System.out.println(softReference);
    }

    /**
     * 内存不够用，会被回收
     * JVM配置，故意产生大对象并配置小内存，让它内存不够用了导致OOM,看软引用回收情况
     * 在VM Option配置：-Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    private static void softRef_Memory_NotEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;

        try {
           byte[] bytes = new byte[20 *1024 *1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }
}
