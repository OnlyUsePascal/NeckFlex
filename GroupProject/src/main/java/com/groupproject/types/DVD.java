package com.groupproject.types;

public class DVD extends Item {

    public DVD(String id, String title, String genre, String rentalType, String loanType, int numberOfCopies, double rentalFee, String rentalStatus) {
        super(id, title, genre, rentalType, loanType, numberOfCopies, rentalFee, rentalStatus);
    }

    public static DVD addDVD(String title, String category, String rentalType, String loanType, int numberOfCopies, double rentalFee, String rentalStatus, int year){
        DVD newDVD = new DVD(convertItemId(year),title, category, rentalType, loanType, numberOfCopies, rentalFee, rentalStatus);
        return newDVD;
    }
}
