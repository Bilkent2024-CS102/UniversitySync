package app.controller;

//import app.dao.UserDao;
//import app.model.User;
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
    private List<FriendsMock> friendsMocks;


    public void initialize(URL location, ResourceBundle resources) {
        friendsMocks = new ArrayList<>(data());

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

    private List<FriendsMock> data() {
        List<FriendsMock> tss = new ArrayList<>();

        FriendsMock friend1 = new FriendsMock();
        friend1.setFriendUsername("Khaitul");
        tss.add(friend1);
        FriendsMock friend2 = new FriendsMock();
        friend2.setFriendUsername(" Taren Choi");
        tss.add(friend2);
        FriendsMock friend3 = new FriendsMock();
        friend3.setFriendUsername("Badar");
        tss.add(friend3);
        FriendsMock friend4 = new FriendsMock();
        friend4.setFriendUsername("Tauseef");
        tss.add(friend4);

        return tss;
        // return ForumPostDao.getPostsByRecency();
    }


}
