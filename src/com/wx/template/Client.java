package com.wx.template;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) {
        //制作红豆豆浆
        System.out.println("-------制作红豆豆浆------");
        SoyaMilk redMilk = new RedBeanSoyaMilk();
        redMilk.make();
        System.out.println("-------制作花生豆浆------");
        SoyaMilk peanutMilk = new PeanutBeanMilk();
        peanutMilk.make();
    }
}
