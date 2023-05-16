package com.groupproject.entity.generic;

import com.groupproject.entity.runtime.EntityHandler;
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
    private double balance;
    private int rewardPoint;
    private ConstantAccount.AccountStatus status;

    private Cart cart;
    private ArrayList<Order> orderList;
    // private int type;


    public Account(String id, String username,
                   String password, String firstName,
                   String lastName, String address,
                   String phoneNumber, int status,
                   double balance, int rewardPoint) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.rewardPoint = rewardPoint;

        this.cart = new Cart(this);
        this.orderList = new ArrayList<>();
        this.status = ConstantAccount.getStatus(status);
    }

    static public Account getNewAccount(String username, String password,
                                        String firstName, String lastName,
                                        String address, String phone) {
        // get id
        int accId = EntityHandler.accountListLength() + 1;
        Account newAaccount = new Account("C" + String.format("%03d", accId),
                username, password,
                firstName, lastName,
                address, phone,
                ConstantAccount.AccountStatus.GUEST.ordinal(),
                30, 0);
        return newAaccount;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return this.balance;
    }

    public int getRewardPoint() {
        return this.rewardPoint;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public Cart getCart() {
        return cart;
    }

    public String getId() {return id;}

    public String getStatusString() {
        return ConstantAccount.getStatusString(status.ordinal());
    }

    public ConstantAccount.AccountStatus getStatus() {return status;}

    public ArrayList<Order> getOrderList() {
        return orderList;
    }


    public boolean isAdmin() {
        return status == ConstantAccount.AccountStatus.ADMIN;
    }

    public boolean isGuest(){
        //if not guest -> regular, vip
        return status == ConstantAccount.AccountStatus.GUEST;
    }

    public boolean isVIP(){
        return status == ConstantAccount.AccountStatus.VIP;
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

    public void setBalance(double amount) {
        this.balance += amount;
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public void updateCredit(){
        rewardPoint += 10;

        //status
        if(status == ConstantAccount.AccountStatus.REGULAR ||
            rewardPoint >= 30){
            status = ConstantAccount.AccountStatus.REGULAR;
        }

        if (status == ConstantAccount.AccountStatus.VIP ||
            rewardPoint >= 80){
            status = ConstantAccount.AccountStatus.VIP;
        }

        System.out.println(this);
    }

    public void deductBalance(double amount) {
        balance -= amount;
    }

    public void deductRewardPoint() {
        rewardPoint -= 100;
    }

    public void makeAdmin() {
        status = ConstantAccount.AccountStatus.ADMIN;
    }

    @Override
    public String toString() {
        return this.id + "|" + this.username + "|" +
                this.password + "|" + this.firstName + "|" +
                this.lastName + "|" + this.address +
                "|" + this.phoneNumber + "|" + this.status.ordinal() +
                "|" + this.balance + "|" + this.rewardPoint;
    }
}
