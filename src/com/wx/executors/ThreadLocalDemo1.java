package com.wx.executors;
/**
 * 2020-04-11
 * 使用synchornized解决线程不隔离的问题
 */
public class ThreadLocalDemo1 {
    private String name;
    public static void main(String[] args) {
        ThreadLocalDemo1 t = new ThreadLocalDemo1();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                synchronized (ThreadLocalDemo1.class) {
                    t.setName(Thread.currentThread().getName() + "\t的数据");
                    System.out.println("-------------------");
                    System.out.println(Thread.currentThread().getName() + "获得\t" + t.getName());
                }
            },"线程"+String.valueOf(i)).start();
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
         this.name = name;
    }
}
