package com.groupproject.toolkit;

import com.groupproject.entity.generic.*;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.entity.Constant.ConstantAccount;
import com.groupproject.entity.runtime.ViewHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DataHandler {
    public static void getData(){
        System.out.println("===== get data =====");
        getAccount();
        getItem();
        System.out.println("===== done =====");

    }

    public static void saveData(){
        System.out.println("===== save data =====");
        saveAccount();
        saveItem();
        System.out.println("===== done =====");

    }

    //================== GET ===================
    public static void getAccount(){
        File file = ViewHandler.getFile(PathHandler.getMediaTextAccount());

        try {
            Scanner my_reader = new Scanner(file);
            while (my_reader.hasNextLine()) {
                String data = my_reader.nextLine();
                StringTokenizer st = new StringTokenizer(data, "|");
                ArrayList<String> list = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    list.add(st.nextToken());
                }

                Account account = new Account(
                        list.get(ConstantAccount.AccountInfo.ID.ordinal()),
                        list.get(ConstantAccount.AccountInfo.USERNAME.ordinal()),
                        list.get(ConstantAccount.AccountInfo.PASSWORD.ordinal()),
                        list.get(ConstantAccount.AccountInfo.FIRSTNAME.ordinal()),
                        list.get(ConstantAccount.AccountInfo.LASTNAME.ordinal()),
                        list.get(ConstantAccount.AccountInfo.ADDRESS.ordinal()),
                        list.get(ConstantAccount.AccountInfo.PHONE.ordinal()),
                        Integer.parseInt(list.get(ConstantAccount.AccountInfo.STATUS.ordinal())),
                        Double.parseDouble(list.get(ConstantAccount.AccountInfo.BALANCE.ordinal())),
                        Integer.parseInt(list.get(ConstantAccount.AccountInfo.REWARDPOINT.ordinal()))
                );

                EntityHandler.accountAdd(account);
                System.out.println(account);
                // System.out.println(list);
            }
        } catch (FileNotFoundException err){
            err.printStackTrace();
        }
    }

    public static void getItem(){
        File file = ViewHandler.getFile(PathHandler.getMediaTextItem());

        try {
            Scanner my_reader = new Scanner(file);
            while(my_reader.hasNextLine()){
                String data = my_reader.nextLine();
                if (data.length() == 0) break;

                StringTokenizer st = new StringTokenizer(data, "|");
                ArrayList<String> infoList = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    infoList.add(st.nextToken());
                }

                System.out.println(infoList.toString());

                Item newItem = EntityHandler.itemFromInfo(infoList);
                EntityHandler.itemAdd(newItem);
                Item.genericId++;
            }
        } catch(FileNotFoundException e){
            System.out.println("File not Found!");
        }
    }

    //================== SAVE ===================
    public static void saveAccount(){
        File file = ViewHandler.getFile(PathHandler.getMediaTextAccount());

        try{
            PrintWriter printWriter = new PrintWriter(file);
            for(Account account : EntityHandler.accountListGet()){
                printWriter.println(account);
            }

            printWriter.close();
            System.out.println("Save account data successfully!");

        }catch(IOException e2){
            e2.printStackTrace();
        }
    }

    public static void saveItem(){
        File file = ViewHandler.getFile(PathHandler.getMediaTextItem());

        try{
            PrintWriter printWriter = new PrintWriter(file);

            for (Item item : EntityHandler.itemListAdd()) {
                printWriter.println(item);
            }

            printWriter.close();
            System.out.println("Save data successfully!");

        }catch(IOException e2){
            System.out.println("An error occurred.");
            e2.printStackTrace();
        }
    }

}
