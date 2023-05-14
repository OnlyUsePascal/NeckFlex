package com.groupproject.entity.Constant;

public class ConstantAccount {
    static public String[] statusList = {"Admin", "Guest", "Regular", "VIP"};

    static public enum AccountStatus{
        ADMIN,
        GUEST,
        REGULAR,
        VIP
    }

    static public AccountStatus getStatus(int index){
        for (AccountStatus st : AccountStatus.values()){
            if (st.ordinal() == index)
                return st;
        }

        return null;
    }

    static public enum AccountInfo{
        ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, ADDRESS, PHONE, STATUS
    }
}