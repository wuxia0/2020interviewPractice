package com.wx.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类: 不需要每次都创建代理类，通过实现InvocationHandler 类，和反射动态得到代理类
 * 动态代理类的缺点：没有摆脱实现接口，如果一个类没有实现接口，则不能用jdk动态代理，如果
 * 方法被声明为final，也不能使用jdk动态代理
 * 方法一：
 */
public class Cat implements Animal {
    @Override
    public void speak() {
        System.out.println("cat class:cat can speak.....");
    }
}

/**
 *
 */
class JdkProxy implements InvocationHandler {
//这是静态代理的写法
//    private Cat cat;
//    public JdkProxy(Cat cat){
//        this.cat=cat;
//    }
    //动态代理的写法
    private Object object;//代表具体的业务类，调用具体的业务方法

    //包装调用方法，预处理
    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        System.out.println("返回代理类实例前可以干一些事情，例如打印日志等......预处理开始");
        Object result = method.invoke(object,args);
        System.out.println("预处理结束");
        return result;
    }
    //写一个bind方法，绑定业务对象，返回代理类
    public Object bind(Object object){
        this.object=object;
        System.out.println("返回代理类对象");
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }
}

class JdkProxyDemo {
    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();
        Animal cat = (Animal)jdkProxy.bind(new Cat());
        cat.speak();
    }
}