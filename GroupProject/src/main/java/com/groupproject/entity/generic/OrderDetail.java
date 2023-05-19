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
        this.item = EntityHandler.getCopyItem(cartDetail.getItem());
        this.quantity = cartDetail.getQuantity();
        this.price = ViewHandler.getDoubleRound(cartDetail.getTotalPrice());
        this.isReturned = false;
        this.order = order;
    }

    public OrderDetail(Item item, int quantity, boolean isReturned, Order order) {
        this.item = item;
        this.quantity = quantity;
        this.price = ViewHandler.getDoubleRound(item.getPrice() * quantity);
        this.isReturned = isReturned;
        this.order = order;
    }

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

    public void setReturned(){
        isReturned = true;
        EntityHandler.getCurrentUser().updateCredit();
    }

    public String getOrderDetailInfo(){
        String orderDetailInfo = "";

        orderDetailInfo += quantity + "/";
        orderDetailInfo += isReturned + "/";
        orderDetailInfo += item.toString();

        return orderDetailInfo;
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
