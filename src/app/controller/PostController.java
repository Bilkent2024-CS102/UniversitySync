package app.controller;

//import app.dao.UserDao;
//import app.model.User;
//import app.model.userContent.post.ForumPost;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class PostController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

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


    public void switchToPostReply(ActionEvent event) throws IOException {
        //TODO: after pressing reply under post, this opens the reply page associated with that particular post
        //TODO: UI > PostReply.fxml
    }

//   public void setData(Post post) {
////        User user = UserDao.getUserByID(post.getOwnerId());
//            postUsernameID = user.getUserId();
        //postTextAreaID = post.getMainText();
//        postTextAreaID.setEditable(false);
//         postUsernameID.setText(user.getUserId());
//         postTextAreaID.setText(post.getHeading());
 
     //     String text;
     //     if (ForumPostDao.isPostLiked(post, SessionManager.getCurrentUser())) {text = "Unlike";}
     //     else {text = "Like";}
 
     //     likeButtonID = new Button(text);
     //     likeButtonID.setOnAction(event -> handleLikeButtonClick(post));
     //     commentButtonID = new Button("Comment");
     //     commentButtonID.setOnAction(event -> handleCommentButtonClick(post));
   //  }

     private void handleLikeButton(PostMock post) {
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
    }

    private void handleCommentButtonPost(PostMock post) {
    //     // switch UI to comment section of post

    //     ArrayList<Reply> comments = ForumPostDao.getReplies(post);
    }
    private void handleMessageButton(PostMock post) {
        //     // switch UI to comment section of post

        //     ArrayList<Reply> comments = ForumPostDao.getReplies(post);
    }
    private void handleAddFriendButton(PostMock post) {
        //     // switch UI to comment section of post

        //     ArrayList<Reply> comments = ForumPostDao.getReplies(post);
    }

    /**
     *
     * @param post : sets the data (username, text, noOfLikes) for the passed post parameter
     */
    public void setData(PostMock post) {
        // Image image = new Image(getClass().getResourceAsStream(post.getProfileImageSrc()));
        // userImageOnPostID.setImage(image);
        postUsernameID.setText(post.getUsername());
        postTextAreaID.setText(post.getPostText());
    }

    public void editPost(ActionEvent event) {

    }

    public void deletePost(ActionEvent event) {
    }
}


class PostMock {
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
