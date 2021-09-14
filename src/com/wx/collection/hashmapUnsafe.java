package com.wx.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * hashmap线程不安全
 */
public class hashmapUnsafe {
    public static void main(String[] args) {
        Map map1 = new HashMap<>();//线程不安全 ConcurrentModificationException
        Map map2 = Collections.synchronizedMap(new HashMap());//线程安全
        Map map = new ConcurrentHashMap(new HashMap());
        for (int i=0;i<30;i++){
            new Thread(()->{
                map.put(UUID.randomUUID().toString().substring(0,9), "1");
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
