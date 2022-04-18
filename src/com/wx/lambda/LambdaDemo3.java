package com.wx.lambda;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/**
 * @Auther: wuxia
 * @Date: 2022/04/18/16:18
 * Lambda学习之对象引用和方法引用
 */
public class LambdaDemo3 {
    public static void main(String[] args) {
        test01();
        test03();
    }
    //对象:: 实例名
    public static void test01() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("123456");

        PrintStream printStream = System.out;
        Consumer<String> consumer = System.out::println;
        consumer.accept("12345");
    }

    //类::静态方法名
    public static void test02() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> comparator = Integer::compare;
    }

    //类:: 实例方法名 ????
    public static void test03() {
        //需求：比较两个字符串是否相等
        //要求：第一个参数是实例方法的调用者，第二个参数是方法的传入参数
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        BiPredicate<String, String> bp2 = String::equals;
    }

}
