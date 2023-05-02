package com.groupproject.toolkit;

import com.groupproject.entity.current.ShopSystem;
import com.groupproject.entity.generic.Account;
import com.groupproject.toolkit.Constant.ConstantAccount;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DataHandler {
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

    public static void saveAccount(){
        File file = ObjectHandler.getFile(PathHandler.getMediaTextAccount());

        try{
            PrintWriter printWriter = new PrintWriter(file);
            for(Account account : ShopSystem.getAccountList()){
                printWriter.println(account);
            }

            printWriter.close();
            System.out.println("Save data successfully!");

        }catch(IOException e2){
            e2.printStackTrace();
        }
    }
}
