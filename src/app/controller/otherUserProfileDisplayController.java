package app.controller;

import app.dao.DormitoryDao;
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
import javafx.stage.Stage;

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

    public void setData(User searchedUser) {
//        System.out.println(SessionManager.getCurrentUser().getUserId());
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
}