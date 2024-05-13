package app.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import app.dao.UserDao;
import app.model.User;

public class RegistrationController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField confirmPasswordField;

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

    private boolean isPasswordValid(String pass, String passRepeat)
    {
        return pass.equals(passRepeat) && pass.length() >= 8;
    }

    public void registerUser(ActionEvent e) throws IOException {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String repeatPassword = confirmPasswordField.getText();
        if (isPasswordValid(password, repeatPassword) && validateInputs(name, email, password, repeatPassword))
        {
            User newUser = new User(name, email, password);

            switchToFXML(null, e); // need to replace null with login page's fxml path
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Error");
            alert.setHeaderText("Invalid Credentials");
            alert.setContentText("Please check your the information you are entering.");
            alert.showAndWait();
        }
    }

    private boolean validateInputs(String name, String email, String password, String confirmPassword) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return false;
        }

        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
            return false;
        }

        if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            return false;
        }

        if (!password.equals(confirmPassword)) {
            return false;
        }

        return !emailExists(email);
    }

    private boolean emailExists(String email)
    {
        User u = UserDao.getUserByEmail(email);
        return u != null;
    }

    public void registerButton(ActionEvent event) {
    }

    public void takeToLoginPage(ActionEvent event) {
        //use fxml loader to take back to login page
    }

}
