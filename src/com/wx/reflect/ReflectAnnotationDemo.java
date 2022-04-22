package com.wx.reflect;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * @Auther: wuxia
 * @Date: 2022/04/18/11:19
 * 练习 通过反射获取注解
 */
public class ReflectAnnotationDemo {
    public static void main(String[] args) throws Exception {
        Class<?> s = Class.forName("com.wx.reflect.Student2");
        //通过反射获取注解
        Annotation[] annotations = s.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
        //通过反射获取注解value的值
        TableXia tableXia = s.getAnnotation(TableXia.class);
        String value = tableXia.value();
        System.out.println(value);

        //通过反射获取类指定的注解
        Field f = s.getDeclaredField("name");
        FieldXia ann = f.getAnnotation(FieldXia.class);
        System.out.println(ann.columnName());
        System.out.println(ann.length());
        System.out.println(ann.type());
    }

}

@TableXia("da_student")
class Student2 {
    @FieldXia(columnName = "id",type="int",length = 3)
    private int id;
    @FieldXia(columnName = "age",type = "int",length = 4)
    private int age;
    @FieldXia(columnName = "name",type = "String",length = 5)
    private String name;

    public Student2() {
    }

    public Student2(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

//类的注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableXia{
    String value();
}
//属性的注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldXia{
    String columnName();
    String type();
    int length();
}