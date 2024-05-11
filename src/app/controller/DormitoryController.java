package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DormitoryController {
    // private Label dormNameLabel;
    // private Label dormRatingLabel;
    // private Label dormCampusLabel;
    // private Label dormRoomTypeLabel; // Need to decide if we are implementing this

    // public void setData(Dormitory dorm)
    // {
    //     String dormNameLabelText = dorm.getName();
    //     dormNameLabel.setText(dormNameLabelText);
    //     String dormRatingLabelText = "Rated " + dorm.getRating() + "/5";
    //     dormRatingLabel.setText(dormRatingLabelText);
    //     dormCampusLabel.setText(dorm.getCampus());
    //     // Need to implement dormRoomTypeLabel if we decide to include it
    // }
    
    
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();
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
    //*********************************( FOR DORM FILTER *************************************

    public void displayFilterPopup(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/app/view/Dormitory/dormFilterPopup.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Stage filterStage = new Stage();
        filterStage.initModality(Modality.APPLICATION_MODAL);
        filterStage.initStyle(StageStyle.UTILITY);
        filterStage.setScene(new Scene(root));
        filterStage.showAndWait();
    }
    public void addFilter(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void cancelFilter(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

//    @FXML
//    private ChoiceBox dormChoiceBoxID = new ChoiceBox();
//
//    public void addDormNames() {
//        dormChoiceBoxID.getItems().addAll("61", "62", "63", "70", "71", "74");
//    }

}
