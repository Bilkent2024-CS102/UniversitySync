package app.controller;

import app.dao.UserDao;
import app.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SocialMyFriendsPageController implements Initializable {
    private FXMLLoader fxmlLoader;

    @FXML
    private VBox myFriendsVBox_ID = new VBox();    //right Event VBox where we put our events

    private List<User> friendsMocks;

    public void initialize(URL location, ResourceBundle resources) {
        friendsMocks = UserDao.getFriends(SessionManager.getCurrentUser().getUserId());

        try {
            for(int i = 0; i < friendsMocks.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/SocialPage/myFriendPane.fxml").toURI().toURL());
                Pane friendPane = fxmlLoader.load();
                SocialMyFriendsController eventController = fxmlLoader.getController();
                eventController.setData(friendsMocks.get(i));
                myFriendsVBox_ID.getChildren().add(friendPane);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}