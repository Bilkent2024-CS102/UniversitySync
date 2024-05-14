package app.controller;

import app.model.location.Dormitory;
import app.model.userContent.Review;
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

import java.text.DecimalFormat;
import java.util.List;

import java.awt.*;
import java.io.File;
import java.io.IOException;


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
    private List <Review> dormReviewList;

    // ************  Particular Dorm Associated with this page  ****************
    private Dormitory thisDorm;

    public void setData(Dormitory dorm) {

        thisDorm = dorm;          //connection bw different pages

        DormName_ID.setText( dorm.getName());
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String text = (dorm.getRating() < 0) ? ("No Reviews") : ("" + decimalFormat.format(dorm.getRating()));
        DormRating_ID.setText(text);
        dormCampus_ID.setText("" + dorm.getCampus().getName());

    }

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();

        DormitoryDetailController detailController = fxmlLoader.getController();
        // setting/sending the data of that particular cafe button to cafe details page
        detailController.setData(thisDorm);

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
//    public List<ReviewMock> getDormReviewList() {
//        return dormReviewList;
//    }
//
//    public void setDormReviewList(List<ReviewMock> dormReviewList) {
//        this.dormReviewList = dormReviewList;
//    }
}
//
//class DormMock {
//    private String dormName;
//    private String dormRating;
//    private String dormCampus;
//    private String dormDescription;
//    private ImageView dormImage;
//
//
//    public String getDormName() {
//        return dormName;
//    }
//
//    public String getDormCampus() {
//        return dormCampus;
//    }
//
//    public String getDormRating() {
//        return dormRating;
//    }
//
//    public ImageView getDormImage() {
//        return dormImage;
//    }
//
//    public String getDormDescription() {
//        return dormDescription;
//    }
//
//    public void setDormName(String dormName) {
//        this.dormName = dormName;
//    }
//
//    public void setDormRating(String dormRating) {
//        this.dormRating = dormRating;
//    }
//
//    public void setDormCampus(String dormCampus) {
//        this.dormCampus = dormCampus;
//    }
//
//    public void setDormDescription(String dormDescription) {
//        this.dormDescription = dormDescription;
//    }
//
//
//    public void setDormImage(ImageView dormImage) {
//        this.dormImage = dormImage;
//    }
//
//}