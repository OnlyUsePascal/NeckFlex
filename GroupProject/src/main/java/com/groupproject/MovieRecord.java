package com.groupproject;

public class MovieRecord extends Item{
    public MovieRecord(String id, String title, String genre, String rentalType, String loanType, int numberOfCopies, double rentalFee, String rentalStatus) {
        super(id, title, genre, rentalType, loanType, numberOfCopies, rentalFee, rentalStatus);
    }
    public static MovieRecord addMovieRecord(String title, String category, String rentalType, String loanType, int numberOfCopies, double rentalFee, String rentalStatus, int year){
        MovieRecord newMovieRecord = new MovieRecord(convertItemId(year),title, category, rentalType, loanType, numberOfCopies, rentalFee, rentalStatus);
        return newMovieRecord;
    }
}

