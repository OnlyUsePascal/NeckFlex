package com.groupproject.entity.generic;

import java.util.ArrayList;

public class Cart {
    private Account cartOwner;
    private ArrayList<CartDetail> cartDetailList;

    public Cart(){
        cartOwner = null;
        cartDetailList = new ArrayList<>();
    }

    public void addCartDetail(Item item, int quantity) {
        cartDetailList.add(new CartDetail(item, quantity));
    }

    public void removeCartItem(CartDetail cartDetail) {
        cartDetailList.remove(cartDetail);
    }

    public ArrayList<CartDetail> getcartDetailList() {
        return cartDetailList;
    }
}
