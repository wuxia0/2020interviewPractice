package com.wx.refect;

public class Student {
    private String name;
    public String desc;
    protected int age;

    private void eat(String name){
        System.out.println(name+" eating....");
    }
    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String name) {
        this.name = name;
    }

    protected Student(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", age=" + age +
                '}';
    }
}
