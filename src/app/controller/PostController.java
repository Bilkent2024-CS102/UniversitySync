package app.controller;

import app.dao.UserDao;
import app.model.User;
import app.model.userContent.post.ForumPost;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class PostController {
    @FXML
    private ImageView userImageOnPostID;

    @FXML
    private Label postUsernameID;

    @FXML
    private Button addFriendButtonID;

    @FXML
    private Button commentButtonID;

    @FXML
    private Button likeButtonID;

    @FXML
    private Button messageButtonID;

    @FXML
    private TextArea postTextAreaID;

    @FXML
    private VBox postVBoxID;

    public void setData(ForumPost post) {
//        User user = UserDao.getUserByID(post.getOwnerId());

    }
}
