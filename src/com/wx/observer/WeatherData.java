package com.wx.observer;

/**
 * 该类是核心
 * 含有CurrentCondition 对象
 * 当有数据更新时，就主动调用含有CurrentCondition对象的update方法（含有display方法）
 */
public class WeatherData {
    private float temperature;
    private float pressure;
    private float humidity;
    private CurrentCondition currentCondition;//聚合了CurrentCondition对象

    public WeatherData(CurrentCondition currentCondition) {
        this.currentCondition = currentCondition;
    }

    public void dataChange(){
        currentCondition.update(getTemperature(),getPressure(),getHumidity());
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
}
