package com.wx.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auther: wuxia
 * @Date: 2022/04/18/10:18
 * 普通new对象 和通过反射创建对象，和关闭检测创建对象的性能对比
 */
public class ReflectDemo2 {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        test01();
        test02();
        test03();
    }
    public static void test01(){
        Student student = new Student();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            student.getName();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("普通方式创建对象需要的时间" + (endTime-startTime) + "ms");
    }

    public static void test02() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Student student = new Student();
        Class s = student.getClass();
        Method m = s.getDeclaredMethod("getName", null);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            m.invoke(student, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("反射方式创建对象需要的时间" + (endTime-startTime) + "ms");
    }

    public static void test03() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Student student = new Student();
        Class s = student.getClass();
        Method m = s.getDeclaredMethod("getName", null);
        m.setAccessible(true);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            m.invoke(student, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("关闭检测方式创建对象需要的时间" + (endTime-startTime) + "ms");
    }
}
