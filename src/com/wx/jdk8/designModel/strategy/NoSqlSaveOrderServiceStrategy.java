package com.wx.jdk8.designModel.strategy;

/**
 * @Auther: wuxia
 * @Date: 2022/04/28 17:20
 */
public class NoSqlSaveOrderServiceStrategy implements OrderService{
    @Override
    public void save(String orderNo) {
        System.out.println("order:" + orderNo + " save to nosql");
    }
}

