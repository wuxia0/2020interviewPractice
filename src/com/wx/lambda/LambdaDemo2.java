package com.wx.lambda;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Auther: wuxia
 * @Date: 2022/04/18/15:13
 * Lambda四大内置核心函数式接口
 * 消费型接口：一个参数，无返回值
 * 供给型接口：无参数，有返回值
 * 函数型接口：一个参数，有返回值
 * 断言型接口：有参数，返回值为boolean类型
 */
public class LambdaDemo2 {
    public static void main(String[] args) {
        happy(1000, (m) -> System.out.println("消费型Consumer"));
        List<Integer> numList = getNumList(5, () -> (int)(Math.random()) * 100 );
        numList.forEach(System.out::println);

        System.out.println(strHander("java学习:", (x) ->  x +"Lambda学习"));
    }
    //消费型consumer
    //需求：传入一个参数做业务处理，不需要返回值
    public static void happy(double money, Consumer<Double> com) {
        com.accept(money);
    }

    //供给型接口：Supplier
    //需求：产生指定数量的整数，放到集合中，返回集合
    public static List<Integer> getNumList(int num, Supplier<Integer> sp) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            result.add(sp.get());
        }
        return result;
    }

    //函数型接口：
    //需求：传入一个字符串，返回一个字符串
    public static String strHander(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

}
