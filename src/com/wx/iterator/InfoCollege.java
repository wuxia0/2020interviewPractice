package com.wx.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 2020-04-05 信息学院
 */
public class InfoCollege implements College {
    List<Department> departmentList;//以list形式存放系

    public InfoCollege() {
        departmentList = new ArrayList<Department>();
        addDepartment("信息安全系","信息安全系");
        addDepartment("网络安全系","网络安全系");
        addDepartment("服务器安全系","服务器安全系");
    }

    @Override
    public String getName() {
        return "信息工程学院";
    }

    //真正添加数据的地方
    @Override
    public void addDepartment(String name, String desc) {
       Department department = new Department(name, desc);
       departmentList.add(department);
    }

    //返回迭代器
    @Override
    public Iterator createIterator() {
        return new InfoCollegeIterator(departmentList);
    }
}
