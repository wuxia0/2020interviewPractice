package com.wx.singleton;

import java.util.*;
import java.util.concurrent.*;

public class SingletonTest {
    public static void main(String s[])throws InterruptedException , ExecutionException {

//        System.out.println( Singleton1.SINGLETON_1);
//        System.out.println(Singleton2.s2);
//        System.out.println(Singleton3.INSTANCE);
//
//        System.out.println("==================单线程，安全");
//        Singleton4 s4 = Singleton4.getInstance();
//        Singleton4 s5 = Singleton4.getInstance();
//        System.out.println(s4==s5);//true
//        System.out.println("=========================多线程测试");

        //创建多线程方法一：
//        false
//        com.wx.singleton.Singleton4@7f31245a
//        com.wx.singleton.Singleton4@6d6f6e28
//        Callable<Singleton4> callable = new Callable<Singleton4>(){
//            @Override
//            public Singleton4 call() throws Exception {
//                return Singleton4.getInstance();
//            }
//        };
//        ExecutorService es = Executors.newFixedThreadPool(2);//创建2个线程的线程池
//        Future<Singleton4> f1 = es.submit(callable);
//        Future<Singleton4> f2 = es.submit(callable);
//        Singleton4 s41 = f1.get();
//        Singleton4 s42 = f2.get();
//        System.out.println(s41 == s42);
//        System.out.println(s41);
//        System.out.println(s42);
//        es.shutdown();

        //创建多线程方法二
        //加了synchronized之后，线程就安全了，运行多次得到的都是同一个实例，例如下面：
        //Thread2 - com.wx.singleton.Singleton4@4daa077
        //Thread1 - com.wx.singleton.Singleton4@4daa077
//         new Thread(()->{
////             System.out.println(Thread.currentThread().getName()+" - "+Singleton4.getInstance());
////         },"Thread1").start();
////        new Thread(()->{
////            System.out.println(Thread.currentThread().getName()+" - "+Singleton4.getInstance());
////        },"Thread2").start();

        //集合
        Collections.synchronizedList(new ArrayList());
        Collections.synchronizedSet(new HashSet<>());
        //Vector;
       // LinkedList
        //HashSet
       // HashMap
       // Hashtable

        //Singleton5测试  线程安全的
//        Callable<Singleton5> callable = new Callable<Singleton5>() {//创建多线程的方法
//            @Override
//            public Singleton5 call() throws Exception {
//                return Singleton5.getInstance();
//            }
//        };
//        ExecutorService es = Executors.newFixedThreadPool(2);
//        FutureTask<Singleton5> futureTask1 = new FutureTask(callable);
//        FutureTask<Singleton5>  futureTask2 = new FutureTask(callable);
//        es.submit(futureTask1);
//        es.submit(futureTask2);
//        Singleton5 s1 = futureTask1.get();
//        Singleton5 s2 = futureTask2.get();
//        System.out.println(s1 ==s2 );
//        System.out.println(s1);
//        System.out.println(s2);
//        es.shutdown();

        //Singleton6 测试 ，静态内部类的方法实现单例模式
        Callable<Singleton6> callable = new Callable<Singleton6>() {
            @Override
            public Singleton6 call() throws Exception {
                System.out.println("调用静态方法。。。");
                return Singleton6.getInstance();
            }
        };
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton6> f1 = es.submit(callable);
        Future<Singleton6> f2 = es.submit(callable);
        Singleton6 s1 = f1.get();
        Singleton6 s2 = f1.get();
        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);
        es.shutdown();
    }
}
