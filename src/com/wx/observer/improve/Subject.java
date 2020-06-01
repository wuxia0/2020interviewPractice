package com.wx.observer.improve;

/**
 * 2020-04-04
 * 观察者模式 接口
 */
public interface Subject {
    public void register(Observer o);
    public void remove(Observer o);
    public void notifyObserver();
}
