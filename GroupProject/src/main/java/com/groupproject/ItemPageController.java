package com.groupproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemPageController {
    @FXML
    private Label title;

    @FXML
    private Label genre;

    @FXML
    private Label quantity;

    @FXML
    private Label price;

    @FXML
    private Label addToCartResponse;

    @FXML
    private TextField cartAmount;
    private ArrayList<CartItem> cart;

    public void addToCartHandler(ActionEvent event) {
        Integer requestedAmount = Integer.parseInt(cartAmount.getText());
        if (requestedAmount > Integer.parseInt(quantity.getText()) || requestedAmount < 1) {
            addToCartResponse.setText("Choose the amount from 1 to " + Integer.parseInt(quantity.getText()));
        } else {
            CartItem existingItem = findByName(title.getText());
            if (existingItem != null) {
                if (existingItem.getAmount() + requestedAmount <= Integer.parseInt(quantity.getText())) {
                    existingItem.addAmount(requestedAmount);

                    System.out.println(
                            "updated in cart: " +
                                    title.getText() + " " +
                                    requestedAmount + " " +
                                    genre.getText() + " " +
                                    Double.parseDouble(price.getText())
                    );
                    System.out.println("now have " + cart.size() + " elements in cart");
                    addToCartResponse.setText("Now have " + existingItem.getAmount() + " '" + title.getText() + " 'in cart");
                } else {
                    addToCartResponse.setText("Combined amount is bigger than " + Integer.parseInt(quantity.getText()));
                }
            } else {
                cart.add(new CartItem(title.getText(), requestedAmount, genre.getText(), Double.parseDouble(price.getText())));

                addToCartResponse.setText("Added " + requestedAmount + " '" + title.getText() + " 'to cart");

                System.out.println(
                        "added to cart: " +
                                title.getText() + " " +
                                requestedAmount + " " +
                                genre.getText() + " " +
                                Double.parseDouble(price.getText())
                );
                System.out.println("now have " + cart.size() + " elements in cart");
            }
        }
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setGenre(String genre) {
        this.genre.setText(genre);
    }

    public void setPrice(Double price) {
        this.price.setText(price.toString());
    }

    public void setQuantity(Integer quantity) {
        this.quantity.setText(quantity.toString());
    }

    public void setCart(ArrayList<CartItem> cart) {
        this.cart = cart;
    }

    public void incAmount(ActionEvent e) {
        Integer incremented = Integer.parseInt(this.cartAmount.getText()) + 1;

        this.cartAmount.setText(incremented.toString());
    }

    public void decAmount(ActionEvent e) {
        Integer decremented = Integer.parseInt(this.cartAmount.getText()) - 1;

        if (decremented > 0) {
            this.cartAmount.setText(decremented.toString());
        }
    }

    private CartItem findByName(String name) {
        for (CartItem i: cart) {
            if (i.getName().equals(name)) {
                return i;
            }
        }

        return null;
    }
}
