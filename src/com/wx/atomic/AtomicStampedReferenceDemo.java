package com.wx.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 2020-04-09
 * 解决ABA问题
 */
public class AtomicStampedReferenceDemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<Integer>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);
    public static void main(String[] args) {
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            System.out.println(atomicReference.get());
            atomicReference.compareAndSet(101,100);
            System.out.println(atomicReference.get());
        },"tl").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t第一次");
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
            }
            atomicReference.compareAndSet(100,102);
            System.out.println(atomicReference.get());
        },"t2").start();
        try {
            TimeUnit.SECONDS.sleep(2);//让上面的代码执行完
        }catch(InterruptedException e){
        }
        System.out.println("====================ABA 解决方案==========");
        new Thread(()->{
            int stamp =atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第1此版本号"+stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第2此版本号"+atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第3此版本号"+atomicStampedReference.getStamp());
        },"t3").start();

        new Thread(()->{
            int stamp =atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第1此版本号"+stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch(InterruptedException e){
            }
            boolean res= atomicStampedReference.compareAndSet(100,102,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t第修改成功否"+res +" ,最新版本"+atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName()+"\t当前值："+atomicStampedReference.getReference());
        },"t4").start();
    }
}
