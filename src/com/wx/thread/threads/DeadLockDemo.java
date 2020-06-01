package com.wx.thread.threads;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: wuxia
 * @Date: 2020/05/30/22:07
 * 线程死锁及定位分析练习
 * 死锁原因：资源不足；资源分配不当；
 * 定位分析：jps :定位进程号
 *           jstack：查看某一个进程号的具体信息
 *
 *E:\>jps -l
 * 26672 sun.tools.jps.Jps
 * 30272 org.jetbrains.kotlin.daemon.KotlinCompileDaemon
 * 30340 org.jetbrains.jps.cmdline.Launcher
 * 4660 org.jetbrains.jps.cmdline.Launcher
 * 29208 com.wx.thread.threads.DeadLockDemo
 * 7352 org.jetbrains.idea.maven.server.RemoteMavenServer
 * 8520
 *
 * Java stack information for the threads listed above:
 * ===================================================
 * "threadBBBB":
 *         at com.wx.thread.threads.HoldLockThread.run(DeadLockDemo.java:36)
 *         - waiting to lock <0x00000000eb606fa8> (a java.lang.String)
 *         - locked <0x00000000eb606fe0> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:745)
 * "threadAAAA":
 *         at com.wx.thread.threads.HoldLockThread.run(DeadLockDemo.java:36)
 *         - waiting to lock <0x00000000eb606fe0> (a java.lang.String)
 *         - locked <0x00000000eb606fa8> (a java.lang.String)
 *         at java.lang.Thread.run(Thread.java:745)
 *
 * Found 1 deadlock.
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA="lockA";
        String lockB="lockB";
        new Thread(new HoldLockThread(lockA,lockB),"threadAAAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"threadBBBB").start();
    }
}
class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t自己持有："+lockA+"\t尝试获取："+lockB);
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t自己持有："+lockB+"\t尝试获取："+lockA);
            }
        }
    }
}