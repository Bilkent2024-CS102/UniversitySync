package app.controller;

import app.dao.UserDao;
import app.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class profileEditController {
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private PasswordField oldPassTextField;
    @FXML
    private PasswordField newPassTextField;
    @FXML
    private PasswordField confirmPassTextField;
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
        
        boolean valid = true;
        if (current.getPassword().equals(oldPass))
        {
            current.setName(firstName + " " + lastName);
            current.setBiography(description);
            if (!newPass.isEmpty() || !confirmPass.isEmpty()){
                if(newPass.equals(confirmPass))
                {
                    current.setPassword(newPass);
                }
                else{
                    valid = false;
                    // do not update
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("New passwords dont match");
                    alert.showAndWait();
                }
            }
        }
        else
        {
            valid = false;
            // do not update
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong current Password");
            alert.showAndWait();
        }

        if(valid){
            UserDao.updateUser(current);
            firstNameTextField.clear();
            lastNameTextField.clear();
            oldPassTextField.clear();
            newPassTextField.clear();
            confirmPassTextField.clear();
            descriptionTextField.clear();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Profile updated");
            alert.showAndWait();
        }
    }
}
