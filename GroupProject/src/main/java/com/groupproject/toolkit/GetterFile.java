package com.groupproject.toolkit;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class GetterFile {
    public static AnchorPane getAnchorPane(String url){
        FXMLLoader fxmlLoader = new FXMLLoader(GetterFile.class.getResource(url));
        try{
            AnchorPane result = (AnchorPane) fxmlLoader.load();
            return result;
        } catch (Exception err){
            err.printStackTrace();
            return null;
        }
    }

}
