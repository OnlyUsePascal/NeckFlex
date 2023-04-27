package com.groupproject;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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

        InputStream inputStream = IOStream.class.getResourceAsStream("/data/Customer.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String data;
        while ((data = reader.readLine()) != null) {
            // Process each line of data here
//            System.out.println(data);

            StringTokenizer st = new StringTokenizer(data, "|");
            ArrayList<String> list = new ArrayList<>();
            while (st.hasMoreTokens()) {
                list.add(st.nextToken());
            }
//            System.out.println(list.size());
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
        }
        reader.close();
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
