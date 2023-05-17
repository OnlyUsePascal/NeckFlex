package com.groupproject;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set root1 to Screen1fxml
        //set root2 to Screen2fxml
        //set root3 to Screen3fxml

        Thread test1Thread = new Thread(() -> {
            //                FXMLLoader view1Loader = new FXMLLoader(getClass().getResource("Screen1.fxml"));
//                Scene loadingScene = new Scene(view1Loader.load());
//
//                root1.getChildren().clear();
//                System.out.println("Temp thread: " + Thread.currentThread());
//                root1.getChildren().add(loadingScene.getRoot());

            FXMLLoader view1Loader = new FXMLLoader(getClass().getResource("Screen1.fxml"));
            try {
                Scene loadingScene = new Scene(view1Loader.load());

                Platform.runLater(() -> {
//                    root1.getChildren().clear();
                    image1.setVisible(false);
                    System.out.println(123);
                    root1.getChildren().add(loadingScene.getRoot());
                    System.out.println(423);
                });

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

//
//            Thread taskThread = new Thread(() -> {
//                FXMLLoader view1Loader = new FXMLLoader(getClass().getResource("Screen1.fxml"));
//                try {
//                    Scene loadingScene = new Scene(view1Loader.load());
//
//                    Platform.runLater(() -> {
//                        root1.getChildren().clear();
//                        System.out.println(123);
//                        root1.getChildren().add(loadingScene.getRoot());
//                        System.out.println(423);
//                    });
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//
//            });

//            taskThread.start();

//            Platform.runLater(() -> {
//                System.out.println("Temp thread2: " + Thread.currentThread());
////                    image1.setVisible(false);
//            });



        });

        test1Thread.start();

        FXMLLoader view2Loader = new FXMLLoader(getClass().getResource("Screen2.fxml"));
        try {
            root2.getChildren().add(view2Loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        Thread test1Thread = new Thread(() ->{
//            try {
//                FXMLLoader view1Loader = new FXMLLoader(getClass().getResource("Screen1.fxml"));
//                Scene loadingScene = new Scene(view1Loader.load());
////                root1.getChildren()
//                root1.getChildren().clear();
//                System.out.println("Temp thread" + Thread.currentThread());
//                root1.getChildren().add(loadingScene.getRoot());
//
//                Platform.runLater(() ->{
//                    System.out.println(Thread.currentThread());
//                    image1.setVisible(false);
//                    //only change scene to root1, root2 and root3 keep unchanged
////                    root1.getChildren().add(loadingScene.getRoot());
//
//
//                });
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//        test1Thread.start();


    }
}
