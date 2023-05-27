package com.groupproject.entity.generic;

public class BankAccount {
    private String number;
    private String brand;
    private double balance;

    public BankAccount(String number, String brand) {
        this.number = number;
        this.brand = brand;
        this.balance = 300000;
    }

    public BankAccount(String number, String brand, double balance) {
        this.number = number;
        this.brand = brand;
        this.balance = balance;
    }

    public boolean transfer(AccountCustomer user, double amount) {
        if (this.balance > amount && amount > 0) {
            user.updateBalance1(amount);
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public String getNumber() {
        return number;
    }
    public String getBrand() {
        return brand;
    }
    public double getBalance() {
        return balance;
    }

    // public String getBankAccInfo(){
    //     return number + "|" + brand + "|" + balance;
    // }

    public boolean correctInfo(String number, String brand){
        return this.number.equals(number) && this.brand.equals(brand);
    }

    @Override
    public String toString() {
        return number + "|" + brand + "|" + balance;
    }
}

