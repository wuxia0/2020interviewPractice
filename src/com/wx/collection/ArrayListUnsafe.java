package com.wx.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ArrayList线程不安全--？并发争抢导致的，new 30个线程就会报concurrentModifiedException异常
 * HashSet线程不安全
 */
public class ArrayListUnsafe {
    public static void main(String[] args) {
        Collections.synchronizedSet(new HashSet());
        //List<String> list = new ArrayList<>();//线程不安全
        //1.vector 线程安全
        //2.线程安全 Collections.synchronizedList(new ArrayList<>())
       List<String> list = new ArrayList<>(Collections.synchronizedList(new ArrayList<>()));
        //3.CopyOnWriteArrayList 线程安全  为什么呢？ 写时复制，读写分离，
        //写的时候，加上锁，然后复制一个比旧数组大的新的数组，然后把新加的那个数据，放到数组最后，
        // 然后把新的数组塞回类里面，并释放锁
//        public boolean add(E e) {
//            final ReentrantLock lock = this.lock;
//            lock.lock();
//            try {
//                Object[] elements = getArray();
//                int len = elements.length;
//                Object[] newElements = Arrays.copyOf(elements, len + 1);
//                newElements[len] = e;
//                setArray(newElements);
//                return true;
//            } finally {
//                lock.unlock();
//            }
//        }
//        CopyOnWriteArrayList list = new CopyOnWriteArrayList(new ArrayList());
//        for (int i=1;i<=30;i++){
//            new Thread(()->{
//                list.add(UUID.randomUUID().toString().substring(0,8));
//                System.out.println(list);//.ConcurrentModificationException
//            },String.valueOf(i)).start();
//        }


        //hashSet
        //Set set= new HashSet();// 线程不安全ConcurrentModificationException
        //Set set=  Collections.synchronizedSet(new HashSet()); //线程安全
        Set set= new CopyOnWriteArraySet(new ArrayList());//线程安全
        for (int i=0;i<=30;i++){
            new Thread(()->{
                    set.add(UUID.randomUUID().toString().substring(0,8));
                    System.out.println(set);
            },String.valueOf(i)).start();
        }


    }
}
