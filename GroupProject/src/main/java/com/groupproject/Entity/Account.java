package com.groupproject.Page;

public class Account {
    private String id;
    private String username;
    private double balance;
    private double rewardPoint;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String accountType;

    public Account(){
        this.id = "";
        this.username = "Default";
        this.balance = 0;
        this.rewardPoint = 0;
        this.firstName = "Default";
        this.lastName = "Default";
        this.phoneNumber = "Default";
        this.accountType = "Default";
        this.address = "Default";
    }
    public Account(String id, String username, double rewardPoint, String firstName, String lastName, double balance, String phoneNumber, String address, String accountType){
        this.id = id;
        this.username = username;
        this.balance = balance;
        this.rewardPoint = rewardPoint;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.accountType = accountType;
    }
    public String getUsername(){
        return this.username;
    }
    public double getBalance(){
        return this.balance;
    }

    public double getRewardPoint() {
        return rewardPoint;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRewardPoint(double rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

}
