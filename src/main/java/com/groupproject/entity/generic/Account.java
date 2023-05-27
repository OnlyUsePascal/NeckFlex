package com.groupproject.entity.generic;

import com.groupproject.entity.EntityHandler;
import com.groupproject.entity.Constant.ConstantAccount;
import com.groupproject.controller.ViewHandler;

import java.util.ArrayList;

public class Account {
    protected String id;
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String phoneNumber;
    protected ConstantAccount.AccountStatus status;
    // private double balance;
    // private int rewardPoint;
    // private Cart cart;
    // private ArrayList<Order> orderList;


    public Account(){}

    public Account(String id, String username,
                   String password, String firstName,
                   String lastName, String address,
                   String phoneNumber, int status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = ConstantAccount.getStatus(status);
    }

    // restore
    // public Account(ArrayList<String> infoList){
    //     this(infoList.get(ConstantAccount.AccountInfo.ID.ordinal()),
    //         infoList.get(ConstantAccount.AccountInfo.USERNAME.ordinal()),
    //         infoList.get(ConstantAccount.AccountInfo.PASSWORD.ordinal()),
    //         infoList.get(ConstantAccount.AccountInfo.FIRSTNAME.ordinal()),
    //         infoList.get(ConstantAccount.AccountInfo.LASTNAME.ordinal()),
    //         infoList.get(ConstantAccount.AccountInfo.ADDRESS.ordinal()),
    //         infoList.get(ConstantAccount.AccountInfo.PHONE.ordinal()),
    //         Integer.parseInt(infoList.get(ConstantAccount.AccountInfo.STATUS.ordinal())),
    //         Double.parseDouble(infoList.get(ConstantAccount.AccountInfo.BALANCE.ordinal())),
    //         Integer.parseInt(infoList.get(ConstantAccount.AccountInfo.REWARDPOINT.ordinal())));
    // }

    static public Account getNewAccount(String username, String password,
                                        String firstName, String lastName,
                                        String address, String phone, boolean isAdmin) {
        int accId = EntityHandler.accountListLength() + 1;
        if (isAdmin) {
            return new AccountAdmin("C" + String.format("%03d", accId),
                    username, password,
                    firstName, lastName,
                    address, phone,
                    ConstantAccount.AccountStatus.ADMIN.ordinal());
        }

        return new AccountCustomer("C" + String.format("%03d", accId),
                username, password,
                firstName, lastName,
                address, phone,
                ConstantAccount.AccountStatus.GUEST.ordinal(),
                30, 0);
    }

    static public Account getRestoreAccount(ArrayList<String> infoList) {
        int status = Integer.parseInt(infoList.get(ConstantAccount.AccountInfo.STATUS.ordinal()));
        if (status == ConstantAccount.AccountStatus.ADMIN.ordinal()) {
            return new AccountAdmin(infoList);
        }
        return new AccountCustomer(infoList);
    }

    // --- GET ---
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // public double getBalancee() {
    //     return ViewHandler.getDoubleRound(balance);
    // }
    //
    // public int getRewardPoint() {
    //     return this.rewardPoint;
    // }

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
    public String getId() {return id;}

    public String getStatusString() {
        return ConstantAccount.getStatusString(status.ordinal());
    }

    public ConstantAccount.AccountStatus getStatus() {return status;}

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


    // --- SET ---
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

    public void setPwd(String pwd){
        this.password = pwd;
    }

    // --- OTHER ---
    // public Cart getCartt() {
    //     return cart;
    // }

    // public ArrayList<Order> getOrderList() {
    //     return orderList;
    // }

    // public String getOrderListInfo(){
    //     String orderListInfo = username + "@";
    //     for (Order order : orderList){
    //         orderListInfo += order.getOrderInfo() + "@";
    //     }
    //
    //     return orderListInfo;
    // }

    // public int getRentingItemNum(){
    //     int rentingItemNum = 0;
    //     for (Order order : orderList){
    //         rentingItemNum += order.getRentingItemNum();
    //     }
    //     return rentingItemNum;
    // }


    // public void updateBalance(double amount) {
    //     this.balance += amount;
    // }

    // public void addOrder(Order order) {
    //     orderList.add(order);
    // }

    // public boolean updateCredit(){
    //     rewardPoint += 10;
    //
    //     //upgrade
    //     if(status == ConstantAccount.AccountStatus.GUEST &&
    //         rewardPoint >= 30){
    //         status = ConstantAccount.AccountStatus.REGULAR;
    //         return true;
    //     } else if (status == ConstantAccount.AccountStatus.REGULAR &&
    //         rewardPoint >= 60){
    //         status = ConstantAccount.AccountStatus.VIP;
    //         return false;
    //     }
    //     return false;
    // }

    // public boolean deductBalance(double amount) {
    //     if (balance >= amount) {
    //         balance -= amount;
    //         balance = ViewHandler.getDoubleRound(balance);
    //         return true;
    //     }
    //     return false;
    // }
    //
    // public boolean deductRewardPoint(int amount) {
    //     if (rewardPoint >= amount) {
    //         rewardPoint -= amount;
    //         return true;
    //     }
    //     return false;
    // }

    // public void makeAdmin() {
    //     status = ConstantAccount.AccountStatus.ADMIN;
    // }

    //
    // @Override
    // public String toString() {
    //     return this.id + "|" + this.username + "|" +
    //             this.password + "|" + this.firstName + "|" +
    //             this.lastName + "|" + this.address +
    //             "|" + this.phoneNumber + "|" + this.status.ordinal();
    //             // "|" + ViewHandler.getDoubleRound(this.balance) + "|" + this.rewardPoint;
    // }
}
