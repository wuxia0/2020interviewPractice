package com.wx.iterator;

import java.util.Iterator;

/**
 * 计算机学院
 */
public class ComputerCollege implements College {
    Department[] departments;
    int numOfDepartment = 0;//保存当前数组的对象个数

    public ComputerCollege() {
        departments = new Department[5];//假如最多只能放5个元素。初始化
        addDepartment("java专业","java专业");
        addDepartment("PHP专业","PHP专业");
        addDepartment("大数据专业","大数据专业");
        addDepartment("C++专业","C++专业");
    }

    @Override
    public String getName() {
        return "计算机学院";
    }

    //真正的数据是在这里加入的
    @Override
    public void addDepartment(String name, String desc) {
        Department department =new Department(name,desc);
        departments[numOfDepartment]=department;//因为是数组，要指定加的位置
        numOfDepartment +=1;//表示已经加了一个系
    }

    //返回一个迭代器
    @Override
    public Iterator createIterator() {
        return new ComputerCollegeIterator(departments);//要把departments 传进去，迭代器才知道怎么遍历
    }
}
