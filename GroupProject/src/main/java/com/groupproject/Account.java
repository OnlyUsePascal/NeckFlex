package com.groupproject;

public class Account {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    static int customerID = 1;


    public Account(String id, String username, String password, String firstName, String lastName, String address, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    static public void registerCustomer(String username, String password, String firstName, String lastName, String address, String phone){
        Account newCustomer = new Account("C" + String.format("%03d", customerID), username, password, firstName, lastName, address, phone);
        customerID++;
    }

    public String Account2Str(){
        return this.id + "|" + this.username + "|" + this.password + "|" + this.firstName + "|" + this.lastName + "|" + this.address + "|" + this.phone;
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
