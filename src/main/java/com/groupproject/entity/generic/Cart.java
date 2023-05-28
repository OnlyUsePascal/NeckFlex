package com.groupproject.entity.generic;

import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.Constant.ConstantOrder;
import com.groupproject.entity.EntityHandler;
import com.groupproject.controller.ViewHandler;

import java.util.ArrayList;

public class Cart {
    private AccountCustomer owner;
    private ArrayList<CartDetail> cartDetailList;
    private double totalPrice;

    public Cart(AccountCustomer owner) {
        this.owner = owner;
        cartDetailList = new ArrayList<>();
        totalPrice = 0;
    }

    // --- GET ---
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

    public int getCartItemNum(){
        int quantity = 0;
        for (CartDetail cartDetail : cartDetailList){
            quantity += cartDetail.getQuantity();
        }
        return quantity;
    }

    public AccountCustomer getOwner() {
        return owner;
    }

    public int getCartWeight(){
        return cartDetailList.size();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // public String getCartInfo(){
    //     String cartInfo = "";
    //     cartInfo += (owner.getUsername() + "|");
    //     for (CartDetail cartDetail : cartDetailList){
    //         cartInfo += cartDetail.getCartDetailInfo() + "|";
    //     }
    //
    //     return cartInfo;
    // }

    // --- SET ---
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

    public void updateTotalPrice(double price) {
        totalPrice = ViewHandler.getDoubleRound(totalPrice + price);
    }

    // --- checkout ---
    public ConstantOrder.OrderStatus checkout(boolean payWithBalance, ConstantOrder.OrderDuration duration) {
        //validate
        ConstantOrder.OrderStatus status = checkValid(payWithBalance);
        if (status != ConstantOrder.OrderStatus.ACCEPTED) return status;

        //make order
        Order newOrder = new Order(owner, cartDetailList, duration);
        owner.addOrder1(newOrder);
        finishCheckout();

        return status;
    }

    public void finishCheckout() {
        updateTotalPrice(-totalPrice);

        //update stock
        for (CartDetail cartDetail : cartDetailList) {
            cartDetail.updateStock();
        }

        cartDetailList.clear();
    }

    public boolean checkLimit() {
        if (!owner.isGuest()) return true;

        int curRenting = owner.getRentingItemNum1();
        int curCart = getCartItemNum();

        return curRenting + curCart <= ConstantOrder.rentingLimit;
    }

    ConstantOrder.OrderStatus checkValid(boolean payWithBalance){
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
        return owner.deductBalance1(totalPrice);
    }

    public boolean payWithPoint(){
        int curCartWeight = getCartWeight();
        int price = (int) (curCartWeight * 10);

        return owner.deductRewardPoint1(price);
    }

    @Override
    public String toString() {
        String cartInfo = "";
        cartInfo += (owner.getUsername() + "|");
        for (CartDetail cartDetail : cartDetailList){
            cartInfo += cartDetail.toString() + "|";
        }

        return cartInfo;
    }
}
