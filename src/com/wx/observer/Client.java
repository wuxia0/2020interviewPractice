package com.wx.observer;

public class Client {
    public static void main(String[] args) {
        CurrentCondition currentCondition = new CurrentCondition();
        WeatherData weatherData = new WeatherData(currentCondition);
        //更新天气信息
        weatherData.setData(30,150,40);
        //天气发生变化
        System.out.println("天气发生变化......");
        weatherData.setData(44,456,55);
    }
}
