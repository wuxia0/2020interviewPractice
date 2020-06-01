package com.wx.staticTest;

/**
 * @Auther: wuxia
 * @Date: 2020/05/28/21:56
 * 测试静态方法不能被重写，但是可以被重定义。
 * 静态方法是在编译期就被选好的
 */
public class TestStaticMethod {
    public static void main(String[] args) {
        SubClass s = new SubClass();
        Parent p = s;
        Parent p1 = new SubClass();
        p.testStatic();// 打印父类方法。
        s.testStatic();// 打印子类方法。
        p1.testStatic();//父类方法
    }
}
class Parent {
    public static void testStatic() {
        System.out.println("父类方法");
    }
}
class SubClass extends Parent {
    public static void testStatic() {
        System.out.println("子类方法 ");
    }
}


