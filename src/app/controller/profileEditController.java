package app.controller;

import app.dao.UserDao;
import app.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

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

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public void updateUser(ActionEvent event) throws IOException {
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
            refresh(event);
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

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();

        if (event.getSource() instanceof Node) {                                    //FOR Buttons
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }
        else if (event.getSource() instanceof MenuItem menuItem)  {                 //FOR MenuButtons
            stage = (Stage) menuItem.getParentPopup().getOwnerWindow();
        }
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);     //it should be after stage.setScene
        stage.show();
    }

    public void refresh(ActionEvent event) throws IOException {
//        switchToFXML("src/app/view/ProfilePage/profile_LEFT_DISPLAY.fxml", event);
        switchToFXML("src/app/view/ProfilePage/profileMyPost.fxml", event);
    }
}
