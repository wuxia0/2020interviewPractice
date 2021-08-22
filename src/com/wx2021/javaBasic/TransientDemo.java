package com.wx2021.javaBasic;

import java.io.Serializable;

/**
 * @Auther: wuxia
 * @Date: 2021/08/15/14:42
 */
public class TransientDemo {
    public static void main(String[] args) {
        User user = new User();
        user.setAge(18);
        user.setName("practice");
        System.out.println(user);
    }

}

class User implements Serializable {
    private String name;
    private transient int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}