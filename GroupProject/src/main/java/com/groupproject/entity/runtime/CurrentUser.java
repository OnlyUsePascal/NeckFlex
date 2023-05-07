package com.groupproject.entity.runtime;

import com.groupproject.entity.generic.Account;

public class CurrentUser {
    private static Account currentUser ;
    // private static Account currentUser;

    public static void setCurrentUser(Account acc) {
        currentUser = acc;
    }

    public static Account getCurrentUser() {
        return Account.getNewAccount("joun", "123", "dat", "nguyen", "123", "123");
        // return currentUser;
    }

}
