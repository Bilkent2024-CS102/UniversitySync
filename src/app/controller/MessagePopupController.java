package app.controller;

import app.dao.MessageDao;
import app.model.User;
import app.model.userContent.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class MessagePopupController {

    @FXML
    private TextArea sendTextArea_ID;

    private User receiver;


    public void sendMessageButton(ActionEvent event) {
        String messageText = sendTextArea_ID.getText();
        Message message = new Message(SessionManager.getCurrentUser().getUserId(), receiver.getUserId(), messageText);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setData(User user) {
        receiver = user;
    }

    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


}
