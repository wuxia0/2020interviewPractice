package com.wx.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 *客户端调用
 */
public class Client {
    public static void main(String[] args) {
        //创建学院
        List<College> collegeList = new ArrayList<College>();
        ComputerCollege computerCollege= new ComputerCollege();
        InfoCollege infoCollege = new InfoCollege();
        collegeList.add(computerCollege);
        collegeList.add(infoCollege);

        //输出
        OutputImpl output = new OutputImpl(collegeList);
        output.printCollege();
    }
}
