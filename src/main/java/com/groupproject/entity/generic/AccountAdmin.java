package com.groupproject.entity.generic;

import com.groupproject.controller.ViewHandler;
import com.groupproject.entity.Constant.ConstantAccount;

import java.util.ArrayList;

public class AccountAdmin extends Account{
    public AccountAdmin(){}

    public AccountAdmin(String id, String username,
                   String password, String firstName,
                   String lastName, String address,
                   String phoneNumber, int status){
        super(id, username, password, firstName, lastName, address, phoneNumber, status);
    }

    public AccountAdmin(ArrayList<String> infoList){
        this(infoList.get(ConstantAccount.AccountInfo.ID.ordinal()),
                infoList.get(ConstantAccount.AccountInfo.USERNAME.ordinal()),
                infoList.get(ConstantAccount.AccountInfo.PASSWORD.ordinal()),
                infoList.get(ConstantAccount.AccountInfo.FIRSTNAME.ordinal()),
                infoList.get(ConstantAccount.AccountInfo.LASTNAME.ordinal()),
                infoList.get(ConstantAccount.AccountInfo.ADDRESS.ordinal()),
                infoList.get(ConstantAccount.AccountInfo.PHONE.ordinal()),
                Integer.parseInt(infoList.get(ConstantAccount.AccountInfo.STATUS.ordinal())));
    }

    @Override
    public String toString() {
        return this.id + "|" + this.username + "|" +
                this.password + "|" + this.firstName + "|" +
                this.lastName + "|" + this.address +
                "|" + this.phoneNumber + "|" + this.status.ordinal() +
                "|" + "0|0";
    }


}
