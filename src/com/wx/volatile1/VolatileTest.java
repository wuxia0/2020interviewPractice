package com.wx.volatile1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: wuxia
 * @Date: 2020/05/29/14:42
 * volatile的可见性：
 * int number =1; 中没有加volatile关键字修饰，线程之间不可见
 * volatile的原子性：加synchronized关键字或者用juc包下面的原子类
 *
 * volatile禁止指令重排序
 *     计算机在执行程序时，为了提高性能，编译器和处理器往往会对指令进行重排
 */
public class VolatileTest {
    public static void main(String[] args) {
        MyData myData = new MyData();
        //验证volatile的可见性
        //testVolatileSee(myData);

        //验证volatile原子性
        testVolatileAtomic(myData);
    }

    private static void testVolatileAtomic(MyData myData) {
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000 ; j++) {
                    myData.addPlus();
                    myData.myAddAtomic();
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2){//默认一个main线程和一个gc线程
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\tfinal number value="+myData.number);
        System.out.println(Thread.currentThread().getName()+"\tatomicInteger final number value="+myData.atomicInteger);
        //main	final number value=19553,基本上都是少于20000，偶尔会有一次是20000，因为volatile不能保证原子性
        //如果加上lsynchonize关键字修饰addPlus()，那么就是20000
        //解决volatile原子性：用synchronized太耗性能了，应该用原子类
    }

    private static void testVolatileSee(MyData myData) {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.add();
            System.out.println("mydata.num的值改变了"+myData.number);
        },"AAA").start();
        System.out.println(Thread.currentThread().getName()+"\t"+myData.number);
        while(myData.number == 0){

        }
        System.out.println("加了volatile会打印这句话");
    }
}
class MyData{
    volatile int number = 0;
    AtomicInteger atomicInteger = new AtomicInteger();
    public void add(){
        this.number = 60;
    }
    public /**synchronized **/void addPlus(){
        this.number++;
    }

    public void myAddAtomic(){
        atomicInteger.getAndIncrement();
    }
}
