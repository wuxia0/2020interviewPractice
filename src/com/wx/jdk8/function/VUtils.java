package com.wx.jdk8.function;

/**
 * @Auther: wuxia
 * @Date: 2022/04/28 16:57
 */
public class VUtils {
    /** 
    * @Description: 如果参数为true则抛出异常 
    * @Param:  
    * @return:  
    * @Author: wuxia
    * @Date: 
    */
    public static ThrowExceptionFunction isTrue(boolean b) {
        return (errorMessage) -> {
            if (b) {
                throw new RuntimeException(errorMessage);
            }
        };
    }
}

