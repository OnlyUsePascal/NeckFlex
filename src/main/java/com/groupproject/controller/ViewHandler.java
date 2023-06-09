package com.groupproject.controller;

import com.groupproject.controller.component.ItemBoxController;
import com.groupproject.controller.component.NavBarController;
import com.groupproject.controller.component.NavBarCustomerController;
import com.groupproject.controller.component.SidebarController;
import com.groupproject.controller.page.AdminItemController;
import com.groupproject.controller.page.HomeController;
import com.groupproject.controller.page.UserRecordController;
import com.groupproject.controller.popup.NotiController;
import com.groupproject.entity.generic.Item;
import com.groupproject.toolkit.PathHandler;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

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
    static private AdminItemController adminItemController;


    //=========== CONTROLLER ============
    // home
    static public void setHomeController(HomeController homeController) {
        ViewHandler.homeController = homeController;
    }

    static public void setPageContent(String url) {
        homeController.setPageContent(url);
    }

    // nav bar
    static public void setNavBarController(NavBarController navBarController) {
        ViewHandler.navBarController = navBarController;
    }

    static public void refreshMenuButtonName() {
        navBarController.refreshMenuButtonName();
    }

    static public String getSearchText() {
        return ((NavBarCustomerController) navBarController).getSearchText();
    }

    static public void clearSearchText() {
        ((NavBarCustomerController) navBarController).clearSearchText();
    }

    // sidebar
    static public void setSidebarController(SidebarController sidebarController) {
        ViewHandler.sidebarController = sidebarController;
    }

    static public void setMenuActive() {
        homeController.setSideBarActive();
    }

    static public boolean sideBarIsOpen() {
        return homeController.isSideBarOpen();
    }

    // user record
    static public void setUserRecordController(UserRecordController controller) {
        ViewHandler.userRecordController = controller;
    }

    static public UserRecordController getUserRecordController() {
        return userRecordController;
    }

    // admin item
    static public void setAdminItemController(AdminItemController controller) {
        ViewHandler.adminItemController = controller;
    }

    static public void refreshTable(){
        adminItemController.refreshTable();
    }

    //=========== SCENE, PANE, .. ============
    static public Stage getCurrentStage() {
        return currentStage;
    }

    static public void setCurrentStage(Stage stage) {
        currentStage = stage;
    }

    static public Window getWindow(ActionEvent event) {
        Node node = (Node) event.getSource();
        Window window = node.getScene().getWindow();
        return window;
    }

    static public void closePopup(ActionEvent event) {
        Node node = (Node) event.getSource();
        Window window = node.getScene().getWindow();
        window.hide();
    }

    static public void reOpenPopup(ActionEvent event) {
        Popup popup = (Popup) getWindow(event);
        popup.show(getCurrentStage());
    }

    static public Popup getPopup(AnchorPane pane, EventHandler<WindowEvent> popupOnClose) {
        Stage stage = getCurrentStage();

        // popup
        Popup popup = new Popup();
        popup.setAutoHide(true);
        popup.getContent().add(pane);
        popup.setOnHidden(popupOnClose);
        popup.show(stage);

        // to middle
        AnchorPane contentPane = (AnchorPane) popup.getContent().get(0);
        double x = stage.getX() + (stage.getWidth() - popup.getWidth()) / 2;
        double y = stage.getY() + (stage.getHeight() - popup.getHeight()) / 2;
        popup.setX(x);
        popup.setY(y);

        return popup;
    }

    static public void getNoti(String text, AnchorPane rootPane) {
        // default
        AnchorPane curRoot = (rootPane != null) ?
                rootPane : homeController.getPageContent();

        try {
            FXMLLoader loader = new FXMLLoader(ViewHandler.class.getResource(PathHandler.getPopupNoti()));
            AnchorPane notiPane = loader.load();
            NotiController notiController = loader.getController();

            notiController.setText(text);

            // position
            double offset = 10;
            notiPane.setLayoutX(curRoot.getPrefWidth() - notiPane.getPrefWidth() - offset);
            notiPane.setLayoutY(offset);

            // show -> hold -> fade
            curRoot.getChildren().add(notiPane);
            new Thread(() -> {
                Platform.runLater(() -> {
                    toggleFadeNode(notiPane, true);
                });

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }

                Platform.runLater(() -> {
                    toggleFadeNode(notiPane, false);
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }

                Platform.runLater(() -> {
                    curRoot.getChildren().remove(notiPane);
                });
            }).start();

        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    //=========== NODE ============
    static public void fakeLoading() {
        for (long i = 0; i < 5e8; i++) ;
    }

    static public void toggleNode(Node node, boolean isShow) {
        node.setVisible(isShow);
        // node.setManaged(isShow);
    }

    static public void toggleFadeNode(Node node, boolean show) {
        FadeTransition fadeTransition = new FadeTransition();

        fadeTransition.setNode(node);
        fadeTransition.setDuration(Duration.millis(1000));

        fadeTransition.setFromValue(show ? 0 : 1);
        fadeTransition.setToValue(show ? 1 : 0);

        fadeTransition.play();
    }

    static public void setAnchorPane(AnchorPane frame, Node node) {
        // if (true) {
        if (frame.getChildren().size() == 0) {
            frame.getChildren().add(node);
        } else {
            frame.getChildren().set(0, node);
        }
    }

    static public void setAnchorPane(AnchorPane frame, String url) {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewHandler.class.getResource(url));
        try {
            AnchorPane node = (AnchorPane) fxmlLoader.load();
            setAnchorPane(frame, node);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    static public Button getItemBox(Item item) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ViewHandler.class.getResource(PathHandler.getComponentItemBox()));
            Button itemBox = (Button) fxmlLoader.load();
            ItemBoxController itemBoxController = fxmlLoader.getController();

            itemBoxController.setData(item);

            return itemBox;
        } catch (IOException err) {
            err.printStackTrace();
            return null;
        }
    }

    static public AnchorPane getAnchorPane(String url) {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewHandler.class.getResource(url));
        try {
            AnchorPane node = (AnchorPane) fxmlLoader.load();
            return node;
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
    }

    static public void lockHorizonScroll(Node pane) {
        pane.addEventFilter(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaX() != 0) {
                    event.consume();
                }
            }
        });
    }

    //=========== FILE ============
    static public File getFile(String url) {
        System.out.println(url);
        File file = new File(url);
        if (file.exists()) {
            System.out.println("File found");
            return file;
        }
        System.out.println("File not found:");
        return null;
    }

    static public Image getImage(String imgName) {
        try {
            String url = PathHandler.getMediaImage(imgName);
            Image img = new Image(ViewHandler.class.getResourceAsStream(PathHandler.getMediaImage(imgName)));
            return img;
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
    }

    static public void copyImageToResource(URI filePath, String newName) {
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

    static public void fillShapeWithImage(String imgName, Shape frame) {
        String url = PathHandler.getFileImageItem(imgName);
        Path path = Paths.get(url);

        Image img = new Image(path.toUri().toString());
        ImagePattern imgView = new ImagePattern(img);
        frame.setFill(imgView);
    }

    //=========== STRING, NUMBER ===============
    static public boolean checkStringSafe(String str){
        return !str.contains("|") && !str.contains("~")
                && !str.contains("@") && !str.contains("#")
                && !str.contains("$");
    }

    static public boolean checkStringNumber(String str, boolean isDouble){
        //year, price, stock
        if (isDouble) {
            try {
                Double.parseDouble(str);
                return true;
            } catch (NumberFormatException err) {
                return false;
            }
        }

        return str.matches("[0-9]+");
    }

    static public boolean checkStringAuth(String str){
        //username, password
        return str.matches("[a-zA-Z0-9]+");
    }

    static public boolean checkStringNormal(String str){
        //title, address, desc,
        return str.matches("[a-zA-Z0-9,.\\-: ]+");
    }

    static public boolean checkStringName(String str){
        //first name, last name,
        return str.matches("[a-zA-Z0-9 ]+");
    }

    static public double getDoubleRound(double num) {
        return Math.round(num * 100.0) / 100.0;
    }

    static public boolean checkStringSimilar(String frame, String target) {
        return frame.toLowerCase().contains(target.toLowerCase());
    }


}
