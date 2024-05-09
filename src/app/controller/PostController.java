package app.controller;

//import app.dao.UserDao;
//import app.model.User;
//import app.model.userContent.post.ForumPost;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
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


    public void setData(Post post) {
        //Image image = new Image(getClass().getResourceAsStream(post.getProfileImageSrc()));
       // userImageOnPostID.setImage(image);
        postUsernameID.setText(post.getUsername());
        postTextAreaID.setText(post.getPostText());
    }
}
class Post {
    private String username;
    private String postText;
    private String profileImageSrc;

    public String getUsername() {
        return username;
    }

    public String getProfileImageSrc() {
        return profileImageSrc;
    }

    public String getPostText() {
        return postText;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfileImageSrc(String profileImageSrc) {
        this.profileImageSrc = profileImageSrc;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }
}
