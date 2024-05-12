package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
//import com.mysql.cj.Session;
import javafx.stage.StageStyle;

public class RightEventTabController implements Initializable {
    private Stage stage;
    private FXMLLoader fxmlLoader;

//*************************************************************************************
    @FXML
    private ToggleButton eventFollowButton_ID;
    @FXML
    private VBox Event_VBox_ID = new VBox();    //right Event VBox where we put our events
    private List<EventMock> eventMock;

    public void initialize(URL location, ResourceBundle resources) {
        eventMock = new ArrayList<>(data());

        try {
            for( int i = 0; i < eventMock.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/Event_Tab/Events.fxml").toURI().toURL());
                VBox vbox = fxmlLoader.load();              //the pane that contains posts in the post fxml
                EventController eventController = fxmlLoader.getController();
                //now setting data (username, text ...) for each post
                eventController.setData(eventMock.get(i));
//              eventController.setRightEventTabController(this);
                Event_VBox_ID.getChildren().add(vbox);  //now adding post (pane) to the vbox
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<EventMock> data() {
        List<EventMock> ls = new ArrayList<>();
        EventMock event1 = new EventMock();
        event1.setEventText("There is going to be an event today");
        ls.add(event1);

        EventMock event2 = new EventMock();
        event2.setEventText("hello i am atilla and i am organizing the stuff in the");
        ls.add(event2);
        return ls;
        // return ForumPostDao.getPostsByRecency();
    }


    public void displayAddEventPopup (ActionEvent event) throws IOException {
//        switchToFXML("Add_New_Event.fxml", event);

        // Load the FXML file for the filter screen
        fxmlLoader = new FXMLLoader(new File("src/app/view/Event_Tab/Add_New_Event.fxml").toURI().toURL());
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
