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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class profileDisplayController implements Initializable {
    private User profileUser;
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
    private TextArea descriptionArea;
    @FXML
    private Button messageButton;
    @FXML
    private Button unfriendButton;
    @FXML
    private ImageView imageUrl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println(SessionManager.getCurrentUser().getUserId());
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

        File file = new File("src/app/images/profilePictures/profilePicture" + SessionManager.getCurrentUser().getUserId() + ".png");
        Image image = new Image(file.toURI().toString());
        imageUrl.setImage(image);
    }

    public void displayFriendProfile(User user)
    {
        profileUser = user;

        nameLabel.setText(user.getName());
        majorLabel.setText(user.getMajor());
        emailLabel.setText(user.getEmail());

        if (user.getRoomId() != 0)
        {
            dormLabel.setText(DormitoryDao.getRoomTypeById(user.getRoomId()).getDescription());
        }
        else
        {
            dormLabel.setText("");
        }

        descriptionLabel.setText(user.getBiography());
    }
    public void setProfileImage(ActionEvent event) throws IOException {
//        JFileChooser chooser = new JFileChooser();
//        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "tif"));
//        chooser.showSaveDialog(null);
//        File file = chooser.getSelectedFile();
//        User u = SessionManager.getCurrentUser();
//        String newPath = "src/app/images/profilePictures/profilePicture" + u.getUserId() + ".jpg";
//        Files.copy(Paths.get(file.getAbsolutePath()), Paths.get(newPath), StandardCopyOption.REPLACE_EXISTING);
//        u.setProfilePicturePath(newPath);
//        UserDao.updateUser(u);

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
