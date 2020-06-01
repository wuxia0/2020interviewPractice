package com.wx.proxy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * cglib代理，不需要实现接口，但是要实现methodInterceptor接口，需要引入相关jar包
 *  但是有继承关系，所以被代理的类不能被final 关键字修饰
 *  底层原理是字节码技术
 *  遇到的问题：
 *  cglib-3.3.0.jar  当引入这个jar包是会报错：Exception in thread "main" java.lang.NoClassDefFoundError: org/objectweb/asm/Type
 *  cglib-nodep-3.3.0.jar：改成这个jar包就好了
 */
public class CglibProxyFactory implements MethodInterceptor {
    private Object target;//引入目标类
    public CglibProxyFactory(Object target){//注入目标类
        this.target=target;
    }
    //返回代理对象，是target的代理对象
    public Object getCglibProxyInstance(){
        //1.创建增强器
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(target.getClass());
        //3.回调
        enhancer.setCallback(this);
        //4.返回子类即代理对象
        return enhancer.create();
    }
    //会调用目标对象方法
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib代理~~开始");
        Object result = method.invoke(target,objects);
        System.out.println("cglib代理~~结束");
        return result;
    }
}
class CglibProxyDemo{
    public static void main(String[] args) {
        //创建目标类
        Cat target = new Cat();
        //创建代理对象
        Cat cglibProxyFactory =(Cat) new CglibProxyFactory(target).getCglibProxyInstance();
        //执行代理类方法，会触发intercept方法
        cglibProxyFactory.speak();
    }
}