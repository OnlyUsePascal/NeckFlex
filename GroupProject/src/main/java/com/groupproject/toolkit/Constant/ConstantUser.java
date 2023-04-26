package com.groupproject.toolkit.Constant;

public enum ConstantUser {
    ADMIN(-1), GUEST(0), REGULAR(1), VIP(2);

    private int value;
    ConstantUser(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ConstantUser getStatus(int value) {
        for (ConstantUser s : ConstantUser.values()) {
            if (s.getValue() == value) {
                return s;
            }
        }
        return null;
    }
}