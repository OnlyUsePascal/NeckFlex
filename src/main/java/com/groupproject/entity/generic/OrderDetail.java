package com.groupproject.entity.generic;

import com.groupproject.entity.EntityHandler;
import com.groupproject.controller.ViewHandler;

import java.time.Duration;
import java.time.LocalDateTime;

public class OrderDetail {
    private Item item;
    private int quantity;
    private double price;
    private boolean isReturned;
    private Order order;

    public OrderDetail(CartDetail cartDetail) { // new
        this.item = EntityHandler.getCopyItem(cartDetail.getItem());
        this.quantity = cartDetail.getQuantity();
        this.price = ViewHandler.getDoubleRound(cartDetail.getTotalPrice());
        this.isReturned = false;
        // this.order = order;
    }

    public OrderDetail(Item item, int quantity, boolean isReturned) { // restore
        this.item = item;
        this.quantity = quantity;
        this.price = ViewHandler.getDoubleRound(item.getPrice() * quantity);
        this.isReturned = isReturned;
    }

    // --- SET ---
    public void setRootOrder(Order order) {
        this.order = order;
    }

    public void setReturned() {
        isReturned = true;

        // check credit
        updateUserCredit();
        updateItemStock();
    }

    public void updateItemStock() {
        Item item = getItemFromDb();
        System.out.println(item);
        if (item != null) {
            item.updateStock(getQuantity());
        }
    }

    public void updateUserCredit() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime orderTime = order.getOrderTime();

        int offset = Duration.between(orderTime, now).compareTo(Duration.ofDays(order.getDuration()));
        if (offset <= 0) { // on time
            if (order.getOwner().updateCredit1()) {
                ViewHandler.getNoti("Returned Successfully!\nYour account has been promoted!", null);
            } else {
                ViewHandler.getNoti("Returned Successfully!\nYour credit point has been updated!", null);
            }
        } else {
            ViewHandler.getNoti("Returned Successfully!", null);
        }
    }

    // --- GET ---
    public Item getItem() {
        return item;
    }

    public Item getItemFromDb() {
        return EntityHandler.findItem(item.getId());
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return ViewHandler.getDoubleRound(price);
    }

    public boolean isReturned() {
        return isReturned;
    }

    @Override
    public String toString() {
        String orderDetailInfo = "";

        orderDetailInfo += quantity + "/";
        orderDetailInfo += isReturned + "/";
        orderDetailInfo += item.toString();

        return orderDetailInfo;
    }
}
