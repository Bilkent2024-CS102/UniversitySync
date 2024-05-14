package app.controller;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;


public class SocialEventController {

    @FXML
    private TextArea eventTextArea_ID;

    @FXML
    private Label eventTitle_ID;



    @FXML
    public void unfollowAndRemoveEvent(ActionEvent event) {
        //that event should be removed and unfollowed from the events page
    }

    public void setData(EventMock eventMock) {
        eventTitle_ID.setText( eventMock.getEventTitle());
        eventTextArea_ID.setText( eventMock.getEventText());
    }
}
