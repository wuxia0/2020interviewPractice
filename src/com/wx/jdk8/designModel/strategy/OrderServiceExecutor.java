package com.wx.jdk8.designModel.strategy;

/**
 * @Auther: wuxia
 * @Date: 2022/04/28 17:22
 * 使用策略的辅助“容器”
 */
public class OrderServiceExecutor {
    private final OrderService orderService;

    public OrderServiceExecutor(OrderService orderService) {
        this.orderService = orderService;
    }
    public void save(String orderNo) {
        this.orderService.save(orderNo);
    }
}

class OrderServiceDemo {
    public static void main(String[] args) {
        OrderServiceExecutor executor = new OrderServiceExecutor(new MySqlSaveOrderServiceStrategy());
        executor.save("001");

        OrderServiceExecutor executor1 = new OrderServiceExecutor(new NoSqlSaveOrderServiceStrategy());
        executor.save("002");

        System.out.println("重构后，可以省去2个策略实现类-------------------------");
        OrderServiceExecutor executor2 = new OrderServiceExecutor(
                (String orderNo) ->{
                    System.out.println("order: " + orderNo + " save to mysql");
        });

        OrderServiceExecutor executor3 = new OrderServiceExecutor(
                (String orderNo) ->{
                    System.out.println("order: " + orderNo + " save to nosql");
                });
        executor2.save("001");
        executor2.save("002");
    }
}

