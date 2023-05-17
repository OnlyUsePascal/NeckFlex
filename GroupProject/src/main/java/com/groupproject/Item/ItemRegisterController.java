package com.groupproject.Item;

import com.groupproject.types.DVD;
import com.groupproject.types.MovieRecord;
import com.groupproject.types.SystemShop;
import com.groupproject.types.VideoGame;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemRegisterController implements Initializable {

    @FXML
    private ImageView ItemRegisterImage;
    @FXML
    private TextField ItemRegisterTitle;
    @FXML
    private ChoiceBox ItemRegisterGenre;
    @FXML
    private ChoiceBox ItemRegisterCategory;
    @FXML
    private TextField ItemRegisterStock;
    @FXML
    private TextField ItemRegisterFee;
    @FXML
    private TextField ItemRegisterYear;
    @FXML
    private Label errorMessage;

    public void loadImage(){
        FileChooser fileChooser = new FileChooser();
        String [] extensions = new String[] {"*.jpg", "*.png"};

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter extFilterAll = new FileChooser.ExtensionFilter("All files (*.jpg), (*.png)" , extensions);
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG,extFilterAll);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            System.out.println(file.toURI().toString());

            ItemRegisterImage.setImage(image);
        }
    }

    public void DecreaseStock(){
        try {
            int stock = Integer.parseInt(ItemRegisterStock.getText());
            if (stock > 0) {
                ItemRegisterStock.setText(String.valueOf(stock - 1));
            }
        } catch (NumberFormatException e){
            return;
        }
    }

    public void IncreaseStock(){
        try {
            int stock = Integer.parseInt(ItemRegisterStock.getText());
            ItemRegisterStock.setText(String.valueOf(stock + 1));
        } catch (NumberFormatException e){
            return;
        }
    }

    public void cancel(){
        ItemRegister.window.close();
    }

    public boolean checkTitle(){
        if (ItemRegisterTitle.getText().isEmpty()){
            errorMessage.setText("Title cannot be empty");
            errorMessage.setTextFill(javafx.scene.paint.Color.RED);
            return false;
        }
        return true;
    }

    public boolean checkGenre(){
        if (ItemRegisterGenre.getValue() == null){
            errorMessage.setText("Genre cannot be empty");
            errorMessage.setTextFill(javafx.scene.paint.Color.RED);
            return false;
        }
        return true;
    }

    public boolean checkCategory(){
        if (ItemRegisterCategory.getValue() == null){
            errorMessage.setText("Category cannot be empty");
            errorMessage.setTextFill(javafx.scene.paint.Color.RED);
            return false;
        }
        return true;
    }

    public boolean checkStock(){
        try {
            Integer.parseInt(ItemRegisterStock.getText());
        } catch (NumberFormatException e){
            errorMessage.setText("Stock must be a number");
            errorMessage.setTextFill(javafx.scene.paint.Color.RED);
            return false;
        }
        return true;
    }

    public boolean checkFee(){
        try {
            Double.parseDouble(ItemRegisterFee.getText());
        } catch (NumberFormatException e){
            errorMessage.setText("Invalid fee");
            errorMessage.setTextFill(javafx.scene.paint.Color.RED);
            return false;
        }
        return true;
    }

    public boolean checkImage(){
        if (ItemRegisterImage.getImage() == null){
            errorMessage.setText("Please load your image");
            errorMessage.setTextFill(javafx.scene.paint.Color.RED);
            return false;
        }
        return true;
    }

    public boolean checkYear(){
        try {
            Integer.parseInt(ItemRegisterYear.getText());
        } catch (NumberFormatException e){
            errorMessage.setText("Invalid year");
            errorMessage.setTextFill(javafx.scene.paint.Color.RED);
            return false;
        }
        return true;
    }

    public boolean checkAll(){
        if (checkTitle() == false){
            return false;
        }
        if (checkGenre() == false){
            return false;
        }
        if (checkCategory() == false){
            return false;
        }
        if (checkStock() == false){
            return false;
        }
        if (checkFee() == false){
            return false;
        }
        if (checkImage() == false){
            return false;
        }
        if (checkYear() == false){
            return false;
        }
        return true;
    }





    public void create(){

        if (checkAll() == true){

            String title = ItemRegisterTitle.getText();
            String genre = ItemRegisterGenre.getValue().toString();
            int stock = Integer.parseInt(ItemRegisterStock.getText());
            double fee = Double.parseDouble(ItemRegisterFee.getText());
            int year = Integer.parseInt(ItemRegisterYear.getText());
//            title,
//            String category,
//            String rentalType,
//            String loanType,
//            int numberOfCopies,
//            double rentalFee,
//            String rentalStatus,
//            int year

            switch (ItemRegisterCategory.getValue().toString()){
                case "MovieRecord":
                    MovieRecord movieRecord = MovieRecord.addMovieRecord(ItemRegisterTitle.getText(),
                            "0",
                            "0",
                            "0",
                            stock,
                            fee,
                            "0",
                            year
                    );
                    SystemShop.addItem(movieRecord);
                    break;

                case "DVD":
                    DVD dvd = DVD.addDVD(ItemRegisterTitle.getText(),
                            "1",
                            "0",
                            "0",
                            stock,
                            fee,
                            "0",
                            year
                    );
                    SystemShop.addItem(dvd);
                    break;

                case "VideoGame":
                    VideoGame videoGame = VideoGame.addVideoGame(ItemRegisterTitle.getText(),
                            "2",
                            "0",
                            "0",
                            stock,
                            fee,
                            "0",
                            year
                    );
                    SystemShop.addItem(videoGame);
                    break;

            }

            errorMessage.setText("Item created successfully");
            errorMessage.setTextFill(javafx.scene.paint.Color.GREEN);

            PauseTransition pause = new PauseTransition(Duration.millis(3000));
            pause.setOnFinished(event -> closeWindow());
            pause.play();
        }
    }

    public void closeWindow(){
        ItemRegister.window.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ItemRegisterGenre.getItems().addAll("Action", "Adventure", "Comedy", "Crime", "Drama");
        ItemRegisterCategory.getItems().addAll("MovieRecord", "DVD", "VideoGame");
    }
}
