package com.wx.jdk8.designModel.template;

/**
 * @Auther: wuxia
 * @Date: 2022/04/28 17:43
 * 积分的具体模板
 */
public class PushScoreTemplate extends AbstractPushTemplate{
    @Override
    protected void execute(int customerId, String shopName) {
        System.out.println("会员：" + customerId + "，你好，" + shopName + "送你1000000个积分");
    }
}

