package com.groupproject.entity.current;

import com.groupproject.entity.generic.Account;

public class CurrentUser {
    private static Account currentUser;

    public static void setCurrentUser(Account acc) {
        currentUser = acc;
    }

    public static Account getCurrentUser() {
        return currentUser;
    }

}
