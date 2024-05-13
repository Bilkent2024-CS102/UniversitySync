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
import javafx.scene.input.MouseEvent;

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

    private DormMock dormAssociatedWithThis;

    public void setData(DormMock dorm) {

        dormAssociatedWithThis = dorm;

        dormName_ID.setText( dorm.getDormName());
        //dormImage_ID.setImage( dorm.getDormImage().getImage());
        dormDescription_ID.setText( dorm.getDormDescription());
        dormRateTextField_ID.setText( dorm.getDormRating());
        dormCampus_ID.setText( dorm.getDormCampus());
    }

    public void switchToFXML(String fxmlFileName, MouseEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();

        ReviewPageController detailController = fxmlLoader.getController();
        // setting/sending the data of that particular cafe button to cafe details page
        detailController.setData( dormAssociatedWithThis);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void switchToReviews(MouseEvent event) throws IOException {
        //suppose user clicks dorm 76, it will take it to dorm 76 detail page
        switchToFXML("src/app/view/ReviewPage.fxml", event);
    }


}
