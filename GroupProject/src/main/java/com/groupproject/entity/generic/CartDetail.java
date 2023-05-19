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

    public boolean refreshQuantity(){
        if (!item.isAvailable()) return false;
        quantity = Math.min(quantity, item.getStock());
        return true;
    }

    public void updateStock(){
        item.updateStock(-quantity);
    }

    public boolean setQuantity(int newQuantity) {
        if (newQuantity < 1 || newQuantity > item.getStock()) {
            return false;
        }

        cart.updateTotalPrice(item.getPrice() * (newQuantity - quantity));
        quantity = newQuantity;
        totalPrice = ViewHandler.getDoubleRound(item.getPrice() * newQuantity);
        return true;
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
