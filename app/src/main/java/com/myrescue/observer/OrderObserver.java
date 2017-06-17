package com.myrescue.observer;

import java.util.Observable;

/**
 * 抽象主题角色的子类
 */
public class OrderObserver extends Observable{

    /* 抢单状态
     * 1 抢单 2 抢单成功 3 抢单失败  4 订单完成   */
    public static final String ORDERTYPE_UNPAYMENT = "10";
    public static final String ORDERTYPE_SUBMIT = "20";
    public static final String ORDERTYPE_RECEIVEORDER = "30";
    public static final String ORDERTYPE_DISTRIBUTION = "40";


    //单例模式
    private OrderObserver(){}
    private static OrderObserver orderObserver = new OrderObserver();
    public static OrderObserver getInstance() {
        return orderObserver;
    }

    //到达目的地更改UI效果
    public void changeUI(Object obj){
        setChanged();
        notifyObservers(obj);
    }
}
