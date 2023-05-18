package com.groupproject.ScrollPaneTest;

import com.groupproject.Item.ItemView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScrollPaneTestController implements Initializable {
    @FXML
    private AnchorPane root1;
    @FXML
    private AnchorPane root2;
    @FXML
    private AnchorPane root3;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set root1 to Screen1fxml
        //set root2 to Screen2fxml
        //set root3 to Screen3fxml

        Thread thread1 = new Thread(() -> {

            FXMLLoader view1Loader = new FXMLLoader(getClass().getResource("/com/groupproject/Screen1.fxml"));
            try {
//                Scene loadingScene = new Scene(view1Loader.load());

                AnchorPane anchorPane1 = view1Loader.load();

                Platform.runLater(() -> {
//                    root1.getChildren().clear();
                    image1.setVisible(false);
                    root1.getChildren().add(anchorPane1);
                });

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();

        Thread thread2 = new Thread(() -> {
            FXMLLoader view2Loader = new FXMLLoader(getClass().getResource("/com/groupproject/Screen2.fxml"));
            try {
                AnchorPane anchorPane2 = view2Loader.load();
                Platform.runLater(() -> {
                    image2.setVisible(false);
                    root2.getChildren().add(anchorPane2);
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        thread2.start();

        Thread thread3 = new Thread(() -> {
            FXMLLoader view3Loader = new FXMLLoader(getClass().getResource("/com/groupproject/Screen3.fxml"));
            try {
                AnchorPane anchorPane3 = view3Loader.load();
                Platform.runLater(() -> {
                    image3.setVisible(false);
                    root3.getChildren().add(anchorPane3);
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        thread3.start();

    }
}
