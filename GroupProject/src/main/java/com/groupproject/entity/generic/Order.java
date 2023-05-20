package com.groupproject.entity.generic;

import com.groupproject.entity.Constant.ConstantOrder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {
    private Account user;
    private ArrayList<OrderDetail> orderDetailList;
    private LocalDateTime date;
    private double totalPrice;
    private ConstantOrder.OrderDuration duration;

    public Order() {
        orderDetailList = new ArrayList<>();
    }

    public Order(Account user, ArrayList<CartDetail> cartDetailList,
                 ConstantOrder.OrderDuration duration) {
        this.user = user;
        this.duration = duration;
        orderDetailList = new ArrayList<>();
        date = LocalDateTime.now();

        for (CartDetail cartDetail : cartDetailList) {
            OrderDetail orderDetail = new OrderDetail(cartDetail);
            addOrderDetail(orderDetail);
        }
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetailList.add(orderDetail);
        orderDetail.setRootOrder(this);
        updateTotalPrice(orderDetail.getPrice());
    }

    public void updateTotalPrice(double price) {
        totalPrice += price;
    }

    public void setDate(String date) {
        this.date = LocalDateTime.parse(date);
    }

    public void setDuration(int durationIndex) {
        // this.duration = orderDuration;
        duration = ConstantOrder.OrderDuration.values()[durationIndex];
    }

    public void setUser(Account user) {
        this.user = user;
    }


    public int getDuration(){
        return duration.getDurationValue();
    }

    public LocalDateTime getOrderTime() {
        return date;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MMMM-dd"));
    }

    public ArrayList<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public String getOrderInfo() {
        String orderInfo = "";

        orderInfo += date + "~";
        orderInfo += duration.ordinal() + "~";
        for (OrderDetail orderDetail : orderDetailList) {
            orderInfo += orderDetail.getOrderDetailInfo() + "~";
        }

        return orderInfo;
    }

    public ArrayList<OrderDetail> getReturnedOrderDetailList() {
        ArrayList<OrderDetail> returnedOrderDetailList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            if (orderDetail.isReturned()) {
                returnedOrderDetailList.add(orderDetail);
            }
        }
        return returnedOrderDetailList;
    }

    public ArrayList<OrderDetail> getRentingOrderDetailList() {
        ArrayList<OrderDetail> rentingOrderDetailList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            if (!orderDetail.isReturned()) {
                rentingOrderDetailList.add(orderDetail);
            }
        }
        return rentingOrderDetailList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderOwner=" + user +
                ", orderDetailList=" + orderDetailList +
                '}';
    }
}