package app.controller;

import app.dao.UserDao;
import app.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class profileEditController {
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField oldPassTextField;
    @FXML
    private TextField newPassTextField;
    @FXML
    private TextField confirmPassTextField;
    @FXML
    private TextArea descriptionTextField;

    public void updateUser(ActionEvent event) {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String oldPass = oldPassTextField.getText();
        String newPass = newPassTextField.getText();
        String confirmPass = confirmPassTextField.getText();
        String description = descriptionTextField.getText();

        User current = SessionManager.getCurrentUser();
        if (current.getPassword().equals(oldPass))
        {
            current.setName(firstName + " " + lastName);
            current.setBiography(description);
            if(newPass.equals(confirmPass))
            {
                current.setPassword(newPass);
            }
            UserDao.updateUser(current);
        }
        else
        {
            // do not update
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong old Password");
        }
    }
}
