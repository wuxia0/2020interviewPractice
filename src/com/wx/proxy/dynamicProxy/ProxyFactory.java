package com.wx.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建代理工厂
 * 方法二：
 */
public class ProxyFactory {
    public Object target;//目标对象
    public ProxyFactory(Object target){//注入目标对象
        this.target=target;
    }
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理对象开始");
                Object result = method.invoke(target,args);
                System.out.println("代理对象结束");
                return result;
            }
        });
    }
}

class ProxyFactoryDemo{
    public static void main(String[] args) {
        Animal animal = new Cat();
        Animal proxyFactory = (Animal) new ProxyFactory(animal).getProxyInstance();
        proxyFactory.speak();
    }
}