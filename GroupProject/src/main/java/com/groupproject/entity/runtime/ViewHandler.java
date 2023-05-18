package com.groupproject.entity.runtime;

import com.groupproject.controller.component.NavBarController;
import com.groupproject.controller.component.SidebarController;
import com.groupproject.controller.page.HomeController;
import com.groupproject.controller.page.UserRecordController;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;

public class ViewHandler {
    static private HomeController homeController;
    static private NavBarController navBarController;
    static private SidebarController sidebarController;
    static private UserRecordController userRecordController;
    static private Stage currentStage;


    //=========== CONTROLLER ============
    //home
    static public void setHomeController(HomeController homeController) {
        ViewHandler.homeController = homeController;
    }

    static public void setPageContent(String url){
        homeController.setPageContent(url);
    }

    //nav bar
    static public void setNavBarController(NavBarController navBarController) {
        ViewHandler.navBarController = navBarController;
    }

    static public void refreshMenuButtonName(){
        navBarController.refreshMenuButtonName();
    }

    //sidebar
    static public void setSidebarController(SidebarController sidebarController) {
        ViewHandler.sidebarController = sidebarController;
    }

    static public void setMenuActive(){
        sidebarController.menuActive(null);
    }

    //user record
    static public void setUserRecordController(UserRecordController controller){
        ViewHandler.userRecordController = controller;
    }

    static public UserRecordController getUserRecordController(){
        return userRecordController;
    }


    //=========== SCENE, PANE, .. ============
    static public Stage getCurrentStage() {
        return currentStage;
    }

    static public void setCurrentStage(Stage stage) {
        currentStage = stage;
    }

    static public void closePopup(ActionEvent event) {
        Node node = (Node) event.getSource();
        Window window =  node.getScene().getWindow();
        window.hide();
    }

    static public Popup getPopup(AnchorPane pane, EventHandler<WindowEvent> popupOnClose){
        Stage stage = getCurrentStage();

        //popup
        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.getContent().add(pane);
        popup.setOnHidden(popupOnClose);
        popup.show(stage);

        //to middle
        AnchorPane contentPane = (AnchorPane) popup.getContent().get(0);
        double x = stage.getX() + (stage.getWidth() - popup.getWidth()) / 2;
        double y = stage.getY() + (stage.getHeight() - popup.getHeight()) / 2;
        popup.setX(x);
        popup.setY(y);

        // System.out.println(window.getX() + " " + window.getY() + " " + window.getWidth() + " " + window.getHeight());
        // System.out.println(pane.getWidth() + " " + pane.getHeight());
        // System.out.println(stage.getScene().getWidth());
        // System.out.println(popup.getWidth());
        // System.out.println(x + " " + y);
        return popup;
    }

    static public void hideNode(Node node){
        node.setVisible(!node.isVisible());
        node.setDisable(!node.isDisable());
    }

    static public void setScene(Scene scene, String url){
        FXMLLoader loader = new FXMLLoader(ViewHandler.class.getResource(url));
        try {
            scene.setRoot(loader.load());
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    static public Window getWindow(ActionEvent event){
        Node node = (Node) event.getSource();
        Window window =  node.getScene().getWindow();
        return window;
    }

    //=========== PANE ============
    static public void setAnchorPane(AnchorPane frame, Node node){
        // if (true) {
        if (frame.getChildren().size() == 0) {
            frame.getChildren().add(node);
        } else {
            frame.getChildren().set(0, node);
        }
    }

    static public void setAnchorPane(AnchorPane frame, String url){
        FXMLLoader fxmlLoader = new FXMLLoader(ViewHandler.class.getResource(url));
        try {
            AnchorPane node = (AnchorPane) fxmlLoader.load();
            setAnchorPane(frame, node);
        } catch (Exception err){
            err.printStackTrace();
        }
    }

    static public AnchorPane getAnchorPane(String url){
        FXMLLoader fxmlLoader = new FXMLLoader(ViewHandler.class.getResource(url));
        try {
            AnchorPane node = (AnchorPane) fxmlLoader.load();
            return node;
        } catch (Exception err){
            err.printStackTrace();
            return null;
        }
    }

    static public void lockHorizonScroll(Node pane){
        pane.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaX() != 0) {
                    event.consume();
                }
            }
        });
    }

    //=========== FILE ============
    static public File getFile(String url){
        System.out.println(url);
        File file = new File(url);
        if (file.exists()) {
            System.out.println("File found");
            return file;
        }
        System.out.println("File not found:");
        return null;
    }

    static public Image getImage(String imgName){
        try {
            String url = PathHandler.getMediaImage(imgName);
            Image img = new Image(ViewHandler.class.getResourceAsStream(PathHandler.getMediaImage(imgName)));
            return img;
        } catch (Exception err){
            err.printStackTrace();
            return null;
        }
    }

    static public Image getImageItem(String imgName){
        try {
            Image img = new Image(ViewHandler.class.getResourceAsStream(PathHandler.getMediaImageItem(imgName)));
            return img;
        } catch (Exception err){
            err.printStackTrace();
            return null;
        }
    }

    static public void copyImageToResource(URI filePath, String newName){
        Path from = Paths.get(filePath);
        String toPath = PathHandler.getFileImageItem(newName);
        Path to = Paths.get(toPath);
        // System.out.println(Files.exists(to));

        try {
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public void fillShapeWithImage(String imgName, Shape frame){
        String url = PathHandler.getFileImageItem(imgName);
        Path path = Paths.get(url);

        Image img = new Image(path.toUri().toString());
        ImagePattern imgView = new ImagePattern(img);
        frame.setFill(imgView);
    }

    static public void deleteItemImage(String imgName){
        String url = PathHandler.getFileImageItem(imgName);
        Path path = Paths.get(url);
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //=========== STRING, NUMBER ============
    static public boolean checkStringCharacterOnly(String str){
        // System.out.println(str);
        return str.matches("[a-zA-Z ]+");
    }

    static public boolean checkStringNumberOnly(String str){
        // System.out.println(str);
        return str.matches("[0-9]+");
    }

    static public boolean checkStringGeneral(String str){
        // System.out.println(str);
        return !str.matches(".*\\|.*") && str.length() != 0;
    }

    static public double getDoubleRound(double num){
        return Math.round(num * 100.0) / 100.0;
    }

    static public boolean checkStringSimilar(String frame, String target){
        return frame.toLowerCase().contains(target.toLowerCase());
    }
}
