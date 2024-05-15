package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class MessagePopupController {

    @FXML
    private TextArea sendTextArea_ID;


    public void sendMessageButton(ActionEvent event) {
        String messageText = sendTextArea_ID.getText();
    }

    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


}
