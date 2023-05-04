package com.groupproject;

import com.groupproject.types.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

enum AccountLabel {
    ID,
    USERNAME,
    PASSWORD,
    FIRSTNAME,
    LASTNAME,
    ADDRESS,
    PHONE,
    BALANCE,
    REWARDPOINT
}

enum  ItemLabel {
    CATEGORY,
    ID,
    TITLE,
    GENRE,
    RENTALTYPE,
    LOANTYPE,
    NUMBEROFCOPIES,
    RENTALFEE,
    RENTALSTATUS
}

public class IOStream {

    static void loadAccountData() throws IOException{

        String filePath = new File("src/main/resources/data/Customer.txt").getAbsolutePath();
//        String pathrtest = "/run/media/kaito/DATA/OOP/OOP-Group-Project/GroupProject/src/main/resources/data/Customer.txt";
        File myfile = new File(filePath);
        System.out.println(myfile);

        if (myfile.exists()) {
//            System.out.println("File name: " + myfile.getName());
//            System.out.println("Absolute path: " + myfile.getAbsolutePath());
//            System.out.println("Writeable: " + myfile.canWrite());
//            System.out.println("Readable " + myfile.canRead());
//            System.out.println("File size in bytes " + myfile.length());

            // Read file
            try {
                Scanner my_reader = new Scanner(myfile);
                while(my_reader.hasNextLine()){
                    String data = my_reader.nextLine();
                    StringTokenizer st = new StringTokenizer(data, "|");
                    ArrayList<String> list = new ArrayList<>();
                    while (st.hasMoreTokens()) {
                        list.add(st.nextToken());
                    }
                    Account account = new Account(
                            list.get(AccountLabel.ID.ordinal()),
                            list.get(AccountLabel.USERNAME.ordinal()),
                            list.get(AccountLabel.PASSWORD.ordinal()),
                            list.get(AccountLabel.FIRSTNAME.ordinal()),
                            list.get(AccountLabel.LASTNAME.ordinal()),
                            list.get(AccountLabel.ADDRESS.ordinal()),
                            list.get(AccountLabel.PHONE.ordinal()),
                            Double.parseDouble(list.get(AccountLabel.BALANCE.ordinal())),
                            Double.parseDouble(list.get(AccountLabel.REWARDPOINT.ordinal()))
                    );
                    SystemShop.addAccount(account);
                    Account.customerID++;
                }
            } catch(FileNotFoundException e){
                System.out.println("File not Found!");
            }
        } else {
            System.out.println("The file does not exist.");
        }
    }

