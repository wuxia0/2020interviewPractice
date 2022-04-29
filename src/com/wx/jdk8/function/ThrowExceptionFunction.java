package com.wx.jdk8.function;

/**
 * @Auther: wuxia
 * @Date: 2022/04/28 16:50
 * Function 函数式接口：
 * 四种形式：
 * Supplier供给型：无无参，有返回值
 * Consumer消费型：有一个入参，无返回值
 * Runnable:无参无返回函数
 * Function:有参数有返回值
 *
 *  抛出异常接口：处理抛出异常的if
 */
@FunctionalInterface
public interface ThrowExceptionFunction {
    /** 
    * @Description: 抛出异常信息
    * @Param:  
    * @return:  
    * @Author: wuxia
    * @Date: 
    */
    void throwMessage(String message);
}
