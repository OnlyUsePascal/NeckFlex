package com.groupproject.controller.page;

import com.groupproject.entity.generic.AccountCustomer;
import com.groupproject.entity.generic.BankAccount;
import com.groupproject.entity.EntityHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDepositController implements Initializable {
    // @FXML
    // private Label transferMoney;
    @FXML
    private TextField numberBox;
    @FXML
    private TextField brandBox;
    @FXML
    private TextField amountBox;
    @FXML
    private Label messBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListener(numberBox);
        setListener(brandBox);
        setListener(amountBox);
    }

    public void setListener(Node node) {
        node.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().toString().equals("ENTER")) {
                transfer();
            }
        });
    }

    public void transfer() {
        String number = numberBox.getText();
        String brand = brandBox.getText();
        try {
            double amount = Double.parseDouble(this.amountBox.getText());

            BankAccount bankAcc = EntityHandler.findBankAccount(number, brand);
            if (bankAcc == null) {
                messBox.setText("Bank Account not found!.");
            } else {
                if (bankAcc.transfer((AccountCustomer) EntityHandler.getCurrentUser(), amount)) {
                    messBox.setText("Successfully transferred $" + amount);
                    messBox.setTextFill(Color.GREEN);

                    numberBox.clear();
                    brandBox.clear();
                    amountBox.clear();
                } else {
                    messBox.setText("Not enough money. Current balance = " + bankAcc.getBalance() + "USD");
                }
            }
        } catch (NumberFormatException exception) {
            messBox.setText("Please only use numbers for Amount field.");
        }


    }
}