    static void loadItemData() throws IOException{
        String filePath = new File("src/main/resources/data/Item.txt").getAbsolutePath();
        File myfile = new File(filePath);
        System.out.println(myfile);
        if (myfile.exists()) {
//            System.out.println("File name: " + myfile.getName());
//            System.out.println("Absolute path: " + myfile.getAbsolutePath());
//            System.out.println("Writeable: " + myfile.canWrite());
//            System.out.println("Readable " + myfile.canRead());
//            System.out.println("File size in bytes " + myfile.length());

            // Read file
            try {
                Scanner my_reader = new Scanner(myfile);
                while(my_reader.hasNextLine()){
                    String data = my_reader.nextLine();
                    StringTokenizer st = new StringTokenizer(data, "|");
                    ArrayList<String> list = new ArrayList<>();
                    while (st.hasMoreTokens()) {
                        list.add(st.nextToken());
                    }
                    if(list.get(ItemLabel.CATEGORY.ordinal()).equals("0")){
                        System.out.println("DVD Loading!");
                        DVD dvd = new DVD(
                                list.get(ItemLabel.ID.ordinal()),
                                list.get(ItemLabel.TITLE.ordinal()),
                                list.get(ItemLabel.GENRE.ordinal()),
                                list.get(ItemLabel.RENTALTYPE.ordinal()),
                                list.get(ItemLabel.LOANTYPE.ordinal()),
                                Integer.parseInt(list.get(ItemLabel.NUMBEROFCOPIES.ordinal())),
                                Double.parseDouble(list.get(ItemLabel.RENTALFEE.ordinal())),
                                list.get(ItemLabel.RENTALSTATUS.ordinal())
                        );
                        SystemShop.addItem(dvd);
                        SystemShop.addDVD(dvd);
                        Item.itemID++;


                    }else if(list.get(ItemLabel.CATEGORY.ordinal()).equals("1")){
                        System.out.println("MovieRecord Loading!");
                        MovieRecord movieRecord = new MovieRecord(
                                list.get(ItemLabel.ID.ordinal()),
                                list.get(ItemLabel.TITLE.ordinal()),
                                list.get(ItemLabel.GENRE.ordinal()),
                                list.get(ItemLabel.RENTALTYPE.ordinal()),
                                list.get(ItemLabel.LOANTYPE.ordinal()),
                                Integer.parseInt(list.get(ItemLabel.NUMBEROFCOPIES.ordinal())),
                                Double.parseDouble(list.get(ItemLabel.RENTALFEE.ordinal())),
                                list.get(ItemLabel.RENTALSTATUS.ordinal())
                        );
                        SystemShop.addItem(movieRecord);
                        SystemShop.addMovieRecord(movieRecord);
                        Item.itemID++;


                    }else{
                        System.out.println("VideoGame Loading!");
                        VideoGame videoGame = new VideoGame(
                                list.get(ItemLabel.ID.ordinal()),
                                list.get(ItemLabel.TITLE.ordinal()),
                                list.get(ItemLabel.GENRE.ordinal()),
                                list.get(ItemLabel.RENTALTYPE.ordinal()),
                                list.get(ItemLabel.LOANTYPE.ordinal()),
                                Integer.parseInt(list.get(ItemLabel.NUMBEROFCOPIES.ordinal())),
                                Double.parseDouble(list.get(ItemLabel.RENTALFEE.ordinal())),
                                list.get(ItemLabel.RENTALSTATUS.ordinal())
                        );

                        SystemShop.addItem(videoGame);
                        SystemShop.addVideoGame(videoGame);
                        Item.itemID++;
                    }
                }
            } catch(FileNotFoundException e){
                System.out.println("File not Found!");
            }
        } else {
            System.out.println("The file does not exist.");
        }
    }
    public static void loadData() throws IOException {

        loadAccountData();
        loadItemData();

    }

    static void saveAccountData() throws IOException{
        //Get Absolute Path
        String filePath = new File("src/main/resources/data/Customer.txt").getAbsolutePath();
//        System.out.println(filePath);

        //Write to file
        try{
            File myfile = new File(filePath);
            PrintWriter printWriter = new PrintWriter(myfile);
            for(Account account : SystemShop.getAccountLists()){
                printWriter.println(account.Account2Str());
            }
            printWriter.close();
            System.out.println("Save data successfully!");

        }catch(IOException e2){
            System.out.println("An error occurred.");
            e2.printStackTrace();
        }
    }

    static void saveItemData() throws IOException{
        //Get Absolute Path
        String filePath = new File("src/main/resources/data/Item.txt").getAbsolutePath();
//        System.out.println(filePath);

//        Write to file
        try{
            File myfile = new File(filePath);
            PrintWriter printWriter = new PrintWriter(myfile);

            for (Item item : SystemShop.getItemLists()) {
                    if(item instanceof DVD){
                        printWriter.print("0|");
                    }else if(item instanceof MovieRecord){
                        printWriter.print("1|");
                    }else{
                        printWriter.print("2|");
                    }
                printWriter.println(item.Item2Str());
            }

            printWriter.close();
            System.out.println("Save data successfully!");

        }catch(IOException e2){
            System.out.println("An error occurred.");
            e2.printStackTrace();
        }
    }

    public static void saveData() throws IOException {
        saveAccountData();
        saveItemData();

    }

    public static void main(String[] args) throws IOException {
        SystemShop systemShop = new SystemShop();
        loadData();
        systemShop.displayAccount();
        systemShop.displayItem();
        saveData();
    }
}
