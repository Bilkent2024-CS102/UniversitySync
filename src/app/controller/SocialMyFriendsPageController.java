package app.controller;
//import app.dao.UserDao;
//import app.model.FriendRequest;
//import app.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private Stage stage;
    private FXMLLoader fxmlLoader;

    //*****************************************************************************************************************
    private List<FriendsMock> friendsMocks;
    @FXML
    private VBox myFriendsVBox_ID = new VBox();    //right Event VBox where we put our events

    public void initialize(URL location, ResourceBundle resources) {
        friendsMocks = new ArrayList<>(data());

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

    private List<FriendsMock> data() {
        List<FriendsMock> tss = new ArrayList<>();

        FriendsMock friend1 = new FriendsMock();
        friend1.setFriendUsername("Atilla Akus");
        tss.add(friend1);
        FriendsMock friend2 = new FriendsMock();
        friend2.setFriendUsername("Musa Quershi");
        tss.add(friend2);
        FriendsMock friend3 = new FriendsMock();
        friend3.setFriendUsername("Zaeem Sheikh");
        tss.add(friend3);
        FriendsMock friend4 = new FriendsMock();
        friend4.setFriendUsername("Saqib Sheikh");
        tss.add(friend4);

        return tss;
        // return ForumPostDao.getPostsByRecency();
    }

//**********************************************************************************************************************

    public void displayFriendRequests(ActionEvent e)
    {
//        User currentUser = SessionManager.getCurrentUser();
//        ArrayList<FriendRequest> friendRequests = UserDao.getFriendRequests(currentUser.getUserId());

        // Need to make UI elements for each friend request
    }

    public void unfriend(ActionEvent e)
    {
//        User other = (User) e.getSource();
//        UserDao.removeFriend(SessionManager.getCurrentUser().getUserId(), other.getUserId());
    }

    public void addNewFriendsButton (ActionEvent event) {

    }
    public void searchFriendTextField () {

    }

}
