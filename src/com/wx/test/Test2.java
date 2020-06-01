package com.wx.test;



import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test2 {
    public static void main(String[] args) {
       //List.of(1,2).stream().peek(System.out::println).limit(1);
        Person person = new Person("ww",GENDER.MALE);
        Person person1 = new Person("wwss",GENDER.FEMALE);
        Person person11 = new Person("wddw",GENDER.MALE);
        Person person2 = new Person("wxdd",GENDER.FEMALE);
        Stream stream = Stream.of(person,person1,person11,person2);
        System.out.println(stream.collect(Collectors.toMap(person.getSex(),person.getName())));
        //List map = List.of(1);
       // List list = new ArrayList(map);
       // list.stream().forEach(i ->list.add(i + 1));
       // System.out.println(list);
    }
}
class Person{
    GENDER sex;
    String name;

    public GENDER getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public Person(String name,GENDER sex ) {
        this.sex = sex;
        this.name = name;
    }
}
enum GENDER{
    MALE,FEMALE
}
