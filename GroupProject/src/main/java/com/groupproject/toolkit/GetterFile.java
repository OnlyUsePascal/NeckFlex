package com.groupproject.toolkit;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
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

    public static FXMLLoader getFXMLLoader(String url){
        FXMLLoader fxmlLoader = new FXMLLoader(GetterFile.class.getResource(url));
        try {
            fxmlLoader.load();
            return fxmlLoader;
        } catch (Exception err){
            err.printStackTrace();
            return null;
        }
    }

    public static Image getImage(String url){
        try {
            Image img = new Image(GetterFile.class.getResourceAsStream(GetterPath.getMediaImage(url)));
            return img;
        } catch (Exception err){
            err.printStackTrace();
            return null;
        }
    }

}
