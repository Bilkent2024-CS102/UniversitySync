package app.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
//import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//import app.dao.UserDao;
//import app.model.userContent.Reply;
//import app.model.userContent.post.ForumPost;
//import app.dao.ForumPostDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePageController implements Initializable
{
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    // ******************************************************************* TEST ***************************************
    @FXML
    private VBox postVBoxID = new VBox();
    private List<Post> posts;

    public void initialize(URL location, ResourceBundle resources) {
        posts = new ArrayList<>(data());   //each time test controller class is called, we make a new arraylist and display the data

        try {
            for(int i = 0; i < posts.size(); i++) {

                fxmlLoader = new FXMLLoader(new File("src/app/view/Post.fxml").toURI().toURL());
                Pane postPane = fxmlLoader.load();  //the pane that contains posts in the post fxml

                PostController postController = fxmlLoader.getController();
                postController.setData(posts.get(i));

                postVBoxID.getChildren().add(postPane);  //now adding post to the vbox
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Post> data() {
        List<Post> ls = new ArrayList<>();
        Post post = new Post();
        post.setUsername("Musa");
        post.setPostText("hello i am musa and");
        ls.add(post);

        Post post2 = new Post();
        post2.setUsername("Atilla");
        post2.setPostText("hello i am atilla and");
        ls.add(post2);
        return ls;

        // return ForumPostDao.getPostsByRecency();
    }
// ******************************************************************* TEST ***************************************

    public void refreshAndShowPosts(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(new File("src/app/view/Homepage.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();

//        ArrayList<ForumPost> posts = ForumPostDao.getPostsByRecency();
//        displayRecentPosts(posts);
    }

//    /**
//     * This method is invoked when like buttons of ForumPosts
//     * are pressed. This method accessed the ForumPost object
//     * of which like button is pressed and uses according DAO
//     * class to add a like to the database. Also it updates the UI
//     * so that the like button shows a red heart and user knows what happened.
//     * @param e
//     */
//
//    public void likePost(ActionEvent e) {
        //        ForumPost post = e.getSource().getForumPost();
//        ForumPostDao.addLike(post);
//        // TODO for Zaeem.
//        // The like button should be red or change in someway.//   }
//
//    public void messageUser(ActionEvent e)
//    {
//        ForumPost post = e.getSource().getForumPost();
//        int user = post.getOwnerId();
//        showMessageScreen(user);
//    }
//
//    /**
//     * TODO for Zaeem
//     * This method should showCommentPane when comment button
//     * of the ForumPost instance is clicked.
//     */
//    public void showCommentPane(ActionEvent e)
//    {
//        ForumPost post = e.getSource().getForumPost();
//        ArrayList<Reply> comments = ForumPostDao.getReplies(post);
//        // TODO: display comments and also a send button for
//        //  currentUser's comment.
//    }
//
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
//        Date createdAt = null;
//        Date lastEdit = null;
//        Reply comment = new Reply(SessionManager.getCurrentUser(), commentContent, createdAt, lastEdit, post);
//        ForumPostDao.addComment(post, comment);
//        // TODO: show success message.
//    }
//
//    public void addFriendButton(ActionEvent e)
//    {
//        ForumPost post = e.getSource().getForumPost();
//        int user = post.getOwnerId();
//        UserDao.addFriendRequest(SessionManager.getCurrentUser(), user);
//        // TODO: show success message.
//    }
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
