package app.controller;

//import app.dao.UserDao;
//import app.model.User;
//import app.model.userContent.post.ForumPost;
import app.dao.ForumPostDao;
import app.dao.UserDao;
import app.model.FriendRequest;
import app.model.User;
import app.model.userContent.post.ForumPost;
import app.model.userContent.Reply;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PostController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private ForumPost thisPost;


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
    private TextArea commentTextAreaID;
    @FXML
    private Button commentSendButtonID;
    @FXML
    private Button deleteButton;

    // The below will be labels. They are strings just for now.
    private String heading;
    private String likeCount;
    private String commentCount;

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


    public void setData(ForumPost post)
    {
        // Below works good.
        thisPost = post;
        postUsernameID.setText("" + post.getOwnerId());
        postTextAreaID.setText(post.getMainText());
        postTextAreaID.setEditable(false);
        heading = post.getHeading();
        likeCount = post.getLikeCount() + " Likes";
        commentCount = post.getCommentCount() + " Comments";

        // This part has some problems.
        boolean isLiked = ForumPostDao.isLikedByUser(SessionManager.getCurrentUser().getUserId(), post.getUserContentItemId());
        if (isLiked)
        {
            likeButtonID.setText("Unlike");
        }

        boolean isFriend = UserDao.isFriend(SessionManager.getCurrentUser().getUserId(), thisPost.getOwnerId());
        if (isFriend)
        {
            addFriendButtonID.setText("Unfriend");
        }
        else if (UserDao.friendRequestExist(SessionManager.getCurrentUser().getUserId(), thisPost.getOwnerId()))
        {
            addFriendButtonID.setText("Cancel Request");
        }
        else if (UserDao.friendRequestExist(thisPost.getOwnerId(), SessionManager.getCurrentUser().getUserId()))
        {
            addFriendButtonID.setText("View Request");
        }
        else
        {
            addFriendButtonID.setText("Send Request");
        }

        if (post.getOwnerId() == SessionManager.getCurrentUser().getUserId())
        {
            likeButtonID.setDisable(true);
            commentButtonID.setDisable(true);
            messageButtonID.setDisable(true);
            addFriendButtonID.setDisable(true);
        }
        else
        {
            deleteButton.setDisable(true);
        }

    }

    public void setData(Reply reply)
    {

    }

    public void likeButton(ActionEvent e)
    {
        if (likeButtonID.getText().equals("Like"))
        {
            likeButtonID.setText("Unlike");
            ForumPostDao.addLike(thisPost.getUserContentItemId(), SessionManager.getCurrentUser().getUserId());
        }
        else
        {
            likeButtonID.setText("Like");
            ForumPostDao.removeLike(thisPost.getUserContentItemId(), SessionManager.getCurrentUser().getUserId());
        }
    }


    // TODO for zaeem
    public void messageUser(ActionEvent e)
    {
        //showMessageScreen(thisPost.getOwnerId());
    }


    /**
     * TODO for Zaeem
     * This method should showCommentPane when comment button
     * of the ForumPost instance is clicked.
     */
    public void showCommentPane(ActionEvent e)
    {
//        UI method for comment page

        ArrayList<Reply> comments = ForumPostDao.getReplies(thisPost.getUserContentItemId());
        // TODO: display comments and also a send button for
//        Use a for loop to display all the comments in the new UI page
        //  currentUser's comment.
    }

    public void submitComment(ActionEvent e)
    {
        String comment = commentTextAreaID.getText();
        Reply reply = new Reply(SessionManager.getCurrentUser().getUserId(), comment, thisPost.getUserContentItemId());
        ForumPostDao.addComment(reply);
    }

    public void returnBack(ActionEvent e)
    {
        // Load UI page of homepage
    }

    // THIS METHOD BELOW MIGHT NEED TO BE IN ANOTHER CONTROLLER.
//    /**
//     * This buttons send the comment message.
//     * @param e
//     */
//    public void sendComment(ActionEvent e)
//    {
//        // The below line accessed the post and the comment content
//        // through thew commentPane invoked.
//        int post = e.getSource().getForumPost();
//        String commentContent = e.getSource().getCommentContent();
//        Reply comment = new Reply(SessionManager.getCurrentUser().getUserId(), )
//        Reply comment = new Reply(SessionManager.getCurrentUser(), commentContent, createdAt, lastEdit, post);
//        ForumPostDao.addComment(post, comment);
//        // TODO: show success message.
//    }

    public void addFriendButton(ActionEvent e)
    {
        boolean isFriend = UserDao.isFriend(SessionManager.getCurrentUser().getUserId(), thisPost.getOwnerId());
        if (addFriendButtonID.getText().equals("Unfriend"))
        {
            UserDao.removeFriend(thisPost.getOwnerId(), SessionManager.getCurrentUser().getUserId());
            addFriendButtonID.setText("Send Request");
        }
        else if (addFriendButtonID.getText().equals("Cancel Request"))
        {
            UserDao.concludeFriendRequest(SessionManager.getCurrentUser().getUserId(), thisPost.getOwnerId(), false);
            addFriendButtonID.setText("Send Request");
        }
        else if (addFriendButtonID.getText().equals("Accept OR Deny?"))
        {
            // Change the UI to friend request page.
            // UI part Zaeem.
        }
        else
        {
            FriendRequest fr = new FriendRequest(SessionManager.getCurrentUser().getUserId(), thisPost.getOwnerId());
            UserDao.addFriendRequest(fr);
            addFriendButtonID.setText("Cancel Request");
        }
    }

    public void deleteButton(ActionEvent e)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("You have deleted.");
        alert.showAndWait();
        ForumPostDao.delete(thisPost.getUserContentItemId());
//        Alert confirm = new Alert(Alert.AlertType.INFORMATION, "Post deleted");

        try {
            HomePageController.refresh(e);
        }
        catch (IOException exception)
        {
            System.out.println("IO Exception found");
        }
    }

    public void editPost(ActionEvent event){

    }

//
//    public void deletePostButton(ActionEvent e)
//    {
//        ForumPost post = e.getSource().getForumPost();
//        if (post.getOwnerId().getUserId() == SessionManager.getCurrentUser().getUserId)
//        {
//            ForumPostDao.delete(post.getUserContentItemId());
//        }
//        // TODO: show success message.
//    }
//
//    public void editPostButton(ActionEvent e)
//    {
//        ForumPost post = e.getSource().getForumPost();
//        showEditDialog(post);
//    }
//
//    public void sendEdit(ActionEvent e)
//    {
//        // Below line accesses the post and the edited text
//        // through its UI EditDialog component.
//        ForumPost post = e.getSource().getForumPost();
//        String editedText = e.getSource().getEditedText();
//        ForumPostDao.edit(post,editedText);
//    }
//
//    public void showEditDialog(ForumPost post)
//    {
//        // Show edit dialog.
//    }
//
//    /**
//     * @TODO for Zaeem
//     * This method should take the ArrayList of ForumPosts
//     * and fill the fxml template displaying them for all
//     * ForumPosts in the ArrayList.
//     * Note: These components must have a getForumPost() method
//     * to access the post object of which UI element is interacted
//     * @see #likePost(ActionEvent)
//     * @param posts
//     */
//    public void displayRecentPosts(ArrayList<ForumPost> posts)
//    {
//        for (ForumPost post1 : posts)
//        {
//            // Render FXML with info from post1.
//        }
//    }
//
//    /**
//     * TODO for Zaeem and maybe some other guy.
//     * This method should display messaging screen for given user.
//     * @param user
//     */
//    public void showMessageScreen(int user)
//    {
//
//    }



}
