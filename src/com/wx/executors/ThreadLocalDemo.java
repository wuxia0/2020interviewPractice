package com.wx.executors;

/**
 * 2020-04-11
 * 需求：
 * 线程A 设置变量1，获得变量1
 * 线程B 设置变量2，获得变量2
 * 没使用ThreadLocal会反生线程A,设置变量1，获得变量2的结果。这肯定是不行的
 *
 * ThreadLocal ：
 *      1.set()将变量绑定到当前线程
 *      2.get()获取当前线程绑定的变量
 */
public class ThreadLocalDemo {
    private String name;
    ThreadLocal threadLocal = new ThreadLocal();

    public static void main(String[] args) {
        ThreadLocalDemo t = new ThreadLocalDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                t.setName(Thread.currentThread().getName()+"\t的数据");
                System.out.println("-------------------");
                System.out.println(Thread.currentThread().getName()+"获得\t"+t.getName());
            },"线程"+String.valueOf(i)).start();
        }
    }
    public String getName() {
        //return name;
        return (String) threadLocal.get();
    }
    public void setName(String name) {
        //将name绑定到当前线程
        // this.name = name;
        threadLocal.set(name);
    }
}
