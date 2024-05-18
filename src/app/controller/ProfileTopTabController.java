package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ProfileTopTabController {
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

    public void switchToMyPosts(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/ProfilePage/profileMyPost.fxml", event);
    }
    public void switchToLikedPosts(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/ProfilePage/profileLikedPost.fxml", event);
    }
    public void switchToEditProfile(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/ProfilePage/profileEdit.fxml", event);
    }
    public void switchToContactUs(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/ProfilePage/profileContactUs.fxml", event);
    }
}