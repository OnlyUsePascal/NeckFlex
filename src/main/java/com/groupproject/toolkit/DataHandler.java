package com.groupproject.toolkit;

import com.groupproject.entity.generic.*;
import com.groupproject.entity.EntityHandler;
import com.groupproject.controller.ViewHandler;

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
        getCart();
        getOrder();
        getBank();
        System.out.println("===== done =====");

    }

    public static void saveData(){
        System.out.println("===== save data =====");
        saveAccount();
        saveItem();
        saveCart();
        saveOrder();
        saveBank();
        System.out.println("===== done =====");

    }

    //================== GET ===================
    public static void getAccount(){
        File file = ViewHandler.getFile(PathHandler.getFileTextAccount());

        try {
            Scanner my_reader = new Scanner(file);
            while (my_reader.hasNextLine()) {
                String data = my_reader.nextLine();
                if (data.length() == 0) break;

                StringTokenizer st = new StringTokenizer(data, "|");
                ArrayList<String> infoList = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    infoList.add(st.nextToken());
                }

                EntityHandler.restoreAccount(infoList);
            }
        } catch (FileNotFoundException err){
            err.printStackTrace();
        }
    }

    public static void getItem(){
        File file = ViewHandler.getFile(PathHandler.getFileTextItem());

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

                EntityHandler.restoreItem(infoList);
            }
        } catch(FileNotFoundException err){
            err.printStackTrace();
        }
    }

    public static void getCart(){
        File file = ViewHandler.getFile(PathHandler.getFileTextCart());

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

                //basic
                String username = infoList.get(0);
                // System.out.println(username);
                AccountCustomer user = (AccountCustomer) EntityHandler.accountFromUsername(username);
                Cart cart = user.getCart1();

                //detail
                for (int i = 1 ; i < infoList.size() ; i++) {
                    StringTokenizer st2 = new StringTokenizer(infoList.get(i), "/");
                    ArrayList<String> itemInfo = new ArrayList<>();

                    while (st2.hasMoreTokens()) {
                        itemInfo.add(st2.nextToken());
                    }

                    Item item = EntityHandler.getItemList().get(Integer.parseInt(itemInfo.get(0)));
                    int quantity = Integer.parseInt(itemInfo.get(1));
                    cart.addCartDetail(item, quantity);
                }

            }
        } catch(FileNotFoundException err){
            err.printStackTrace();
        }
    }

    public static void getOrder(){
        File file = ViewHandler.getFile(PathHandler.getFileTextOrder());

        try {
            Scanner my_reader = new Scanner(file);
            while(my_reader.hasNextLine()){
                String data = my_reader.nextLine();
                if (data.length() == 0) break;

                StringTokenizer st = new StringTokenizer(data, "@");
                ArrayList<String> infoList = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    infoList.add(st.nextToken());
                }

                //basic
                AccountCustomer user = (AccountCustomer) EntityHandler.accountFromUsername(infoList.get(0));

                //order
                for (int i = 1; i < infoList.size(); i++){
                    Order order = new Order();

                    StringTokenizer st2 = new StringTokenizer(infoList.get(i), "~");
                    ArrayList<String> orderInfo = new ArrayList<>();
                    while (st2.hasMoreTokens()) {
                        orderInfo.add(st2.nextToken());
                    }

                    order.setOwner(user);
                    order.setDate(orderInfo.get(0));
                    order.setDuration(Integer.parseInt(orderInfo.get(1)));

                    //order detail
                    for (int j = 2 ; j < orderInfo.size() ; j++){
                        String orderDetailInfo = orderInfo.get(j);
                        OrderDetail orderDetail = getOrderDetailFromString(orderDetailInfo);

                        order.addOrderDetail(orderDetail);
                    }

                    user.addOrder1(order);
                }
            }
        } catch(FileNotFoundException err){
            err.printStackTrace();
        }
    }

    public static OrderDetail getOrderDetailFromString(String orderDetailInfo){
        StringTokenizer st = new StringTokenizer(orderDetailInfo, "#");
        ArrayList<String> orderDetailList = new ArrayList<>();
        while (st.hasMoreTokens()) {
            orderDetailList.add(st.nextToken());
        }

        int quantity = Integer.parseInt(orderDetailList.get(0));
        boolean returned = Boolean.parseBoolean(orderDetailList.get(1));

        //1 item
        String itemInfo = orderDetailList.get(2);
        Item item = getItemFromString(itemInfo);

        OrderDetail orderDetail = new OrderDetail(item, quantity, returned);
        return orderDetail;
    }

    public static Item getItemFromString(String itemInfo){
        StringTokenizer st = new StringTokenizer(itemInfo, "|");
        ArrayList<String> itemInfoList = new ArrayList<>();
        while (st.hasMoreTokens()) {
            itemInfoList.add(st.nextToken());
        }

        return Item.getRestoreItem(itemInfoList);
    }

    public static void getBank(){
        File file = ViewHandler.getFile(PathHandler.getFileTextBank());

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

                String number = infoList.get(0);
                String name = infoList.get(1);
                double balance = Double.parseDouble(infoList.get(2));

                EntityHandler.addRestoredBankAcc(number, name, balance);
            }

        } catch (FileNotFoundException err){
            err.printStackTrace();
        }
    }


    //================== SAVE ===================
    public static void saveAccount(){
        File file = ViewHandler.getFile(PathHandler.getFileTextAccount());

        try{
            PrintWriter printWriter = new PrintWriter(file);
            for(Account account : EntityHandler.getAccountList()){
                printWriter.println(account);
            }

            printWriter.close();
            System.out.println("Save account data successfully!");

        }catch(IOException err){
            err.printStackTrace();
        }
    }

    public static void saveItem(){
        File file = ViewHandler.getFile(PathHandler.getFileTextItem());

        try{
            PrintWriter printWriter = new PrintWriter(file);

            for (Item item : EntityHandler.getItemList()) {
                printWriter.println(item);
            }

            printWriter.close();
            System.out.println("Save item successfully!");

        }catch(IOException err){
            err.printStackTrace();
        }
    }

    public static void saveCart(){
        File file = ViewHandler.getFile(PathHandler.getFileTextCart());

        try{
            PrintWriter printWriter = new PrintWriter(file);

            for (Account user : EntityHandler.getAccountList()) {
                if (user.isAdmin()) continue;

                ((AccountCustomer) user).getCart1().refreshCart();
                String cartInfo = ((AccountCustomer) user).getCart1().toString();
                printWriter.println(cartInfo);
            }

            printWriter.close();
            System.out.println("Save cart successfully!");

        }catch(IOException e2){
            e2.printStackTrace();
        }
    }

    public static void saveOrder(){
        File file = ViewHandler.getFile(PathHandler.getFileTextOrder());

        try {
            PrintWriter printWriter = new PrintWriter(file);

            for (Account user : EntityHandler.getAccountList()) {
                if (user.isAdmin()) continue;

                String orderInfo = ((AccountCustomer) user).getOrderListInfo1();
                printWriter.println(orderInfo);
            }

            printWriter.close();
            System.out.println("Save order successfully!");
        }catch(IOException e2){
            e2.printStackTrace();
        }
    }

    public static void saveBank(){
        File file = ViewHandler.getFile(PathHandler.getFileTextBank());

        try {
            PrintWriter printWriter = new PrintWriter(file);

            for (BankAccount bankAccount : EntityHandler.getBankAccountList()) {
                printWriter.println(bankAccount);
            }

            printWriter.close();
            System.out.println("Save bank successfully!");
        } catch (IOException err){
            err.printStackTrace();
        }
    }
}
