package org.example.homepage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.stage.StageStyle;


public class Right_Event_TAB_Controller {
    private Stage stage;
    private FXMLLoader fxmlLoader;

    //accessing follow button from event tab using its id "followEventButtonID"
    @FXML
    private ToggleButton followEventButtonID;    //not button but toggle button


    public void followEvent(ActionEvent event) throws IOException {

        if (followEventButtonID.getText().equalsIgnoreCase("Follow")) {
            followEventButtonID.setText("Unfollow");
        }
        else {
            followEventButtonID.setText("Follow");
        }
    }
    public void displayAddEventPopup (ActionEvent event) throws IOException {
//        switchToFXML("Add_New_Event.fxml", event);

        // Load the FXML file for the filter screen
        fxmlLoader = new FXMLLoader(getClass().getResource("Event_Tab/Add_New_Event.fxml"));
        Parent root = fxmlLoader.load();

        // Create a new stage for the filter screen
        Stage filterStage = new Stage();
        filterStage.initModality(Modality.APPLICATION_MODAL); // Make it modal
        filterStage.initStyle(StageStyle.UTILITY);
        filterStage.setScene(new Scene(root));

        // Show the filter screen
        filterStage.showAndWait(); // This will block interaction with the main window
    }

    //after user adds Name, Location, and other things, this method adds
    //it to the event pool that user can see and follow
    public void addEvent(ActionEvent event) throws IOException {
        /*
        The code checks which window (stage) the button belongs to and then closes that window.
        This is a common pattern in JavaFX for closing the window (stage) associated with a
        button or any other UI element.
        */
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        // Close the filter screen
        stage.close();
    }

    public void cancelButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

}