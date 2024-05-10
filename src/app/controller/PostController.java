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


    public void setData(ForumPost post) {
        User user = UserDao.getUserByID(post.getOwnerId());
        postUsernameID = user.getUserId();
        postTextAreaID = post.getMainText();
        postTextAreaID.setEditable(false);
     //    postUsernameID.setText(user.getUserId());
     //    postTextAreaID.setText(post.getHeading());
 
     //     String text;
     //     if (ForumPostDao.isPostLiked(post, SessionManager.getCurrentUser())) {text = "Unlike";}
     //     else {text = "Like";}
 
     //     likeButtonID = new Button(text);
     //     likeButtonID.setOnAction(event -> handleLikeButtonClick(post));
     //     commentButtonID = new Button("Comment");
     //     commentButtonID.setOnAction(event -> handleCommentButtonClick(post));
     }

    // private void handleLikeButtonClick(Post post) {
    //     if (likeButtonID.getText().equals("Like"))
    //     {
    //         ForumPostDao.addLike(post);
    //         likeButtonID.setText("Unlike");
    //     }
    //     else
    //     {
    //         ForumPostDao.removeLike(post);
    //         likeButtonID.setText("Like");
    //     }
    // }

    // private void handleCommentButtonClick(Post post) {
    //     // switch UI to comment section of post

    //     ArrayList<Reply> comments = ForumPostDao.getReplies(post);
    // }
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
