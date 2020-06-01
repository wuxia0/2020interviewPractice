package com.wx.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * 输出类
 */
public class OutputImpl {
    //学院集合
    List<College> collegeList;

    public OutputImpl(List<College> collegeList) {//注入学院集合
        this.collegeList = collegeList;
    }
    //遍历所偶的学院，然后调用printDepartment，然后输出各个学院的系
    public void printCollege(){
        //从collegeList取出所有的学院
        Iterator<College> collegeIterator = collegeList.iterator();
        while (collegeIterator.hasNext()){
            //取出一个学院
           College college =  collegeIterator.next();
           System.out.println("=========="+college.getName()+"==========");
           printDepartment(college.createIterator());//得到对应的迭代器
        }
    }

    //输出 ：学院输出系
    public void printDepartment(Iterator iterator){//给个迭代器，才能遍历
        while(iterator.hasNext()){
            Department department = (Department) iterator.next();//不管存储方式时数组，还是list
            System.out.println(department.getName());
        }
    }
}
