package com.groupproject.entity.generic;

import com.groupproject.entity.runtime.EntityHandler;

import java.util.ArrayList;

public class Order {
    private Account owner;
    private ArrayList<OrderDetail> orderDetailList;
    private double totalPrice;


    public Order(Account user){
        owner = user;
        orderDetailList = new ArrayList<>();
    }

    public void addOrderDetail(OrderDetail orderDetail){
        orderDetailList.add(orderDetail);
    }

    static public Order getNewOrder(ArrayList<CartDetail> cartDetailList){
        Order newOrder = new Order(EntityHandler.getCurrentUser());

        for (CartDetail cartDetail : cartDetailList){
            OrderDetail orderDetail = new OrderDetail(cartDetail, newOrder);

            newOrder.addOrderDetail(orderDetail);
            newOrder.updateTotalPrice(cartDetail.getTotalPrice());
        }

        return newOrder;
    }

    public void updateTotalPrice(double price){
        totalPrice += price;
    }

    public ArrayList<OrderDetail> getOrderDetailList(){
        return orderDetailList;
    }

    public void setTotalPrice(double price){
        totalPrice = price;
    }

    public String getOrderInfo(){
        String orderInfo = "";
        for (OrderDetail orderDetail : orderDetailList){
            orderInfo += orderDetail.getOrderDetailInfo() + "~";
        }

        return orderInfo;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderOwner=" + owner +
                ", orderDetailList=" + orderDetailList +
                '}';
    }
}
