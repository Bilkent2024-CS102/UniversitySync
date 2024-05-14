package app.controller;

import javafx.event.ActionEvent;

import java.awt.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import static app.dao.UserDao.addRequestToAdmin;

public class ProfileContactUsController {

    @FXML
    private TextArea contactUsTextInput;

    public void submitContactUs(ActionEvent e)
    {
        addRequestToAdmin(SessionManager.getCurrentUser().getUserId(), contactUsTextInput.getText());
        contactUsTextInput.clear();
    }
}
