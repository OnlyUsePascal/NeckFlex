package com.groupproject.entity.runtime;

import com.groupproject.entity.generic.Cart;

public class CurrentCart {
    static public Cart currentCart;

    public static void setCurrentCart(Cart cart) {
        currentCart = cart;
    }

    public static Cart getCurrentCart() {
        return currentCart;
    }
}
