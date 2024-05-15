package app.controller;

import app.dao.UserDao;
import app.model.FriendRequest;
import app.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import java.io.File;
import java.io.File;
import java.io.IOException;

public class SocialMyFriendsController {

    //*******************************************************************************************************************
    @FXML
    private ImageView friendProfileImageID;
    @FXML
    private Label friendUsernameID;
    @FXML
    private Button messageButton;
    @FXML
    private Button unfriendButton;

    private User thisFriend;

    private FXMLLoader fxmlLoader;
    private Stage stage;
    private Scene scene;

    public void setData(User friend) {
//      Image image = new Image(getClass().getResourceAsStream(post.getProfileImageSrc()));
//      friendProfileImageID.setImage(image);
        thisFriend = friend;
        friendUsernameID.setText(friend.getName());
        if (UserDao.isFriend(SessionManager.getCurrentUser().getUserId(), friend.getUserId()))
        {
            unfriendButton.setText("Unfriend");
        }
        else
        {
            unfriendButton.setText("Add Friend");
        }
    }

//*******************************************************************************************************************

    private void switchToFXML2(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();
        MessagePopupController newController = fxmlLoader.getController();
        newController.setData(thisFriend);
        // Create a new stage for the filter screen
        Stage filterStage = new Stage();
        filterStage.initModality(Modality.APPLICATION_MODAL); // Make it modal
        filterStage.initStyle(StageStyle.UTILITY);
        filterStage.setScene(new Scene(root));
        // Show the filter screen
        filterStage.showAndWait();
    }

    public void messagePopUp(ActionEvent event) throws IOException {
        switchToFXML2("src/app/view/MessagePopup.fxml", event);
    }

    public void messageFriendButton (ActionEvent event) throws IOException {
//       switchToMessages(event);
        messagePopUp(event);
        // 2 Steps:
                // Takes you to the all messages social page
                // and opens the message pane of this particular user

    }
    public void unFriendButton (ActionEvent event) throws IOException {
        if (unfriendButton.getText().equals("Unfriend"))
        {
            UserDao.removeFriend(SessionManager.getCurrentUser().getUserId(), thisFriend.getUserId());
        }
        else
        {
            FriendRequest fr = new FriendRequest(SessionManager.getCurrentUser().getUserId(), thisFriend.getUserId());
            UserDao.addFriendRequest(fr);
        }
        refresh(event);
    }

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void switchToMessages(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/SocialPage/socialAllMessages.fxml", event);
    }

    public void refresh(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/SocialPage/socialMyFriends.fxml", event);
    }
}