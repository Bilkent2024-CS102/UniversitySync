package app.controller;

import app.dao.DormitoryDao;
import app.model.location.Dormitory;
import app.model.location.Room;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.dao.UserDao;
import app.model.User;

public class RegistrationController implements Initializable {

    @FXML
    private TextField fullnameID;
    @FXML
    private TextField universityEmailID;
    @FXML
    private ChoiceBox<String> majorChoiceBoxID;
    @FXML
    private ChoiceBox<Room> dormChoiceBoxID;
    @FXML
    private TextField passwordID;
    @FXML
    private TextField confirmPasswordID;

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    UserDao userDao = new UserDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> majors = UserDao.getMajors();
        majorChoiceBoxID.getItems().addAll(majors);

        ArrayList<Room> roomTypes = DormitoryDao.getAllRoomTypes();
        dormChoiceBoxID.getItems().addAll(roomTypes);
    }

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
    }

    private boolean isPasswordValid(String pass, String passRepeat)
    {
        return pass.equals(passRepeat) && pass.length() >= 8;
    }

    public static boolean isValidEmailFormat(String email) {
        // Regular expression pattern for email matching
        String emailRegex = "^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.bilkent\\.edu\\.tr$";

        // Compile the pattern
        Pattern pattern = Pattern.compile(emailRegex);

        // Create a matcher object
        Matcher matcher = pattern.matcher(email);

        // Check if the email matches the pattern
        return matcher.matches();
    }

    public void registerUser(ActionEvent e) throws IOException {
        String name = fullnameID.getText();
        String email = universityEmailID.getText();
        String major = majorChoiceBoxID.getSelectionModel().getSelectedItem();
        int roomType;
        if (dormChoiceBoxID.getSelectionModel().getSelectedItem() == null){
            roomType = 0;
        }
        else{
            roomType = dormChoiceBoxID.getSelectionModel().getSelectedItem().getRoomId();
        }
        String password = passwordID.getText();
        String repeatPassword = confirmPasswordID.getText();

        if (!isValidEmailFormat(email))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Error");
            alert.setHeaderText("Non-university email");
            alert.setContentText("Please make sure your email is university email.");
            alert.showAndWait();
        }
        else if (isPasswordValid(password, repeatPassword) && validateInputs(name, email, password, repeatPassword))
        {
            User newUser = new User(name, email, password, major, "", roomType); //initially 0 as roomId indicating no room

            switchToFXML("src/app/view/LoginPage.fxml", e); // need to replace null with login page's fxml path
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
        /*if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            return false;
        }*/
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

    public void takeToLoginPage(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/LoginPage.fxml", event);
    }


}
