package com.example.amyas.hotelfamily.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * author: Amyas
 * date: 2017/11/16
 */

public class OrderLab {
    private static OrderLab sOrderLab;
    private List<Order> mOrderList;

    private OrderLab(){
        mOrderList = new ArrayList<>();
        init();
    }

    public static OrderLab get(Context context){
        if (sOrderLab==null){
            sOrderLab = new OrderLab();
        }
        return sOrderLab;
    }
    private void init(){
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setAvailable(i%2==0);
            order.setDesk_number(i);
            order.setPrice(i*300);
            mOrderList.add(order);
        }
    }
    public List<Order> getOrderList(){
        return mOrderList;
    }
    public Order getOrder(UUID uuid){
        for (Order order : mOrderList) {
            if (order.getUUID().equals(uuid)){
                return order;
            }
        }
        return null;
    }
}
