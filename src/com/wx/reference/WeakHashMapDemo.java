package com.wx.reference;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @Auther: wuxia
 * @Date: 2021/09/11/10:01
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("======================");
        weakHashMap();
    }

    private static void myHashMap() {
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key,value);
        System.out.println(map);//{1=HashMap}

        key = null;//
        System.out.println(map);//{1=HashMap}

        System.gc();
        System.out.println(map + "\t" + map.size());//{1=HashMap}	1
    }

    private static void weakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "weakHashMap";

        map.put(key,value);
        System.out.println(map);//{1=HashMap}

        key = null;//
        System.out.println(map);//{1=HashMap}

        System.gc();
        System.out.println(map + "\t" + map.size());//{}  0
    }
}
