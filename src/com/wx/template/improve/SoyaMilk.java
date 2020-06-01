package com.wx.template.improve;

/**
 * 2020-04-03  模板模式练习
 * 重点：父类只负责定义顺序，不关心具体实现；子类做具体实现，不关系顺序
 * 抽象类，表示豆浆
 */
public abstract class SoyaMilk {
    //模板方法，可以做成final 不让子类去覆盖
    final void make(){
        select();
        if (customerWantCondiments()){
            addCondiments();
        }
        soak();
        beat();
    }
    //选材料
    void select(){
        System.out.println("第一步：选好新鲜的黑豆...");
    }
    //添加不同的配料，抽象方法，让子类去实现
    abstract void addCondiments();

    //浸泡
    void soak(){
        System.out.println("第三步：黄豆和材料开始浸泡，浸泡三个小时....");
    }
    void beat(){
        System.out.println("第四步：黄豆和配料放到豆浆机打碎...");
    }
    //钩子方法：定义一个方法，默认不做任何事情，子类视情况要不要覆盖它，则称该方法为钩子方法
    boolean customerWantCondiments(){
        return true;
    }
}
