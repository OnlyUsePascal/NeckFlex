package com.groupproject.entity.generic;

import com.groupproject.entity.Constant.ConstantOrder;
import com.groupproject.entity.runtime.CurrentUser;
import com.groupproject.toolkit.ObjectHandler;

import java.util.ArrayList;

public class Cart {
    private Account cartOwner;
    private ArrayList<CartDetail> cartDetailList;
    private double totalPrice;

    public Cart(){
        cartOwner = CurrentUser.getCurrentUser();
        cartDetailList = new ArrayList<>();
        totalPrice = 0;
    }

    public void addCartDetail(Item item, int quantity) {
        CartDetail cartDetail = new CartDetail(item, quantity);
        cartDetailList.add(cartDetail);
        cartDetail.setCart(this);
        updateTotalPrice(cartDetail.getTotalPrice());
    }

    public void removeCartDetail(CartDetail cartDetail) {
        updateTotalPrice(-cartDetail.getTotalPrice());
        cartDetailList.remove(cartDetail);

        System.out.println(cartDetailList);
    }

    public ArrayList<CartDetail> getcartDetailList() {
        return cartDetailList;
    }

    public void updateTotalPrice(double price){
        totalPrice = ObjectHandler.getDoubleRound(totalPrice + price);
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public ConstantOrder.OrderStatus checkout(boolean payWithBalance){
        if (payWithBalance){
            if (cartOwner.getBalance() < totalPrice){
                return ConstantOrder.OrderStatus.INSUFFICIENT_BALANCE;
            }
        } else {
            if (cartOwner.getRewardPoint() < totalPrice){
                return ConstantOrder.OrderStatus.INSUFFICIENT_POINT;
            }
        }


        return ConstantOrder.OrderStatus.ACCEPTED;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartOwner=" + cartOwner.getUsername() +
                ", cartDetailList=" + cartDetailList +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
