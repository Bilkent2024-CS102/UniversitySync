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
import javafx.stage.StageStyle;

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
    @FXML
    private Button editButton;
    @FXML
    private Label likeNumber;
    @FXML
    private Label commentNumber;
    @FXML
    private Label userName;
    @FXML
    private TextField postHeading;

    private void switchToFXML(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();
        PostReplyPageController newController = fxmlLoader.getController();
        newController.setData(thisPost);

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

    private void switchToFXML2(String fxmlFileName, ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
        Parent root = fxmlLoader.load();
        MessagePopupController newController = fxmlLoader.getController();
        newController.setData(UserDao.getUserById(thisPost.getOwnerId()));
        // Create a new stage for the filter screen
        Stage filterStage = new Stage();
        filterStage.initModality(Modality.APPLICATION_MODAL); // Make it modal
        filterStage.initStyle(StageStyle.UTILITY);
        filterStage.setScene(new Scene(root));
        // Show the filter screen
        filterStage.showAndWait();
    }


    public void switchToPostReply(ActionEvent event) throws IOException {
        //TODO: after pressing reply under post, this opens the reply page associated with that particular post
        //TODO: UI > PostReply.fxml
    }


    public void setData(ForumPost post)
    {
        // Below works good.
        thisPost = post;
        userName.setText("" + UserDao.getUserById(post.getOwnerId()).getName());
        postTextAreaID.setText(post.getMainText());
        postTextAreaID.setEditable(false);
        likeNumber.setText(post.getLikeCount() + " Likes");
        commentNumber.setText(post.getCommentCount() + " Comments");
        postHeading.setEditable(false);
        postTextAreaID.setEditable(false);

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
            editButton.setDisable(true);
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
    public void messageUser(ActionEvent event) throws IOException {
        switchToFXML2("src/app/view/MessagePopup.fxml", event);
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
            // nothing :)
        }
    }

    public void editPost(ActionEvent event){
        if (editButton.getText().equals("Edit"))
        {
            postTextAreaID.setEditable(true);
            editButton.setText("Unedit");
        }
        else
        {
            String newPostText = postTextAreaID.getText();
            thisPost.setMainText(newPostText);
            ForumPostDao.edit(thisPost.getUserContentItemId(), newPostText);
            postTextAreaID.setEditable(false);
            editButton.setText("Edit");
        }
    }

    public void commentButtonPressed(ActionEvent event) throws IOException {

        switchToFXML("src/app/view/PostReplyPage.fxml", event);
    }

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
