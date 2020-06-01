package com.wx.throwable;

/**
 * @Auther: wuxia
 * @Date: 2020/05/29/19:59
 * c验证 try里面返回return，finally里的代码会不会执行
 */
public class ThrowDemo {
    public static void main(String[] args) {
        System.out.println(add());
    }

    public static int add(){
        int a =1;
        int b=1;
        try {
            int c=a/b;
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return 10;
        } finally {
            System.out.println("try,里面有return,finally里面的代码会执行吗？会，抛异常了，也会,catch里面有retun也会执行");
            System.out.println("try,和finally里有return 时，会执行finally里的return");
            return 11;
        }
    }
}
