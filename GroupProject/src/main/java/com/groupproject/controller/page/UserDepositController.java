package com.groupproject.controller.page;

import com.groupproject.entity.generic.BankAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserDepositController implements Initializable {
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

    private ArrayList<BankAccount> bankAccountsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bankAccountsList = new ArrayList<>();
        bankAccountsList.add(new BankAccount("a123", "TCB"));
        bankAccountsList.add(new BankAccount("a124", "VCB"));
        bankAccountsList.add(new BankAccount("a121", "ACB"));

        setListener(accountNumber);
        setListener(bankBrand);
        setListener(amount);
    }

    public void setListener(Node node){
        node.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().toString().equals("ENTER")){
                transfer(null);
            }
        });
    }

    public void transfer(ActionEvent e) {
        String sAccountNumber = accountNumber.getText();
        String sBankBrand = bankBrand.getText();


        try {
            Double dAmount = Double.parseDouble(amount.getText());

            BankAccount bankAccount = findBankAccount(sAccountNumber, sBankBrand);

            if (bankAccount == null) {
                message.setText("There's no such account.");
            } else {
                if (bankAccount.transfer(bankAccount, dAmount)) {
                    message.setText("Successfully transferred " + dAmount + "USD");
                } else {
                    message.setText("Not enough money. Current balance = " + bankAccount.getBalance() + "USD");
                }
            }
        } catch (NumberFormatException exception) {
            message.setText("Please only use numbers for Amount field.");
        }
    }

    public BankAccount findBankAccount(String number, String brand){
        for (BankAccount b: bankAccountsList) {
            if (b.getNumber().equals(number) && b.getBrand().equals(brand)) {
                return b;
            }
        }

        return null;
    }
}
