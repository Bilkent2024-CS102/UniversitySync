package app.controller;

import app.dao.ForumPostDao;
import app.dao.UserDao;
import app.model.FriendRequest;
import app.model.userContent.post.ForumPost;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;

public class PostController {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private FXMLLoader fxmlLoader2;

    @FXML
    private ImageView userImageOnPostID;
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

    private ForumPost thisPost;

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

    public void setData(ForumPost post)
    {
        thisPost = post;
        userName.setText("" + UserDao.getUserById(post.getOwnerId()).getName());
        postTextAreaID.setText(post.getMainText());
        postTextAreaID.setEditable(false);
        likeNumber.setText(ForumPostDao.getPostLikes(post.getUserContentItemId()) + " Likes");
        commentNumber.setText(post.getCommentCount() + " Comments");
        postHeading.setText(post.getHeading());
        postHeading.setEditable(false);
        postTextAreaID.setEditable(false);

        boolean isLiked = ForumPostDao.isLikedByUser(SessionManager.getCurrentUser().getUserId(), post.getUserContentItemId());
        if (isLiked) {likeButtonID.setText("Unlike");}

        boolean isFriend = UserDao.isFriend(SessionManager.getCurrentUser().getUserId(), thisPost.getOwnerId());
        if (isFriend) {addFriendButtonID.setText("Unfriend");}
        else if (UserDao.friendRequestExist(SessionManager.getCurrentUser().getUserId(), thisPost.getOwnerId()))
        {addFriendButtonID.setText("Cancel Request");}
        else if (UserDao.friendRequestExist(thisPost.getOwnerId(), SessionManager.getCurrentUser().getUserId()))
        {addFriendButtonID.setText("View Request");}
        else {addFriendButtonID.setText("Send Request");}

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

        File file = new File("src/app/images/profilePictures/profilePicture" + post.getOwnerId() + ".png");
        Image image = new Image(file.toURI().toString());
        userImageOnPostID.setImage(image);
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

    public void messageUser(ActionEvent event) throws IOException {
        switchToFXML2("src/app/view/MessagePopup.fxml", event);
    }

    public void addFriendButton(ActionEvent e) throws IOException {
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
        else if (addFriendButtonID.getText().equals("View Request"))
        {
            String fxmlFileName = "src/app/view/SocialPage/socialFriendRequest.fxml";
            fxmlLoader2 = new FXMLLoader(new File(fxmlFileName).toURI().toURL());
            Parent root = fxmlLoader2.load();

            if (e.getSource() instanceof Node) {                                    //FOR Buttons
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            }
            else if (e.getSource() instanceof MenuItem menuItem)  {                 //FOR MenuButtons
                stage = (Stage) menuItem.getParentPopup().getOwnerWindow();
            }
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);     //it should be after stage.setScene
            stage.show();
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
        alert.initOwner((Stage) ((Button) e.getSource()).getScene().getWindow());
        alert.showAndWait();
        ForumPostDao.delete(thisPost.getUserContentItemId());

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
            editButton.setText("Submit");
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
}