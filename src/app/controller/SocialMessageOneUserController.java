package app.controller;

import app.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.io.File;
import java.io.IOException;

public class SocialMessageOneUserController {

    private FXMLLoader fxmlLoader;
    private SocialAllMessagesController largeController;

    @FXML
    private Label friendToMessageName_ID; // The place where we display message box
    private User associatedFriend;

    public void setData( User friend, SocialAllMessagesController largeController) {
        this.largeController = largeController;
        associatedFriend = friend;
        friendToMessageName_ID.setText(friend.getName());
    }

    public void displayTextScreen(MouseEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File("src/app/view/SocialPage/messageBox.fxml").toURI().toURL());
        Pane newScene = fxmlLoader.load();   //the pane on which texts would be displayes

        SocialMessageBoxController eventController = fxmlLoader.getController();
        eventController.setData(associatedFriend);
        largeController.getBorderTextBox_ID().setCenter(newScene);
    }
}