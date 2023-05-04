package com.groupproject;

import com.groupproject.types.SystemShop;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class AddictechShopApplication extends Application {
    public static Stage window;

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        SystemShop systemShop = new SystemShop();
        IOStream.loadData();
        LoginPage loginPage = new LoginPage();
        loginPage.start(stage);

        //Close program
        window.setOnCloseRequest(e -> {
            e.consume();
            try {
                IOStream.saveData();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            window.close();
        });

    }

    public static void main(String[] args) {
        launch();
    }


}
