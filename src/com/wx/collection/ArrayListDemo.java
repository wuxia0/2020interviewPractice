package com.wx.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Auther: wuxia
 * @Date: 2021/08/22/16:07
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        List<String> arrList = new ArrayList<>();
        //CopyOnWriteArrayList
        for (int i = 0; i <= 30 ; i++) {
            new Thread(()->{
                arrList.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(arrList);
            },String.valueOf(i)).start();
        }
    }

}
