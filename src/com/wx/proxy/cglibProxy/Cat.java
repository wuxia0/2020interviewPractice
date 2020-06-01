package com.wx.proxy.cglibProxy;

/**
 * 2020-04-02
 * cglib动态代理，利用了字节码技术，和拦截方法。虽然没用实现，但是摆脱不了继承
 *
 */
public class Cat {
    public void speak(){
        System.out.println("cglib ->Cat class :real business class......");
    }
}


