package com.wx.proxy.staticProxy;

/**
 *静态代理类的弊端：每次都要实现一个业务 都要创建不同的代理类，
 */
public class Bird implements Animal {
    public void yeild(){
        System.out.println("Bird call yeild method ....");
    }
}

class Proxy implements Animal{
  // Bird bird = new Bird();//方法一：直接在代理类new业务类，耦合性太高，可以用构造方法注入
//    private Bird bird;
////    public Proxy(Bird bird){
////        this.bird=bird;
////    }
   private Animal bird;
   public Proxy (Animal bird){//通过接口来聚合
       this.bird=bird;
   }
    public void yeild(){
        System.out.println("proxy class ...proxy can add others methods");
        bird.yeild();
    }
}
class ProxyDemo {
    public static void main(String[] args) {
       // Proxy proxy = new Proxy();
        Proxy proxy = new Proxy(new Bird());
        proxy.yeild();
    }
}