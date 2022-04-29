package com.wx.jdk8.designModel.template;

/**
 * @Auther: wuxia
 * @Date: 2022/04/28 17:44
 */
public class TemplateDemo {
    public static void main(String[] args) {
        AbstractPushTemplate template1 = new PushCouponTemplate();
        template1.push(1, "KFC");
        AbstractPushTemplate template2 = new PushScoreTemplate();
        template2.push(1, "香奈儿");


    }

}

