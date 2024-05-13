package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class DormitoryDetailController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    @FXML
    private Label dormName_ID;
    @FXML
    private ImageView dormImage_ID;
    @FXML
    private TextArea dormDescription_ID;
    @FXML
    private TextField dormRateTextField_ID;
    @FXML
    private Label dormCampus_ID;


    public void setData(DormMock dorm) {

        dormName_ID.setText( dorm.getDormName());
        //dormImage_ID.setImage( dorm.getDormImage().getImage());
        dormDescription_ID.setText( dorm.getDormDescription());
        dormRateTextField_ID.setText( dorm.getDormRating());
        dormCampus_ID.setText( dorm.getDormCampus());
    }

    public void switchToReviews( ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/app/view/ReviewPage.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        //FOR Buttons
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

}
