package com.groupproject.entity.Constant;

public class ConstantAccount {
    static public String[] statusList = {"Admin", "Guest", "Regular", "VIP"};

    static public enum AccountStatus{
        ADMIN, GUEST, REGULAR, VIP;
    }

    static public enum AccountInfo{
        ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESS, PHONE
    }
}