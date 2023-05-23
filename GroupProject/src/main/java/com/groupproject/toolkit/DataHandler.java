package com.groupproject.toolkit;

import com.groupproject.entity.generic.*;
import com.groupproject.entity.EntityHandler;
import com.groupproject.entity.Constant.ConstantAccount;
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
        System.out.println("===== done =====");

    }

    public static void saveData(){
        System.out.println("===== save data =====");
        saveAccount();
        // saveItem();
        saveCart();
        saveOrder();
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

                EntityHandler.addAccount(account);
                System.out.println(account);
                // System.out.println(list);
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

                System.out.println(infoList);
                Item newItem = EntityHandler.getRestoreItem(infoList);
                EntityHandler.addItem(newItem);
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
                Account user = EntityHandler.accountFromUsername(username);
                Cart cart = user.getCart();

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
                Account user = EntityHandler.accountFromUsername(infoList.get(0));

                //order
                for (int i = 1; i < infoList.size(); i++){
                    Order order = new Order();

                    StringTokenizer st2 = new StringTokenizer(infoList.get(i), "~");
                    ArrayList<String> orderInfo = new ArrayList<>();
                    while (st2.hasMoreTokens()) {
                        orderInfo.add(st2.nextToken());
                    }

                    order.setUser(user);
                    order.setDate(orderInfo.get(0));
                    order.setDuration(Integer.parseInt(orderInfo.get(1)));

                    //order detail
                    for (int j = 2 ; j < orderInfo.size() ; j++){
                        String orderDetailInfo = orderInfo.get(j);
                        OrderDetail orderDetail = getOrderDetailFromString(orderDetailInfo);

                        order.addOrderDetail(orderDetail);
                    }

                    user.addOrder(order);
                }
            }
        } catch(FileNotFoundException err){
            err.printStackTrace();
        }
    }

    public static OrderDetail getOrderDetailFromString(String orderDetailInfo){
        StringTokenizer st = new StringTokenizer(orderDetailInfo, "/");
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

        return EntityHandler.getRestoreItem(itemInfoList);
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

                user.getCart().refreshCart();
                String cartInfo = user.getCart().getCartInfo();
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

                String orderInfo = user.getOrderListInfo();
                printWriter.println(orderInfo);
            }

            printWriter.close();
            System.out.println("Save order successfully!");
        }catch(IOException e2){
            e2.printStackTrace();
        }
    }
}
