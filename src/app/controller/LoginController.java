package app.controller;

import java.awt.TextField;
import java.awt.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import app.dao.UserDao;
import app.model.User;

public class LoginController {

    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    private SessionManager sessionManager = new SessionManager();

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    UserDao userDao = new UserDao();

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }


    public void signIn(ActionEvent e) throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (userDao.authenticate(email, password)) // need method to validate email and password
        {
            int currentUser = UserDao.getUserByEmail(email);
            sessionManager.loginUser(currentUser);
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

    public void switchToSignUpPage(ActionEvent e) throws IOException {
        switchToFXML(null, e); // need to replace null with path to registration javafx file
    }

}
