package app.controller;

//import app.dao.UserDao;
import app.model.User;
import app.dao.UserDao;
import com.mysql.cj.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SocialFriendReqPageController implements Initializable {
    private FXMLLoader fxmlLoader;
    @FXML
    private VBox friendReqVBox_ID;
    private List<User> friendsMocks;


    public void initialize(URL location, ResourceBundle resources) {
        friendsMocks = UserDao.getFriends(SessionManager.getCurrentUser().getUserId());

        try {
            for(int i = 0; i < friendsMocks.size(); i++) {
                fxmlLoader = new FXMLLoader(new File("src/app/view/SocialPage/SocialFriendReqUser.fxml").toURI().toURL());
                Pane friendPane = fxmlLoader.load();
                SocialFriendReqUserController eventController = fxmlLoader.getController();
                eventController.setData(friendsMocks.get(i));
                friendReqVBox_ID.getChildren().add(friendPane);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
