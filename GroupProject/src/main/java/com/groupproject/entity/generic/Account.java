package com.groupproject.entity.generic;

import com.groupproject.toolkit.Constant.ConstantUser;

public class Account {
    protected String _id;
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String phone;
    protected ConstantUser accountType;

    public ConstantUser getAccountType() {
        return accountType;
    }

}
