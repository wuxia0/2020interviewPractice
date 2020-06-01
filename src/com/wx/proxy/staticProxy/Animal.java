package com.wx.proxy.staticProxy;

/**
 * 2020-04-02 静态代理类.代理类的作用：中间隔离作用，还可以增加额外的功能
 * 业务类接口，具体业务类实现，代理类（需要实现业务类接口），客户端类
 */
public interface Animal {
    public void yeild();//1.接口 只能有public声明的方法，其他类型不被允许
   // protected  void speak();//报错
    //public void add(){}//2.接口 不允许实现方法，即方法体
    public final String name ="aaa";
    //public abstract Double d=1.0;//4.final和abstract 关键字共用
    //protected Sting a='1';//3.接口中的属性 只能被public修饰，其他类型会报错
}
