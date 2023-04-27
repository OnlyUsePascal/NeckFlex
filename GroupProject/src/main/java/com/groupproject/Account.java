package com.groupproject;

public class Account {
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    static public int customerID = 1;

    public Account() {
    }

    public Account(String id, String username, String password, String firstName, String lastName, String address, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    static public Account registerCustomer(String username, String password, String firstName, String lastName, String address, String phone){
        Account newCustomer = new Account("C" + String.format("%03d", customerID), username, password, firstName, lastName, address, phone);
        customerID++;
        return newCustomer;
    }

    public String Account2Str(){
        return this.id + "|" + this.username + "|" + this.password + "|" + this.firstName + "|" + this.lastName + "|" + this.address + "|" + this.phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
