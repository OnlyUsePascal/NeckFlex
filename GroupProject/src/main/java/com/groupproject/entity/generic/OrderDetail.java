package com.groupproject.entity.generic;

import com.groupproject.entity.runtime.ShopSystem;
import com.groupproject.toolkit.ObjectHandler;

public class OrderDetail {
    private Item item;
    private int quantity;
    private double price;
    private boolean isReturned;

    public OrderDetail(CartDetail cartDetail) {
        this.item = ShopSystem.getItemFromInfo(cartDetail.getItem());
        this.quantity = cartDetail.getQuantity();
        this.price = ObjectHandler.getDoubleRound(cartDetail.getTotalPrice());
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
