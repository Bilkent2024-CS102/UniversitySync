package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SocialTopTabController {
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
    public void switchToMyFriends(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/SocialPage/socialMyFriends.fxml", event);
    }
    public void switchToAllMessages(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/SocialPage/socialAllMessages.fxml", event);
    }
    public void switchToEvents(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/SocialPage/socialEvents.fxml", event);
    }
    public void switchToFriendRequests(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/SocialPage/socialFriendRequest.fxml", event);
    }

}
