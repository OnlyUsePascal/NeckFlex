package com.groupproject.entity.generic;

import com.groupproject.entity.runtime.ShopSystem;
import com.groupproject.entity.Constant.ConstantAccount;

import java.util.ArrayList;

public class Account {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private ConstantAccount accountType;
    private double balance;
    private double rewardPoint;

    private Cart cart;
    private ArrayList<Order> orderList;


    public Account(String id, String username, String password, String firstName, String lastName, String address, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = 100;
        this.rewardPoint = 100;

        this.cart = new Cart(this);
        this.orderList = new ArrayList<>();
    }

    static public Account getNewAccount(String username, String password, String firstName, String lastName, String phone, String address){
        //get id
        int accId = ShopSystem.getAccountListLength() + 1;
        Account newAaccount = new Account("C" + String.format("%03d", accId), username, password, firstName, lastName, address, phone);
        return newAaccount;
    }

    public ConstantAccount getAccountType() {
        return accountType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance(){
        return this.balance;
    }

    public double getRewardPoint(){
        return this.rewardPoint;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public String getAddress(){
        return this.address;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBalance(double amount){
        this.balance += amount;
    }


    public Cart getCart(){
        return cart;
    }

    public void addOrder(Order order){
        orderList.add(order);
    }

    public ArrayList<Order> getOrderList(){
        return orderList;
    }

    @Override
    public String toString(){
        return this.id + "|" + this.username + "|" + this.password + "|" + this.firstName + "|" + this.lastName + "|" + this.address + "|" + this.phoneNumber;
    }
}
