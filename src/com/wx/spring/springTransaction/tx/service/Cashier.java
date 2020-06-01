package com.wx.spring.springTransaction.tx.service;

import java.util.List;

/**
 * @Auther: wuxia
 * @Date: 2020/05/29/11:53
 */
public interface Cashier {
    void checkout(int userid, List<String> isbns);
}
