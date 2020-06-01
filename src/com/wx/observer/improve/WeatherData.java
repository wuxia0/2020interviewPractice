package com.wx.observer.improve;
import java.util.ArrayList;

/**
 * 该类是核心
 * 含有观察者集合，使用ArrayList集合
 * 含有CurrentCondition 对象
 * 当有数据更新时，就主动调用含有CurrentCondition对象的update方法（含有display方法）
 */
public class WeatherData implements Subject {
    private float temperature;
    private float pressure;
    private float humidity;
    //观察者集合
    private ArrayList<Observer>observers;

    public WeatherData() {
        this.observers = new ArrayList<Observer>();
    }

    public void dataChange(){
        notifyObserver();
    }
    //当数据有更新时，就调用setData
    public void setData(float temperature,float pressure,float humidity){
        this.temperature=temperature;
        this.pressure=pressure;
        this.humidity=humidity;
        //调用这个方法，推送给调用者
        dataChange();
    }
    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    //注册一个观察者
    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    //移除一个观察者
    @Override
    public void remove(Observer o) {
        if(observers.contains(o))
        observers.remove(o);
    }

    //遍历所有的观察者，并通知
    @Override
    public void notifyObserver() {
        for (int i=0;i<observers.size();i++){
            observers.get(i).update(this.temperature,this.pressure,this.humidity);
        }
    }
}
