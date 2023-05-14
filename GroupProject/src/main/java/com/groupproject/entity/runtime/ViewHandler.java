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
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;

public class ViewHandler {
    static private HomeController homeController;
    static private NavBarController navBarController;
    static private SidebarController sidebarController;
    static private UserRecordController userRecordController;
    static private Stage currentStage;


    //=========== CONTROLLER ============
    //home
    static public void homeControllerSet(HomeController homeController) {
        ViewHandler.homeController = homeController;
    }

    static public void setPageContent(String url){
        homeController.setPageContent(url);
    }

    //nav bar
    static public void navBarControllerSet(NavBarController navBarController) {
        ViewHandler.navBarController = navBarController;
    }

    static public void refreshMenuButtonName(){
        navBarController.refreshMenuButtonName();
    }

    //sidebar
    static public void sidebarControllerSet(SidebarController sidebarController) {
        ViewHandler.sidebarController = sidebarController;
    }

    static public void setMenuActive(){
        sidebarController.menuActive(null);
    }

    //user record
    static public void userRecordControllerSet(UserRecordController controller){
        ViewHandler.userRecordController = controller;
    }

    static public UserRecordController userRecordControllerGet(){
        return userRecordController;
    }


    //=========== SCENE, PANE, .. ============
    static public Stage currentStageGet() {
        return currentStage;
    }

    static public void currentStageSet(Stage stage) {
        currentStage = stage;
    }

    static public void popupClose(ActionEvent event) {
        Node node = (Node) event.getSource();
        Window window =  node.getScene().getWindow();
        window.hide();
    }

    static public void popupGet(AnchorPane pane, EventHandler<WindowEvent> popupOnClose){
        Stage stage = currentStageGet();

        //popup
        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.getContent().add(pane);
        popup.setOnHidden(popupOnClose);
        popup.show(stage);

        //to middle
        AnchorPane contentPane = (AnchorPane) popup.getContent().get(0);
        double x = stage.getX() + (stage.getScene().getWidth() - popup.getWidth()) / 2;
        double y = stage.getY() + (stage.getScene().getHeight() - popup.getHeight()) / 2;
        // System.out.println(window.getX() + " " + window.getY() + " " + window.getWidth() + " " + window.getHeight());
        // System.out.println(pane.getWidth() + " " + pane.getHeight());
        System.out.println(stage.getScene().getWidth());
        System.out.println(popup.getWidth());
        System.out.println(x + " " + y);
        popup.setX(x);
        popup.setY(y);
    }

    static public void nodeHide(Node node){
        node.setVisible(!node.isVisible());
        node.setDisable(!node.isDisable());
    }

    static public void sceneSet(Scene scene, String url){
        FXMLLoader loader = new FXMLLoader(ViewHandler.class.getResource(url));
        try {
            scene.setRoot(loader.load());
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    //=========== PANE ============
    static public void anchorPaneSet(AnchorPane frame, Node node){
        // if (true) {
        if (frame.getChildren().size() == 0) {
            frame.getChildren().add(node);
        } else {
            frame.getChildren().set(0, node);
        }
    }

    static public void anchorPaneSet(AnchorPane frame, String url){
        FXMLLoader fxmlLoader = new FXMLLoader(ViewHandler.class.getResource(url));
        try {
            AnchorPane node = (AnchorPane) fxmlLoader.load();
            anchorPaneSet(frame, node);
        } catch (Exception err){
            err.printStackTrace();
        }
    }

    static public AnchorPane anchorPaneGet(String url){
        FXMLLoader fxmlLoader = new FXMLLoader(ViewHandler.class.getResource(url));
        try {
            AnchorPane node = (AnchorPane) fxmlLoader.load();
            return node;
        } catch (Exception err){
            err.printStackTrace();
            return null;
        }
    }

    static public void scrollPaneLockScroll(ScrollPane scrollPane){
        scrollPane.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
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

    static public Image getImage(String url){
        try {
            Image img = new Image(ViewHandler.class.getResourceAsStream(PathHandler.getMediaImage(url)));
            return img;
        } catch (Exception err){
            err.printStackTrace();
            return null;
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
}
