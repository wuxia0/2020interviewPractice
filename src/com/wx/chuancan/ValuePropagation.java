package com.wx.chuancan;

/**
 * @Auther: wuxia
 * @Date: 2022/04/23 14:31
 */
/**
 * java方法调用 只有值传递，没有引用传递
 * java中永远不会传递对象本身，只传递对象的引用，当一个对象当做参数传递时，传递的是该对象的引用（即还是地址值，而不是对象本身），函数接受的
 * 的是原始值的内存地址，而不是一个副本。所以能改变原始值得内容
 * 值传递测试
 *
 * @author WX 2019-04-08
 *
 */
public class ValuePropagation {

    String str = new String("hello");
    char[] ch = {'a','b','c'};
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Person p1 = new Person("wx","nv",33);
        int a = 5;
        int b =6;
        System.out.println(p1);//Person [name=wx, sex=nv, age=33]
        changeValue(p1);
        System.out.println(p1);//Person [name=wx, sex=nv, age=22]
        changeValue1(p1);//
        System.out.println(p1);//Person [name=wx, sex=nv, age=22]
        System.out.println(a+"  "+b);//

        //String不改变，数组改变
        ValuePropagation v = new ValuePropagation();
        v.changeString(v.str, v.ch);
        System.out.println(v.str);//hello
        System.out.println(v.ch);//wbc
    }

    public static void changeValue(Person p){
        Person p2 = p;
        p2.setAge(22);//原始值会发生改变
    }
    public static void changeValue1(Person p){
        Person p2 = new Person("wx","nv",33);
        p2.setAge(44);//原始值不会发生改变，因为 引用的地址改变了，指向了一个新的对象，新对象的内容改变，不会对原始对象造成任何影响
    }
    public void change(int a,int b){
        int temp =a;//改变的值得副本
        a=b;
        b=temp;
    }

    public void changeString(String str,char ch[]){
        str = "change";//因为String不可变性 ，相当于 String str = new String(); 改变了对象的引用地址，不再指向原始的对象
        ch[0]='w';
    }
}
