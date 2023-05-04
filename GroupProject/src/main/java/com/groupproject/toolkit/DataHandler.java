package com.groupproject.toolkit;

import com.groupproject.entity.generic.*;
import com.groupproject.entity.runtime.ShopSystem;
import com.groupproject.toolkit.Constant.ConstantAccount;
import com.groupproject.toolkit.Constant.ConstantItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DataHandler {
    public static void getData(){
        getAccount();
        getItem();
    }

    public static void saveData(){
        saveAccount();
        saveItem();
    }

    //================== GET ===================
    public static void getAccount(){
        File file = ObjectHandler.getFile(PathHandler.getMediaTextAccount());

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
                        list.get(ConstantAccount.AccountInfo.PHONE.ordinal())
                );

                ShopSystem.addAccount(account);
                System.out.println(account);
            }
        } catch (FileNotFoundException err){
            err.printStackTrace();
        }
    }

    public static void getItem(){
        File file = ObjectHandler.getFile(PathHandler.getMediaTextItem());

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
                int category = Integer.parseInt(infoList.get(ConstantItem.ItemInfo.CATEGORY.ordinal()));
                switch (category){
                    case 0 -> {
                        ItemDvd itemDvd = new ItemDvd(infoList);
                        ShopSystem.addItem(itemDvd);
                    }
                    case 1 -> {
                        ItemRecord itemRecord = new ItemRecord(infoList);
                        ShopSystem.addItem(itemRecord);
                    }
                    case 2 -> {
                        ItemGame itemGame = new ItemGame(infoList);
                        ShopSystem.addItem(itemGame);
                    }
                }

                Item.genericId++;
            }
        } catch(FileNotFoundException e){
            System.out.println("File not Found!");
        }
    }

    //================== SAVE ===================
    public static void saveAccount(){
        File file = ObjectHandler.getFile(PathHandler.getMediaTextAccount());

        try{
            PrintWriter printWriter = new PrintWriter(file);
            for(Account account : ShopSystem.getAccountList()){
                printWriter.println(account);
            }

            printWriter.close();
            System.out.println("Save account data successfully!");

        }catch(IOException e2){
            e2.printStackTrace();
        }
    }

    public static void saveItem(){
        File file = ObjectHandler.getFile(PathHandler.getMediaTextItem());

        try{
            PrintWriter printWriter = new PrintWriter(file);

            for (Item item : ShopSystem.getItemList()) {
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
