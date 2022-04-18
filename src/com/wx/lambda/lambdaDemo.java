package com.wx.lambda;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @Auther: wuxia
 * @Date: 2022/04/18/13:56
 * java8引入了一个新的操作符“->”箭头操作符或者lambda操作符
 * 箭头操作符将Lambda表达式拆分成两部分：
 * 左侧：Lambda表达式的参数列表
 * 右侧：Lambda表达式中所需执行的功能，即Lambda体
 * 依赖于函数式接口（！！！！！），Lambda表达式即对接口的实现
 * 函数式接口：只有一个方法
 *
 * 上联：左右遇一括号可省
 * 下联：左侧推断类型可省
 * 横批：能省则省
 */
@SuppressWarnings("all")
public class lambdaDemo {
    public static void main(String[] args) {
        test01();
        test02();
    }

    public static void test01() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Lambda的学习");
            }
        };
    }

    /**
     * 语法1：无参，无返回值
     */
    public static void test02() {
        Runnable runnable = ()->{
            System.out.println("Lambda的学习-用Lambda表达式简化后的代码");
        };
    }

    /**
     * 语法2：有一个参数，无返回值
     */
    public void test03() {
        Consumer<String> com = (x) -> {System.out.println(x); };
        com.accept("学习Lambda表达式");
    }
    /**
     * 语法3：只有一个参数，省略括号
     */
    public void test04() {
        Consumer<String> com = x -> {
            System.out.println("www");
        };
    }

    /**
     * 语法4：有两个以上参数，有返回值，并且Lambda表达式有多条语句
     */
    public void test05() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("ww");
            return Integer.compare(x, y);
        };
    }
    /**
     * 语法5;Lambda体中只有一条语句时，return和大括号都可以省略不写
     */
    public void test06() {
        Comparator<Integer> comparable = (x, y) -> Integer.compare(x, y);
    }
    /**
     * 语法6：参数指定类型
     */
    public void test07() {
        Comparator<Integer> comparable = (Integer x, Integer y) -> { return Integer.compare(x, y); };
    }
}
