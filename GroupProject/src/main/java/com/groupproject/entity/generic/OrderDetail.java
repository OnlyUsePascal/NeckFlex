package com.groupproject.entity.generic;

import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.entity.runtime.ViewHandler;

public class OrderDetail {
    private Item item;
    private int quantity;
    private double price;
    private boolean isReturned;

    public OrderDetail(CartDetail cartDetail) {
        this.item = EntityHandler.itemFromInfo(cartDetail.getItem());
        this.quantity = cartDetail.getQuantity();
        this.price = ViewHandler.getDoubleRound(cartDetail.getTotalPrice());
        this.isReturned = false;
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
        EntityHandler.currentUserGet().updateCredit();
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
