package com.groupproject.entity.Constant;

public class ConstantOrder {
    static public enum OrderDuration{
        TWO_DAYS,
        ONE_WEEK,
    }

    static public enum OrderStatus{
        ACCEPTED,
        INSUFFICIENT_BALANCE,
        INSUFFICIENT_POINT,
        LIMITED_AMOUNT,
        OUT_OF_STOCK,
    }
}
