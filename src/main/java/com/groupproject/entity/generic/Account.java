package com.groupproject.entity.generic;

import com.groupproject.entity.EntityHandler;
import com.groupproject.entity.Constant.ConstantAccount;
import com.groupproject.controller.ViewHandler;

import java.util.ArrayList;

public abstract class Account {
    protected String id;
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String phoneNumber;
    protected ConstantAccount.AccountStatus status;


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
}
