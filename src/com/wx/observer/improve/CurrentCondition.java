package com.wx.observer.improve;

/**
 * 2020-04-04
 * 显示当前天气情况（可以理解为天气气象局自己的网站）
 */
public class CurrentCondition implements Observer{
    private float temperature;
    private float pressure;
    private float humidity;

    //更新天气情况，由WeatherData来调用，使用推送模式
    public  void  update(float temperature,float pressure,float humidity){
        this.temperature=temperature;
        this.pressure=pressure;
        this.humidity=humidity;
        display();

    }
    //显示天气
    public void display(){
        System.out.println("***today mTemprature:"+temperature+"***");
        System.out.println("***today pressure:"+pressure+"***");
        System.out.println("***today humidity:"+humidity+"***");
    }
}
