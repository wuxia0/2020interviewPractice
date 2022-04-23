package com.wx.thread.threads;

import java.util.concurrent.*;

/**
 * 1.进程是一个静态的概念.  .class,.exe文件都是一个进程。真正运行的是线程
 * 2.一个cpu 在一个时间点上只有一个线程在运行。只是cpu执行速度很快，分成了很多个时间片，被多个线程轮流拿到，然后看起来像多线程。
 * 	有多个cpu 或者双核，才是真正的多线程。
 * 3.线程分两种：守护线程（垃圾回收器，依赖）和
 * @author WX
 *
 */
public class ThreadDemo {

    public static void main(String[] args) {
        ThreadDemo h= new ThreadDemo();
        Runner1 r1 = h.new Runner1();
        //1.直接调用run方法，只是方法调用
        //r1.run();

        //2.调用runner1线程， 调用start方法：线程启动
        Thread t =new Thread(r1);//创建Runnable实现类的实例，并用这个实例作为Thread的target来创建Thread对象，这个Thread对象才是真正的线程对象
        t.start();//两个线程交替运行
        for (int i = 0; i < 100; i++) {
            System.out.println("MainThread: " + i);
        }

        //3.
        h.new MyThread().start();//创建并启动线程

        //4.实现callable方法，重写call方法

        try {
            ExecutorService threadPool = Executors.newSingleThreadExecutor();
            Future<String> fu = threadPool.submit(h.new Callable1());
            fu.get();//get()方法会阻塞，直到子线程执行结束才返回
            System.out.println(fu.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建一个新的线程的方法:1.实现runnable接口， 2.继承Thread类 3.实现callable和Future 创建线程
     * 1.实现runnable接口
     * @author WX
     *
     */
    class Runner1 implements Runnable{

        @Override
        public void run() {
            for (int i=0 ;i<100; i++){
                System.out.println("Runner1:" + i);
            }
        }

    }
    /**
     * 2.继承Thread类
     * @author WX
     *
     */
    class MyThread extends Thread{
        //重写run方法
        public void run(){
            //System.out.println("2.MyThread创建线程");
            for (int i=0 ;i<100; i++){
                System.out.println("2.MyThread创建线程:" + i);
            }
        }
    }

    class Callable1 implements Callable<String> {

        @Override
        public String call() throws Exception {
            for (int i=0 ;i<100; i++){
                System.out.println("3.Callable1创建线程:" + i);
            }
            return "Callable1.java";
        }

    }
}
