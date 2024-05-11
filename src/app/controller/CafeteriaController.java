package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;


public class CafeteriaController{
    // private Label cafeNameLabel;
    // private Label ratingLabel;
    // private Label priceRangeLabel;

    // public void setData(Cafeteria cafe)
    // {
    //     cafeNameLabel.setText(cafe.getName());
    //     cafeNameLabel.onMouseClickedProperty(openCafe(cafe));
    //     String ratingLabelText = "Rated " + cafe.getRating() + "/5";
    //     ratingLabel.setText(ratingLabelText);
    //     String priceRangeLabelText = "Price range: TL" + cafe.getMinPrice + "-TL" + cafe.getMaxPrice;
    //     priceRangeLabel.setText(priceRangeLabelText);
    // }

    // private void openCafe(Cafe cafe)
    // {
    //     // UI method to open page for this particular cafe
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
    public void switchToCafe1(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/CafeteriaPage/CafeDetail.fxml", event);
    }
    ////// For filter button
    public void displayAddFilterPopup (ActionEvent event) throws IOException {
//        switchToFXML("Add_New_Event.fxml", event);

        // Load the FXML file for the filter screen
        fxmlLoader = new FXMLLoader(new File("src/app/view/CafeteriaPage/AddFilter_popup.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();

        // Create a new stage for the filter screen
        Stage filterStage = new Stage();
        filterStage.initModality(Modality.APPLICATION_MODAL); // Make it modal
        filterStage.initStyle(StageStyle.UTILITY);
        filterStage.setScene(new Scene(root));

        // Show the filter screen
        filterStage.showAndWait(); // This will block interaction with the main window
    }

    //after user puts filter, use this code: (right now both addFilter and close are same)
    public void applyFilter(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        // Close the filter screen
        stage.close();
    }

    public void cancelFilter(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
