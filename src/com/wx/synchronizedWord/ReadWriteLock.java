package com.wx.synchronizedWord;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther: wuxia
 * @Date: 2020/05/30/11:27
 * //写操作：应该独占,写的过程不能被打断，读的过程可以被打断
 * 1	 正在写入1
 * 1	写入完成
 * 4	 正在写入4
 * 4	写入完成
 * 5	 正在写入5
 * 5	写入完成
 * 3	 正在写入3
 * 3	写入完成
 * 2	 正在读取2
 * 4	 正在读取4
 * 3	 正在读取3
 * 2	读取完成
 * 4	读取完成
 * 3	读取完成
 * 2	 正在写入2
 * 2	写入完成
 * 1	 正在读取1
 * 5	 正在读取5
 * 1	读取完成
 * 5	读取完成
 */
public class ReadWriteLock {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //5个线程写
        for (int i = 1; i <=5 ; i++) {
            final int temp=i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }
        //5个线程读
        for (int i = 1; i <=5 ; i++) {
            final int temp=i;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}
class MyCache{
    private volatile Map<String,Object> map = new HashMap();
    //private Lock lock = new ReentrantLock(); //性能较低
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void put(String key,Object value){

        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入"+key);
            //暂停一会线程
            try {TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) {e.printStackTrace();}
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"\t写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }
    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取"+key);
            //暂停一会线程
            try {TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) {e.printStackTrace();}
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }
}
