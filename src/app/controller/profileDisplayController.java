package app.controller;

import app.dao.DormitoryDao;
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
        System.out.println(SessionManager.getCurrentUser().getUserId());
        nameLabel.setText(SessionManager.getCurrentUser().getName());
//        usernamenameLabel.setText(SessionManager.getCurrentUser().getUsername());
        majorLabel.setText(SessionManager.getCurrentUser().getMajor());
        emailLabel.setText(SessionManager.getCurrentUser().getEmail());
        if (SessionManager.getCurrentUser().getRoomId() != 0)
        {
            dormLabel.setText(DormitoryDao.getRoomTypeById(SessionManager.getCurrentUser().getRoomId()).getDescription());
        }
        else
        {
            dormLabel.setText("");
        }
        descriptionLabel.setText(SessionManager.getCurrentUser().getBiography());
    }
}
