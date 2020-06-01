package com.wx.singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * 属性需要初始化，使用这种方法初始化类属性
 */
public class Singleton2 {
    public static final Singleton2 s2 ;
    private String info;
    static {
        try {
            Properties pro = new Properties();
            pro.load(Singleton2.class.getClassLoader().getResourceAsStream("singleton.properties"));
           ;
            s2 = new Singleton2( pro.getProperty("info"));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    private Singleton2(String info){
        this.info=info;
    };

    @Override
    public String toString() {
        return "Singleton2{" +
                "info='" + info + '\'' +
                '}';
    }
}
