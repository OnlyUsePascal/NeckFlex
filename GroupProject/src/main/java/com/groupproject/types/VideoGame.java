package com.groupproject.types;

import com.groupproject.Item.Item;

public class VideoGame extends Item {
    public VideoGame(String id, String title, String genre, String rentalType, String loanType, int numberOfCopies, double rentalFee, String rentalStatus) {
        super(id, title, genre, rentalType, loanType, numberOfCopies, rentalFee, rentalStatus);
    }
    public static VideoGame addVideoGame(String title, String category, String rentalType, String loanType, int numberOfCopies, double rentalFee, String rentalStatus, int year){
        VideoGame newVideoGame = new VideoGame(convertItemId(year),title, category, rentalType, loanType, numberOfCopies, rentalFee, rentalStatus);
        return newVideoGame;
    }
}
