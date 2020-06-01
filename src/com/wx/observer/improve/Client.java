package com.wx.observer.improve;

import java.util.Observable;

public class Client {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        //创建观察者
        CurrentCondition currentCondition = new CurrentCondition();
        BaiduSite baiduSite = new BaiduSite();

        //注册到weatherdata
        weatherData.register(currentCondition);
        weatherData.register(baiduSite);

        //测试
        System.out.println("通知各个注册的观察者");
        weatherData.setData(10f,100f,33f);

        //移除一个
        System.out.println("=====================");
        weatherData.remove(baiduSite);
        System.out.println("通知各个注册的观察者");
        weatherData.setData(10f,100f,33f);

        //Observable 是类，不是接口，类中已经实现了核心方法
    }
}
