package com.groupproject;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

enum  CustomerID {
    ID,
    USERNAME,
    PASSWORD,
    FIRSTNAME,
    LASTNAME,
    ADDRESS,
    PHONE
}

public class IOStream {
    static void loadData() throws IOException {

        String filePath = new File("GroupProject/src/main/resources/data/Customer.txt").getAbsolutePath();
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
                            list.get(CustomerID.ID.ordinal()),
                            list.get(CustomerID.USERNAME.ordinal()),
                            list.get(CustomerID.PASSWORD.ordinal()),
                            list.get(CustomerID.FIRSTNAME.ordinal()),
                            list.get(CustomerID.LASTNAME.ordinal()),
                            list.get(CustomerID.ADDRESS.ordinal()),
                            list.get(CustomerID.PHONE.ordinal())
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

    static void saveData() throws IOException {

        //Get Absolute Path
        String filePath = new File("GroupProject/src/main/resources/data/Customer.txt").getAbsolutePath();
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

    public static void main(String[] args) throws IOException {
        SystemShop systemShop = new SystemShop();
        loadData();
        systemShop.displayAccount();
        saveData();
    }
}
