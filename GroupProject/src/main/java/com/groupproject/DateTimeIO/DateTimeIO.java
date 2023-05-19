package com.groupproject.DateTimeIO;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DateTimeIO {
    //save LocalDateTime to file

    private static ArrayList<LocalDateTime> dateList = new ArrayList<>();
    public static void saveData(){
        LocalDateTime now = LocalDateTime.now();
        dateList.add(now);
        System.out.println(now);
        //Get Absolute Path
        String filePath = new File("GroupProject/src/main/resources/data/Date.txt").getAbsolutePath();
//        System.out.println(filePath);

        //Write to file
        try{
            File myfile = new File(filePath);
            PrintWriter printWriter = new PrintWriter(myfile);
            //save data with only year, month and day
            for(LocalDateTime date : dateList){
                printWriter.println(date.getYear() + "|" + date.getMonthValue() + "|" + date.getDayOfMonth());
            }
            printWriter.close();
            System.out.println("Save data successfully!");

        }catch(IOException e2){
            System.out.println("An error occurred.");
            e2.printStackTrace();
        }
    }

    public static void loadData(){
        String filePath = new File("GroupProject/src/main/resources/data/Date.txt").getAbsolutePath();

        File myfile = new File(filePath);
        System.out.println(myfile);

        if (myfile.exists()) {


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
                    int year = Integer.parseInt(list.get(0));
                    int month = Integer.parseInt(list.get(1));
                    int day = Integer.parseInt(list.get(2));
                    LocalDate date = LocalDate.of(year, month, day);
                    LocalDateTime dateTime = date.atStartOfDay();
                    System.out.println(dateTime);
                    dateList.add(dateTime);
                }
            } catch(FileNotFoundException e){
                System.out.println("File not Found!");
            }
        } else {
            System.out.println("The file does not exist.");
        }
    }

    public static void main(String[] args) {
        loadData();
        saveData();
    }
}
