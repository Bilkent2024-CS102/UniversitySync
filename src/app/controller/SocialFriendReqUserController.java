package app.controller;

import app.dao.UserDao;
import app.model.FriendRequest;
import app.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SocialFriendReqUserController {

    @FXML
    private ImageView userImage_ID;
    @FXML
    private Label username_ID;

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    private User thisSender;

    public void setData(FriendRequest friendsMock) {
        User senderUser = UserDao.getUserById(friendsMock.getSenderId());
        thisSender = senderUser;
        username_ID.setText("" + UserDao.getUserById(friendsMock.getSenderId()).getName());
    }

    public void acceptRequest(ActionEvent e) throws IOException {
        UserDao.concludeFriendRequest(thisSender.getUserId(), SessionManager.getCurrentUser().getUserId(), true);
        refresh(e);
    }

    public void rejectRequest(ActionEvent e) throws IOException {
        UserDao.concludeFriendRequest(thisSender.getUserId(), SessionManager.getCurrentUser().getUserId(), false);
        refresh(e);
    }

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();

        if (event.getSource() instanceof Node) {                                    //FOR Buttons
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }
        else if (event.getSource() instanceof MenuItem menuItem)  {                 //FOR MenuButtons
            stage = (Stage) menuItem.getParentPopup().getOwnerWindow();
        }
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);     //it should be after stage.setScene
        stage.show();
    }
    
    public void refresh(ActionEvent event) throws IOException {
        switchToFXML("src/app/view/SocialPage/socialFriendRequest.fxml", event);
    }
}
