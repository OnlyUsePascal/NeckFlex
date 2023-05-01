package com.groupproject.bank;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BankAccountController {

    @FXML
    private Label transferMoney;

    @FXML
    private TextField accountNumber;

    @FXML
    private TextField bankBrand;

    @FXML
    private TextField amount;

    @FXML
    private Label message;


    class Bank {
        public String brand;
        private double balance;

        public Bank(String brand) {
            this.brand = brand;
            this.balance = 300000;
        }

        public boolean transfer(Account account, double amount) {
            if (this.balance > amount && amount > 0) {
                account.receive(amount);
                this.balance -= amount;
                return true;
            }

            return false;
        }

        public double getBalance() {
            return balance;
        }
    }

    class Account {
        public String accountNumber;
        private Bank bank;
        private double balance;

        public Account(String accountNumber, Bank bank) {
            this.accountNumber = accountNumber;
            this.bank = bank;
            this.balance = 0;
        }

        public void receive(double amount) {
            this.balance += amount;
        }
    }

    private String sAccountNumber;
    private String sBankBrand;
    private double dAmount;
    private Bank[] banks = new Bank[] {
        new Bank("TCB"),
        new Bank("VCB"),
    };
    private Account[] accounts = new Account[] {
        new Account("a123", banks[0]),
        new Account("a124", banks[0]),
        new Account("a125", banks[1]),
    };

    public void transfer(ActionEvent e) {
        sAccountNumber = accountNumber.getText();
        sBankBrand = bankBrand.getText();


        try {
            dAmount = Double.parseDouble(amount.getText());

            Bank bank = findBankByBrand(sBankBrand);
            Account account = findAccountByNumber(sAccountNumber);

            if (bank == null) {
                message.setText("There's no such bank.");
            } else if (account == null) {
                message.setText("There's no such account.");
            } else {
                if (bank.transfer(account, dAmount)) {
                    message.setText("Successfully transferred " + dAmount + "USD to " + account.accountNumber);
                } else {
                    message.setText("Please only transfer positive amounts and less than " + bank.getBalance());
                }
            }
        } catch (NumberFormatException exception) {
            message.setText("Please only use numbers for Amount field.");
        }
    }

    public Bank findBankByBrand(String brand) {
        for (Bank b: banks) {
            if (b.brand.equals(brand)) {
                return b;
            }
        }

        return null;
    }

    public Account findAccountByNumber(String accountNumber) {
        for (Account a: accounts) {
            if (a.accountNumber.equals(accountNumber)) {
                return a;
            }
        }

        return null;
    }
}
