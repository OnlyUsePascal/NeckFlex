package com.groupproject.entity.generic;

import com.groupproject.entity.Constant.ConstantOrder;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.entity.runtime.ViewHandler;

import java.util.ArrayList;

public class Cart {
    private Account owner;
    private ArrayList<CartDetail> cartDetailList;
    private double totalPrice;

    public Cart(Account owner) {
        this.owner = owner;
        cartDetailList = new ArrayList<>();
        totalPrice = 0;
    }

    public Account getOwner() {
        return owner;
    }

    public int getCartWeight(){
        return cartDetailList.size();
    }

    public void addCartDetail(Item item, int quantity) {
        CartDetail cartDetail = new CartDetail(item, quantity);
        cartDetailList.add(cartDetail);
        cartDetail.setCart(this);
        updateTotalPrice(cartDetail.getTotalPrice());
    }

    public void refreshCart(){
        //wipe deleted item
        for (int i = cartDetailList.size() - 1; i >= 0; i--){
            CartDetail cartDetail = cartDetailList.get(i);

            if (cartDetail.getItem().isDeleted()){
                removeCartDetail(cartDetail);
            }

            if (!cartDetail.refreshQuantity()){
                removeCartDetail(cartDetail);
            }
        }
    }

    public void removeCartDetail(CartDetail cartDetail) {
        updateTotalPrice(-cartDetail.getTotalPrice());
        cartDetailList.remove(cartDetail);
    }

    public void removeCartDetail(Item item){
        for (CartDetail cartDetail : cartDetailList){
            if (cartDetail.getItem().equals(item)){
                removeCartDetail(cartDetail);
                return;
            }
        }
    }

    public void finishCheckout() {
        updateTotalPrice(-totalPrice);

        for (CartDetail cartDetail : cartDetailList) {
            cartDetail.updateStock();
        }

        cartDetailList.clear();
    }

    public CartDetail findCartDetail(Item item) {
        for (CartDetail cartDetail : cartDetailList) {
            if (cartDetail.getItem().equals(item)) {
                return cartDetail;
            }
        }
        return null;
    }

    public ArrayList<CartDetail> getCartDetailList() {
        return cartDetailList;
    }

    public void updateTotalPrice(double price) {
        totalPrice = ViewHandler.getDoubleRound(totalPrice + price);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getCartInfo(){
        String cartInfo = "";
        cartInfo += (owner.getUsername() + "|");
        for (CartDetail cartDetail : cartDetailList){
            int itemIndex = EntityHandler.getItemIndex(cartDetail.getItem());
            cartInfo += (itemIndex + "/" + cartDetail.getQuantity() + "|");
        }

        return cartInfo;
    }

    public ConstantOrder.OrderStatus checkout(boolean payWithBalance) {
        // check limit
        if (!checkLimit()) {
            return ConstantOrder.OrderStatus.LIMITED_AMOUNT;
        }

        // check balance
        if (payWithBalance) {
            if (!payWithBalance()){
                return ConstantOrder.OrderStatus.INSUFFICIENT_BALANCE;
            }
        } else {
            if (!payWithPoint()){
                return ConstantOrder.OrderStatus.INSUFFICIENT_POINT;
            }
        }

        return ConstantOrder.OrderStatus.ACCEPTED;
    }

    public boolean payWithBalance(){
        if (owner.getBalance() < totalPrice) {
            return false;
        }

        owner.deductBalance(totalPrice);
        return true;
    }

    public boolean payWithPoint(){
        if (owner.getRewardPoint() < totalPrice) {
            return false;
        }

        owner.deductRewardPoint();
        return true;
    }

    public boolean checkLimit() {
        if (!owner.isGuest()) return true;

        // renting + number in cart <= 2
        int curQuantity = 0;
        for (Order order : EntityHandler.getCurrentUser().getOrderList()) {
            for (OrderDetail orderDetail : order.getOrderDetailList()) {
                if (!orderDetail.isReturned()) {
                    curQuantity += orderDetail.getQuantity();
                    if (curQuantity > ConstantOrder.rentingLimit) {
                        return false;
                    }
                }
            }
        }

        for (CartDetail cartDetail : cartDetailList) {
            curQuantity += cartDetail.getQuantity();
            if (curQuantity > ConstantOrder.rentingLimit) {
                return false;
            }
        }

        return true;
    }



    @Override
    public String toString() {
        return "Cart{" +
                "cartOwner=" + owner.getUsername() +
                ", cartDetailList=" + cartDetailList +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
