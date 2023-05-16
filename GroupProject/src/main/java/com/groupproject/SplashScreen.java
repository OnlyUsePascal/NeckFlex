package com.groupproject;

import javafx.stage.Stage;

import java.io.IOException;

public class SplashScreen {

    private Stage splashStage;

    public SplashScreen() throws IOException {
        splashStage = new Stage();
        SplashUI splashUI = new SplashUI();
        splashUI.start(splashStage);
    }

    public void show() {
        splashStage.show();
    }

    public void hide() {
        splashStage.close();
    }
}
