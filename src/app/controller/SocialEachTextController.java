package app.controller;

import app.model.userContent.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SocialEachTextController {
    private Message thisMessage;

    @FXML
    private Label eachText_ID;

    public void setData(Message message) {
        thisMessage = message;
        eachText_ID.setText(message.getMainText());
    }
}
