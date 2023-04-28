package com.groupproject;

public class Account {
    private String username;
    private double balance;
    private double rewardPoint;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    public Account(){
        this.username = "Default";
        this.balance = 0;
        this.rewardPoint = 0;
        this.firstName = "Default";
        this.lastName = "Default";
        this.phoneNumber = "Default";
        this.address = "Default";
    }
    public Account(String username, double balance, double rewardPoint, String firstName, String lastName, String phoneNumber, String address){
        this.username = username;
        this.balance = balance;
        this.rewardPoint = rewardPoint;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public String getUsername(){
        return this.username;
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
}
