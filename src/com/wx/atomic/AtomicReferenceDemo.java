package com.wx.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 2020-04-09
 * CAS是一条CPU的原子指令
 * 原子类:用的是Unsafe类，可以操作底层资源直接执行，
 * 变量valueOffset (内存偏移地址)，Unsafe类就是根据内存偏移地址获取数据，极度精确
 * AtomicInteger 维护了一个UnSafe对象，unsafe对象有个本地方法objectFieldOffset
 * 为什么用CAS，而不用synchornized，因为自旋锁和Unsafe
 *synchornized：一致性得到了保障，但是并发性下降
 * CAS:一致性得到了保障，并发性也能提高
 * CAS缺点：循环时间长，开销大；只能保证一个共享变量的原子操作；ABA问题
 * 解决ABA问题：加上版本就可以解决
 *
 * unsafe类中的方法：是原子性的，基于cpu原语指令
 * public final int getAndAddInt(Object paramObject, long paramLong, int paramInt)
 *   {
 *     int i;
 *     do
 *       i = getIntVolatile(paramObject, paramLong);
 *     while (!compareAndSwapInt(paramObject, paramLong, i, i + paramInt));
 *     return i;
 *   }
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5,6)+"\t"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,7)+"\t"+atomicInteger.get());

        //ABA问题产生??? 理解原子引用 +新增一种机制，那就是修改版本号（类似时间戳）
        User z3 =new User("z3",23);
        User li4 =new User("li4",24);
        AtomicReference<User> atomicReference = new AtomicReference<User>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t"+atomicReference.get().toString());//true
        System.out.println(atomicReference.compareAndSet(z3,li4)+"\t"+atomicReference.get().toString());//false

    }
}
class User{
    String username;
    int age;

    public User(String z3, int i) {
        this.username=z3;
        this.age=i;
    }
}