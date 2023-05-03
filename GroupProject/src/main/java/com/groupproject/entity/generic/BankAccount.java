package com.groupproject.entity.generic;

import com.groupproject.controller.page.UserDepositController;
import com.groupproject.entity.runtime.CurrentUser;

public class BankAccount {
    private String number;
    private String brand;
    private double balance;

    public BankAccount(String number, String brand) {
        this.number = number;
        this.brand = brand;
        this.balance = 300000;
    }

    public boolean transfer(BankAccount account, double amount) {
        if (this.balance > amount && amount > 0) {
            CurrentUser.getCurrentUser().setBalanceReceive(amount);
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

    @Override
    public String toString() {
        return "BankAccount{" +
                "brand='" + brand + '\'' +
                ", balance=" + balance +
                '}';
    }
}

