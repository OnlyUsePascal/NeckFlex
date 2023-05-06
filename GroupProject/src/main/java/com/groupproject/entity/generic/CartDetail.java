package com.groupproject.entity.generic;

import com.groupproject.toolkit.ObjectHandler;

public class CartDetail {
    Item item;
    int quantity;
    double totalPrice;

    public CartDetail(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.totalPrice = ObjectHandler.getDoubleRound(item.getPrice() * quantity);
    }

    public String getTitle() {
        return item.getTitle();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
            this.totalPrice = ObjectHandler.getDoubleRound(item.getPrice() * quantity);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "CartDetail{" +
                "item=" + item.getTitle() +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
