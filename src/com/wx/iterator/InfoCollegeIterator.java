package com.wx.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * 2020-04-05
 * 信息工程学院迭代器
 */
public class InfoCollegeIterator implements Iterator {
    List<Department> departmentList;//信息工程学院以List方式存放系
    int index =-1;
    public InfoCollegeIterator(List<Department> departmentList) {//注入集合数据
        this.departmentList = departmentList;
    }

    //判断list集合中有没有下一个元素
    @Override
    public boolean hasNext() {
        if(index >= departmentList.size() -1){
        return false;
        }else{
            index +=1;
            return true;
        }
    }

    @Override
    public Object next() {
        return departmentList.get(index);
    }

    //空实现remove方法
    @Override
    public void remove() {

    }
}
