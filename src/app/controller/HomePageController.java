package app.controller;

import java.sql.Date;
import java.util.ArrayList;

import app.dao.UserDao;
import app.model.User;
import app.model.userContent.Reply;
import app.model.userContent.post.ForumPost;
import app.dao.ForumPostDao;
import javafx.event.ActionEvent;

public class HomePageController 
{
    public void refreshAndShowPosts()
    {
        ArrayList<ForumPost> posts = ForumPostDao.getPostsByRecency();
        displayRecentPosts(posts);
    }

    /**
     * This method is invoked when like buttons of ForumPosts
     * are pressed. This method accessed the ForumPost object
     * of which like button is pressed and uses according DAO
     * class to add a like to the database. Also it updates the UI
     * so that the like button shows a red heart and user knows what happened.
     * @param e
     */
    public void likePost(ActionEvent e)
    {
        ForumPost post = e.getSource().getForumPost();
        ForumPostDao.addLike(post);
        // TODO for Zaeem.
        // The like button should be red or change in someway.
    }

    public void messageUser(ActionEvent e)
    {
        ForumPost post = e.getSource().getForumPost();
        User user = post.getOwner();
        showMessageScreen(user);
    }

    /**
     * TODO for Zaeem
     * This method should showCommentPane when comment button
     * of the ForumPost instance is clicked.
     */
    public void showCommentPane(ActionEvent e)
    {
        ForumPost post = e.getSource().getForumPost();
        ArrayList<Reply> comments = ForumPostDao.getReplies(post);
        // TODO: display comments and also a send button for
        //  currentUser's comment.
    }

    /**
     * This buttons send the comment message.
     * @param e
     */
    public void sendComment(ActionEvent e)
    {
        // The below line accessed the post and the comment content
        // through thew commentPane invoked.
        ForumPost post = e.getSource().getForumPost();
        String commentContent = e.getSource().getCommentContent();
        Date createdAt = null;
        Date lastEdit = null;
        Reply comment = new Reply(SessionManager.getCurrentUser(), commentContent, createdAt, lastEdit, post);
        ForumPostDao.addComment(post, comment);
        // TODO: show success message.
    }

    public void addFriendButton(ActionEvent e)
    {
        ForumPost post = e.getSource().getForumPost();
        User user = post.getOwner();
        UserDao.addFriendRequest(SessionManager.getCurrentUser(), user);
        // TODO: show success message.
    }

    public void deletePostButton(ActionEvent e)
    {
        ForumPost post = e.getSource().getForumPost();
        if (post.getOwner().getUserId() == SessionManager.getCurrentUser().getUserId)
        {
            ForumPostDao.delete(post);
        }
        // TODO: show success message.
    }

    public void editPostButton(ActionEvent e)
    {
        ForumPost post = e.getSource().getForumPost();
        showEditDialog(post);
    }

    public void sendEdit(ActionEvent e)
    {
        // Below line accesses the post and the edited text
        // through its UI EditDialog component.
        ForumPost post = e.getSource().getForumPost();
        String editedText = e.getSource().getEditedText();
        ForumPostDao.edit(post,editedText);
    }

    public void showEditDialog(ForumPost post)
    {
        // Show edit dialog.
    }

    /**
     * @TODO for Zaeem
     * This method should take the ArrayList of ForumPosts
     * and fill the fxml template displaying them for all
     * ForumPosts in the ArrayList.
     * Note: These components must have a getForumPost() method
     * to access the post object of which UI element is interacted
     * @see #likePost(ActionEvent)
     * @param posts
     */
    public void displayRecentPosts(ArrayList<ForumPost> posts)
    {
        for (ForumPost post1 : posts)
        {
            // Render FXML with info from post1.
        }
    }

    /**
     * TODO for Zaeem and maybe some other guy.
     * This method should display messaging screen for given user.
     * @param user
     */
    public void showMessageScreen(User user)
    {

    }

}
