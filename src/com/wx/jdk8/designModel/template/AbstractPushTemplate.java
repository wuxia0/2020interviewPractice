package com.wx.jdk8.designModel.template;

/**
 * @Auther: wuxia
 * @Date: 2022/04/28 17:37
 * 抽象模板类
 */
public abstract class AbstractPushTemplate {
    public void push (int customerId, String shopName) {
        System.out.println("摆烂，准备推送.....");
        execute(customerId, shopName);
        System.out.println("栓Q,推送完成\n");
    }

    protected abstract void execute(int customerId, String shopName);
}

