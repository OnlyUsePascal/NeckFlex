package com.groupproject.entity.Constant;

public class ConstantOrder {
    static public final int rentingLimit = 2;
    static public enum OrderDuration{
        TWO_DAYS(2, "Two Days"),
        ONE_WEEK(7, "One Week");

        private int durationValue;
        private String durationName;

        OrderDuration (int durationValue, String durationName){
            this.durationValue = durationValue;
            this.durationName = durationName;
        }

        public int getDurationValue(){
            return durationValue;
        }

        public String getDurationName(){
            return durationName;
        }
    }

    static public enum OrderStatus{
        ACCEPTED,
        INSUFFICIENT_BALANCE,
        INSUFFICIENT_POINT,
        LIMITED_AMOUNT,
        OUT_OF_STOCK,
    }
}
