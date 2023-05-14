package com.groupproject.entity.generic;

import com.groupproject.entity.runtime.ViewHandler;

public class CartDetail {
    Item item;
    int quantity;
    double totalPrice;
    private Cart cart;

    public CartDetail(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.totalPrice = ViewHandler.getDoubleRound(item.getPrice() * quantity);
    }

    public void setCart(Cart cart){
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
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

    public void setQuantity(int newQuantity) {
        if (newQuantity >= 0) {
            cart.totalPriceUpdate(item.getPrice() * (newQuantity - quantity));
            this.quantity = newQuantity;
            totalPrice = ViewHandler.getDoubleRound(item.getPrice() * newQuantity);
        }
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
