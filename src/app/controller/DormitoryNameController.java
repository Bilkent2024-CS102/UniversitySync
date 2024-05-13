package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class DormitoryNameController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    @FXML
    private Button DormName_ID;
    @FXML
    private Label DormRating_ID;
    @FXML
    private Label dormCampus_ID;

    private DormMock dormAssociatedWithThis;

    public void setData(DormMock dorm) {

        dormAssociatedWithThis = dorm;          //connection bw different pages

        DormName_ID.setText( dorm.getDormName());
        DormRating_ID.setText( dorm.getDormRating());
        dormCampus_ID.setText( dorm.getDormCampus());

    }

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();

        DormitoryDetailController detailController = fxmlLoader.getController();
        // setting/sending the data of that particular cafe button to cafe details page
        detailController.setData(dormAssociatedWithThis);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void switchToDormDetails(ActionEvent event) throws IOException {
        //suppose user clicks dorm 76, it will take it to dorm 76 detail page
        switchToFXML("src/app/view/Dormitory/dormitoryDetails.fxml", event);
    }
}

class DormMock {
    private String dormName;
    private String dormRating;
    private String dormCampus;
    private String dormDescription;
    private ImageView dormImage;
    private List <ReviewMock> dormReviewList;


    public String getDormName() {
        return dormName;
    }

    public String getDormCampus() {
        return dormCampus;
    }

    public String getDormRating() {
        return dormRating;
    }

    public ImageView getDormImage() {
        return dormImage;
    }

    public String getDormDescription() {
        return dormDescription;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }

    public void setDormRating(String dormRating) {
        this.dormRating = dormRating;
    }

    public void setDormCampus(String dormCampus) {
        this.dormCampus = dormCampus;
    }

    public void setDormDescription(String dormDescription) {
        this.dormDescription = dormDescription;
    }


    public void setDormImage(ImageView dormImage) {
        this.dormImage = dormImage;
    }

    public List<ReviewMock> getDormReviewList() {
        return dormReviewList;
    }

    public void setDormReviewList(List<ReviewMock> dormReviewList) {
        this.dormReviewList = dormReviewList;
    }
}