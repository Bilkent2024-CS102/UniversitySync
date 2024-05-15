package app.controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.Event;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import app.dao.UserDao;
import app.model.User;

public class LoginController {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;


    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }


    public void signIn(javafx.event.ActionEvent e) throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (UserDao.authenticate(email, password)) // need method to validate email and password
        {
            User currentUser = UserDao.getUserByEmail(email);
            SessionManager.setCurrentUser(currentUser);
            System.out.println(SessionManager.getCurrentUser().getUserId());
            switchToFXML("src/app/view/Homepage.fxml", e);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Invalid Credentials");
            alert.setContentText("Please check your email and password.");
            alert.showAndWait();
        }
    }


    public void switchToRegistrationPage(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/RegisterationPage.fxml", event);
    }
}
