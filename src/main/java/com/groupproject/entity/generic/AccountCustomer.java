package com.groupproject.entity.generic;

import com.groupproject.controller.ViewHandler;
import com.groupproject.entity.Constant.ConstantAccount;

import java.util.ArrayList;

public class AccountCustomer extends Account {
    private double balance;
    private int rewardPoint;
    private Cart cart;
    private ArrayList<Order> orderList;


    public AccountCustomer() {}

    public AccountCustomer(String id, String username,
                           String password, String firstName,
                           String lastName, String address,
                           String phoneNumber, int status,
                           double balance, int rewardPoint) {
        super(id, username, password, firstName, lastName, address, phoneNumber, status);

        this.balance = balance;
        this.rewardPoint = rewardPoint;
        this.cart = new Cart(this);
        this.orderList = new ArrayList<>();
    }

    public AccountCustomer(ArrayList<String> infoList) {
        this(infoList.get(ConstantAccount.AccountInfo.ID.ordinal()),
                infoList.get(ConstantAccount.AccountInfo.USERNAME.ordinal()),
                infoList.get(ConstantAccount.AccountInfo.PASSWORD.ordinal()),
                infoList.get(ConstantAccount.AccountInfo.FIRSTNAME.ordinal()),
                infoList.get(ConstantAccount.AccountInfo.LASTNAME.ordinal()),
                infoList.get(ConstantAccount.AccountInfo.ADDRESS.ordinal()),
                infoList.get(ConstantAccount.AccountInfo.PHONE.ordinal()),
                Integer.parseInt(infoList.get(ConstantAccount.AccountInfo.STATUS.ordinal())),
                Double.parseDouble(infoList.get(ConstantAccount.AccountInfo.BALANCE.ordinal())),
                Integer.parseInt(infoList.get(ConstantAccount.AccountInfo.REWARDPOINT.ordinal())));
    }

    // --- GET ---
    public double getBalance1() {
        return ViewHandler.getDoubleRound(balance);
    }

    public int getRewardPoint1() {
        return this.rewardPoint;
    }

    public Cart getCart1() {
        return cart;
    }

    public ArrayList<Order> getOrderList1() {
        return orderList;
    }

    public String getOrderListInfo1() {
        String orderListInfo = username + "@";
        for (Order order : orderList) {
            orderListInfo += order.toString() + "@";
        }

        return orderListInfo;
    }

    // --- SET ---
    public void updateBalance1(double amount) {
        this.balance += amount;
    }

    public void addOrder1(Order order) {
        orderList.add(order);
    }

    public boolean updateCredit1() {
        rewardPoint += 10;

        // upgrade
        if (status == ConstantAccount.AccountStatus.GUEST &&
                rewardPoint >= 30) {
            status = ConstantAccount.AccountStatus.REGULAR;
            return true;
        } else if (status == ConstantAccount.AccountStatus.REGULAR &&
                rewardPoint >= 80) {
            status = ConstantAccount.AccountStatus.VIP;
            return false;
        }
        return false;
    }

    public boolean deductBalance1(double amount) {
        if (balance >= amount) {
            balance -= amount;
            balance = ViewHandler.getDoubleRound(balance);
            return true;
        }
        return false;
    }

    public boolean deductRewardPoint1(int amount) {
        if (rewardPoint >= amount) {
            rewardPoint -= amount;
            return true;
        }
        return false;
    }

    public int getRentingItemNum1() {
        int rentingItemNum = 0;
        for (Order order : orderList) {
            rentingItemNum += order.getRentingItemNum();
        }
        return rentingItemNum;
    }

    @Override
    public String toString() {
        return this.id + "|" + this.username + "|" +
                this.password + "|" + this.firstName + "|" +
                this.lastName + "|" + this.address +
                "|" + this.phoneNumber + "|" + this.status.ordinal() +
                "|" + ViewHandler.getDoubleRound(this.balance) + "|" + this.rewardPoint;
    }

}

























