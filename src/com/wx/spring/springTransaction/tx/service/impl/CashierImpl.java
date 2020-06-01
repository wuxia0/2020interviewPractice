package com.wx.spring.springTransaction.tx.service.impl;

import com.wx.spring.springTransaction.tx.service.BookService;
import com.wx.spring.springTransaction.tx.service.Cashier;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: wuxia
 * @Date: 2020/05/29/11:54
 */

public class CashierImpl implements Cashier {
    @Resource
    private BookService bookService;
    @Override
    public void checkout(int userid, List<String> isbns) {
        for (String isbn:isbns){

        }
    }
}
