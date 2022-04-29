package com.wx.jdk8.designModel.template.lambda;

import java.util.function.Consumer;

/**
 * @Auther: wuxia
 * @Date: 2022/04/28 17:48
 * 使用java8重构后，可以把三个模板类（包括抽象类模板）减少到一个
 */
public class PushTemplateLambda {
    public void push(int customerId, String shopName, Consumer<Object[]> execute) {
        System.out.println("jdk8-准备推送......");
        Object[] param = new Object[] {customerId, shopName};
        execute.accept(param);
        System.out.println("jdk8- 推送完成\n");
    }
}
class PushTemplateLambdaDemo {
    public static void main(String[] args) {
        new PushTemplateLambda().push(1, "KFC",
                (Object[] obj) -> {
                    System.out.println("会员" + obj[0] + ",你好" + obj[1] + "送你一张优惠券");
        });

        new PushTemplateLambda().push(1, "香奈儿",
                (Object[] obj) -> {
                    System.out.println("会员" + obj[0] + ",你好" + obj[1] + "送你一1000000积分");
                });
    }
}

