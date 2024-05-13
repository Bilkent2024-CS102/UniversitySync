package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CafeteriaDetailController implements Initializable {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    @FXML
    private Label cafeName_ID;
    @FXML
    private ImageView cafeImage_ID;
    @FXML
    private TextArea cafeDescription_ID;
    @FXML
    private TextArea cafeMenuTextArea_ID;
    @FXML
    private TextField cafeRateTextField_ID;
    @FXML
    private TextField cafePriceRange_ID;

    public void initialize(URL location, ResourceBundle resources) {

    }



    public void setData(CafeMock event) {

        cafeName_ID.setText( event.getCafeName());
        //cafeImage_ID.setImage(event.getCafeImage().getImage());
        cafeDescription_ID.setText(event.getCafeDescription());
        cafeMenuTextArea_ID.setText( event.getCafeMenu());
        cafeRateTextField_ID.setText( event.getCafeRating());
        cafePriceRange_ID.setText( event.getCafePriceRange());
    }

    public void switchToReviews( ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/app/view/Reviews.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        //FOR Buttons
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}
