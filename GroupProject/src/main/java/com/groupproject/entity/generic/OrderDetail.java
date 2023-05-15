package com.groupproject.entity.generic;

import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.entity.runtime.ViewHandler;

public class OrderDetail {
    private Item item;
    private int quantity;
    private double price;
    private boolean isReturned;
    private Order order;

    public OrderDetail(CartDetail cartDetail, Order order) {
        this.item = EntityHandler.getCategorizedItem(cartDetail.getItem());
        this.quantity = cartDetail.getQuantity();
        this.price = ViewHandler.getDoubleRound(cartDetail.getTotalPrice());
        this.isReturned = false;
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(){
        isReturned = true;
        EntityHandler.getCurrentUser().updateCredit();
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "item=" + item.getTitle() +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
