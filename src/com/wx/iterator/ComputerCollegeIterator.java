package com.wx.iterator;

import java.util.Iterator;

/**
 * 2020-04-05 计算机学院迭代器
 */
public class ComputerCollegeIterator implements Iterator {
    //我们需要知道department 是以怎样的方式存储
    Department[] departments;//计算机学院以数组的方式存放系
    int position =0;//遍历的位置

    public ComputerCollegeIterator(Department[] departments) {//注入数组数据
        this.departments = departments;
    }

    @Override
    public boolean hasNext() {
        if(position >= departments.length || departments[position] == null){
            return  false;
        }
        return true;
    }

    @Override
    public Object next() {
        Department department = departments[position];
        position +=position+1;
        return department;
    }

    public void remove(){

    }
}
