package app.controller;

import app.dao.DormitoryDao;
import app.dao.UserDao;
import app.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class ProfileDisplayController implements Initializable {
    @FXML
    private Label nameLabel;
    @FXML
    private Label majorLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label dormLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private ImageView imageUrl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameLabel.setText(SessionManager.getCurrentUser().getName());
        majorLabel.setText(SessionManager.getCurrentUser().getMajor());
        emailLabel.setText(SessionManager.getCurrentUser().getEmail());

        if (SessionManager.getCurrentUser().getRoomId() != 0)
        {
            dormLabel.setText(DormitoryDao.getRoomTypeById(SessionManager.getCurrentUser().getRoomId()).getDescription());
        }
        else {dormLabel.setText("");}
        descriptionLabel.setText(SessionManager.getCurrentUser().getBiography());

        File file = new File("src/app/images/profilePictures/profilePicture" + SessionManager.getCurrentUser().getUserId() + ".png");
        Image image = new Image(file.toURI().toString());
        imageUrl.setImage(image);
    }

    public void setProfileImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.tif")
        );
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            User u = SessionManager.getCurrentUser();
            String newPath = "src/app/images/profilePictures/profilePicture" + u.getUserId() + ".png";
            Files.copy(Paths.get(file.getAbsolutePath()), Paths.get(newPath), StandardCopyOption.REPLACE_EXISTING);
            u.setProfilePicturePath(newPath);
            UserDao.updateUser(u);
        }

        Image newImage = new Image(file.toURI().toString());
        imageUrl.setImage(newImage);
    }
}