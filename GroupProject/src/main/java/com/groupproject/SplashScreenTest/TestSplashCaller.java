package com.groupproject.SplashScreenTest;

import com.groupproject.Item.ItemView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

public class TestSplashCaller extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        SplashScreen splashScreen = new SplashScreen();
        splashScreen.show();

        // Simulate data loading in a separate thread
        Thread loadingThread = new Thread(() -> {

            // Simulate data loading delay
            long INF = (long) 5e10;
            for (long i = 0; i < INF; i++) {
            }

            // Data loading complete, hide the splash screen and show the main stage
            Platform.runLater(() -> {
                splashScreen.hide();

                try {
                    ItemView item = new ItemView();
                    item.start(new Stage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });

        loadingThread.start();
    }

    public static void main(String[] args) {
        launch();
    }
}