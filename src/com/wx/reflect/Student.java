package com.wx.reflect;

public class Student {
    private String name;
    public String desc;
    protected int age;
    private String like;
    private String classroom;

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

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
