package com.groupproject.entity.current;

import com.groupproject.entity.generic.Account;

public class CurrentUser {
    private static Account currentAcc;

    public static void setCurrentAcc(Account acc) {
        currentAcc = acc;
    }

    public static Account getCurrentAcc() {
        return currentAcc;
    }
}
