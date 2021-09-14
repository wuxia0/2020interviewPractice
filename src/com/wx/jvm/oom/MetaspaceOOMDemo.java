package com.wx.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther: wuxia
 * @Date: 2021/09/12/9:03
 * JVM参数：
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 * Java8使用元空间替代永久代
 * 元空间使用本地内存
 * 元空间存储了哪些信息：
 *  虚拟机加载类的信息
 *  常量池
 *  静态变量
 *  即使编译后的代码
 *
 *  模拟元空间溢出，我们不断生成类往元空间灌，类占据的空间总是会超过Metespace指定的空间大小
 *  java.lang.OutOfMemoryError: Metaspace
 */
public class MetaspaceOOMDemo {
    static class OOMDemo{ }

    public static void main(String[] args) {
        int i = 0;
        try {
            while(true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMDemo.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e) {
            System.out.println("************************多少次后发生异常i:" + i);
            e.printStackTrace();
        }
    }
}
