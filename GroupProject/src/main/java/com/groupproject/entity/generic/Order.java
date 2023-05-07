package com.groupproject.entity.generic;

import java.util.ArrayList;

public class Order {
    private Account orderOwner;
    private ArrayList<OrderDetail> orderDetailList;
    private double totalPrice;

    public Order(Account user){
        orderOwner = user;
        orderDetailList = new ArrayList<>();
    }

    public void addOrderDetail(OrderDetail orderDetail){
        orderDetailList.add(orderDetail);
    }

    public ArrayList<OrderDetail> getOrderDetailList(){
        return orderDetailList;
    }

    public void setTotalPrice(double price){
        totalPrice = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderOwner=" + orderOwner +
                ", orderDetailList=" + orderDetailList +
                '}';
    }
}
