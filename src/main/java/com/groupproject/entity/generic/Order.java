package com.groupproject.entity.generic;

import com.groupproject.entity.Constant.ConstantOrder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {
    private AccountCustomer owner;
    private ArrayList<OrderDetail> orderDetailList;
    private LocalDateTime date;
    private double totalPrice;
    private ConstantOrder.OrderDuration duration;

    public Order() {
        orderDetailList = new ArrayList<>();
    }

    public Order(AccountCustomer owner, ArrayList<CartDetail> cartDetailList,
                 ConstantOrder.OrderDuration duration) {
        this.owner = owner;
        this.duration = duration;
        orderDetailList = new ArrayList<>();
        date = LocalDateTime.now();

        for (CartDetail cartDetail : cartDetailList) {
            OrderDetail orderDetail = new OrderDetail(cartDetail);
            addOrderDetail(orderDetail);
        }
    }

    // --- GET ---
    public void updateTotalPrice(double price) {
        totalPrice += price;
    }

    public int getRentingItemNum() {
        int rentingQuantity = 0;
        for (OrderDetail orderDetail : orderDetailList) {
            if (!orderDetail.isReturned()) {
                rentingQuantity += orderDetail.getQuantity();
            }
        }
        return rentingQuantity;
    }

    public int getDuration(){
        return duration.getDurationValue();
    }

    public LocalDateTime getOrderTime() {
        return date;
    }

    public String getDateString() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MMMM-dd"));
    }

    public String getDateEndString(){
        return date.plusDays(duration.getDurationValue()).format(DateTimeFormatter.ofPattern("yyyy-MMMM-dd"));
    }

    public ArrayList<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    // public String getOrderInfo() {
    //     String orderInfo = "";
    //
    //     orderInfo += date + "~";
    //     orderInfo += duration.ordinal() + "~";
    //     for (OrderDetail orderDetail : orderDetailList) {
    //         orderInfo += orderDetail.getOrderDetailInfo() + "~";
    //     }
    //
    //     return orderInfo;
    // }

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

    public boolean isLate(){
        return LocalDateTime.now().isAfter(date.plusDays(duration.getDurationValue()));
    }

    public AccountCustomer getOwner() {
        return owner;
    }

    // --- SET ---
    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetailList.add(orderDetail);
        orderDetail.setRootOrder(this);
        updateTotalPrice(orderDetail.getPrice());
    }

    public void setDate(String date) {
        this.date = LocalDateTime.parse(date);
    }

    public void setDuration(int durationIndex) {
        // this.duration = orderDuration;
        duration = ConstantOrder.OrderDuration.values()[durationIndex];
    }

    public void setOwner(AccountCustomer owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        String orderInfo = "";

        orderInfo += date + "~";
        orderInfo += duration.ordinal() + "~";
        for (OrderDetail orderDetail : orderDetailList) {
            orderInfo += orderDetail.toString() + "~";
        }

        return orderInfo;
        // return "Order{" +
        //         "orderOwner=" + owner +
        //         ", orderDetailList=" + orderDetailList +
        //         '}';
    }
}
