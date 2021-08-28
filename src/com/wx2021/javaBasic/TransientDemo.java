package com.wx2021.javaBasic;

import java.io.*;

/**
 * @Auther: wuxia
 * @Date: 2021/08/15/14:42
 * transient 练习
 */
public class TransientDemo {
    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setAge(18);
        user.setAge2(22);
        user.setName("practice");
        System.out.println("序列化开始------------");
        userSerializble(user);
        System.out.println("反序列化开始--------------");
        userDeseriazable();

    }

    public  static void userSerializble (User user) throws FileNotFoundException, IOException {
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("E:/User202108.txt")));
        System.out.println("user:" + user);
        oo.writeObject(user);
        System.out.println("User serialazble success!");
        oo.close();
    }

    public static User userDeseriazable() throws Exception,IOException{
        ObjectInputStream oi = new ObjectInputStream(new FileInputStream(
                new File("E:/User202108.txt")));
        User user = (User) oi.readObject();
        System.out.println("user2:" + user);
        System.out.println("DeSerialazble Object success!");
        return user;
    }

}

class User implements Serializable {
    private String name;
    private transient int age;//加了transient不会被序列化
    private int age2;//会被序列化

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

    public int getAge2() {
        return age2;
    }

    public void setAge2(int age2) {
        this.age2 = age2;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", age2=" + age2 +
                '}';
    }
}