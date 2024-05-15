package app.controller;

import app.dao.DormitoryDao;
import app.dao.UserDao;
import app.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class otherUserProfileDisplayController {
    private User profileUser;
    @FXML
    private Label nameLabel;
    @FXML
    private Label majorLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label dormLabel;
//    @FXML
//    private Label descriptionLabel;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Button messageButton;
    @FXML
    private Button unfriendButton;

    private FXMLLoader fxmlLoader;

    public void setData(User searchedUser) {
//        System.out.println(SessionManager.getCurrentUser().getUserId());
        profileUser = searchedUser;
        nameLabel.setText(searchedUser.getName());
//        usernamenameLabel.setText(SessionManager.getCurrentUser().getUsername());
        majorLabel.setText(searchedUser.getMajor());
        emailLabel.setText(searchedUser.getEmail());
        if (searchedUser.getRoomId() != 0)
        {
            dormLabel.setText(DormitoryDao.getRoomTypeById(searchedUser.getRoomId()).getDescription());
        }
        else
        {
            dormLabel.setText("");
        }
        descriptionArea.setText(searchedUser.getBiography());
        descriptionArea.setEditable(false);
    }

    public void messagePopUp(ActionEvent event) throws IOException {
        switchToFXML2("src/app/view/MessagePopup.fxml", event);
    }

    private void switchToFXML2(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();
        MessagePopupController newController = fxmlLoader.getController();
        newController.setData(profileUser);
        // Create a new stage for the filter screen
        Stage filterStage = new Stage();
        filterStage.initModality(Modality.APPLICATION_MODAL); // Make it modal
        filterStage.initStyle(StageStyle.UTILITY);
        filterStage.setScene(new Scene(root));
        // Show the filter screen
        filterStage.showAndWait();
    }

    public void messageUser(ActionEvent event) throws IOException {
        //showMessageScreen(thisPost.getOwnerId());
        switchToFXML2("src/app/view/MessagePopup.fxml", event);
    }
}