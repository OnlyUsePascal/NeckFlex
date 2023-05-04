package com.groupproject.types;

public class Account {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private double balance;
    private double rewardPoint;

    static public int customerID = 1;

    public Account() {
    }

    public Account(String id, String username, String password, String firstName, String lastName, String address, String phone, double balance, double rewardPoint) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.balance = balance;
        this.rewardPoint = rewardPoint;
    }

    static public Account registerCustomer(String username, String password, String firstName, String lastName, String address, String phone){
        Account newCustomer = new Account("C" + String.format("%03d", customerID), username, password, firstName, lastName, address, phone, 0.0, 0.0);
        customerID++;
        return newCustomer;
    }

    public String Account2Str(){
        return this.id + "|" + this.username + "|" + this.password + "|" + this.firstName + "|" + this.lastName + "|"
                + this.address + "|" + this.phone + "|" + this.balance + "|" + this.rewardPoint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(double rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}' + '\n';
    }
}
