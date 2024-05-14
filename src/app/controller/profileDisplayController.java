package app.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class profileDisplayController implements Initializable {
    @FXML
    private Label nameLabel;
    @FXML
    private Label usernamenameLabel;
    @FXML
    private Label majorLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label dormLabel;
    @FXML
    private Label descriptionLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabel.setText(SessionManager.getCurrentUser().getName());
//        usernamenameLabel.setText(SessionManager.getCurrentUser().getUsername());
        majorLabel.setText(SessionManager.getCurrentUser().getMajor());
        emailLabel.setText(SessionManager.getCurrentUser().getEmail());
        if (SessionManager.getCurrentUser().getRoom() != null)
        {
            dormLabel.setText(SessionManager.getCurrentUser().getRoom().getDescription());
        }
        else
        {
            dormLabel.setText("");
        }
        descriptionLabel.setText(SessionManager.getCurrentUser().getBiography());
    }
}
