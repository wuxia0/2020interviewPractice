package com.wx.jdk8.function;

/**
 * @Auther: wuxia
 * @Date: 2022/04/28 17:01
 */
public class ConsumerFunctionDemo {
    public static void main(String[] args) {
        VUtils.isTrue(true).throwMessage("参数为true,俺要抛出异常哦.....摆烂吧");
    }
}

