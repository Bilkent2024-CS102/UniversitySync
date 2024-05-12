package app.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;

import java.io.IOException;

public class EventController {
    @FXML
    private TextArea eventTexArea_ID;
    @FXML
    private ToggleButton eventFollowButton_ID;

    public void followEvent(ActionEvent event) throws IOException {

        if (eventFollowButton_ID.getText().equalsIgnoreCase("Follow")) {
            eventFollowButton_ID.setText("Unfollow");
            // EventPostDao.addUser(SessionManager.getCurrentUser());
            eventTexArea_ID.setText( eventTexArea_ID.getText() + "\n You are now following.");
        }
        else {
            eventFollowButton_ID.setText("Follow");
            // EventPostDao.removeUser(SessionManager.getCurrentUser());
        }
    }
    public void setData(EventMock event) {
        // Image image = new Image(getClass().getResourceAsStream(post.getProfileImageSrc()));
        // userImageOnPostID.setImage(image);
        eventTexArea_ID.setText( event.getEventText());
    }
}
class EventMock {
    private String eventText;
    public String getEventText() {
        return eventText;
    }
    public void setEventText(String eventText) {
        this.eventText = eventText;
    }
}
