package app.dao;

import app.controller.SessionManager;
import app.model.userContent.Reply;
import app.model.userContent.post.ForumPost;

import java.sql.*;
import java.util.ArrayList;

/**
 * The ForumPostDao class provides methods for accessing and managing forum post data in the database.
 */
public class ForumPostDao {

    /**
     * Retrieves a forum post by its ID from the database.
     *
     * @param id The ID of the forum post to retrieve.
     * @return The ForumPost object corresponding to the given ID, or null if no such post exists.
     */
    public static ForumPost getPostById(int id)
    {
        ForumPost post = null;
        try
        {
            String query = "SELECT FROM university_sync.forum_post WHERE forum_post_id=" + id;
            PreparedStatement st = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            rs.next();
            post = new ForumPost(
                    rs.getInt("forum_post_id"),
                    rs.getString("main_text"),
                    rs.getDate("creation_date"),
                    rs.getDate("last_edit_date"),
                    rs.getString("heading")
            );
            return post;
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return null;
        }
    }


    /**
     * @return all  {@code EventPost} instances by order of recency.
     */
    public static ArrayList<ForumPost> getForumPostsByRecency()
    {
        ArrayList<ForumPost> posts = new ArrayList<ForumPost>();
        ForumPost post = null;
        try
        {
            String query = "SELECT * FROM university_sync.forum_post ORDER BY creation_date DESC";
            PreparedStatement st = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                post = new ForumPost(
                        rs.getInt("forum_post_id"),
                        rs.getInt("owner_student_id"),
                        rs.getString("main_text"),
                        rs.getDate("creation_date"),
                        rs.getDate("last_edit_date"),
                        rs.getString("heading")
                );
                posts.add(post);
            }
            return posts;
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

    /**
     * Checks if a forum post is liked by a specific user.
     *
     * @param userId      The ID of the user.
     * @param forumPostId The ID of the forum post.
     * @return True if the user has liked the post, false otherwise.
     */
    public static boolean isLikedByUser(int userId, int forumPostId)
    {
        boolean isLiked = false;
        try{
            String query = "SELECT * FROM university_sync.like_forum_post WHERE liked_by_student_id = ? AND liked_forum_post_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, userId);
            pst.setInt(2, forumPostId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                isLiked = true;
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return isLiked;
    }

    /**
     * Adds a like to a forum post in the database.
     *
     * @param post The forum post to add a like to.
     * @return True if the operation was successful, false otherwise.
     */
    public static boolean addLike(ForumPost post)
    {
        try {
            String query = "INSERT INTO university_sync.like_forum_post (liked_by_student_id, liked_forum_post_id) VALUES (?,?)";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, SessionManager.getCurrentUser().getUserId());
            pst.setInt(2, post.getUserContentItemId());
            pst.executeUpdate();
            return true;
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    /**
     * Adds a comment (reply) to a forum post in the database.
     *
     * @param comment The reply to be added.
     * @return The ID of the newly added comment, or -1 if an error occurred.
     */
    public static int addComment(Reply comment)
    {
        int postId = comment.getPostId();
        ForumPost post = ForumPostDao.getPostById(postId);
        try {
            String query = "INSERT INTO university_sync.comment (owner_student_id, creation_date, last_edit_date, main_text, replies_to_forum_post_id) VALUES (?,?,?,?,?)";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, comment.getOwnerId());
            pst.setDate(2, new Date(comment.getCreationDate().getTime()));
            pst.setDate(3, new Date(comment.getLastEditDate().getTime()));
            pst.setString(4, comment.getMainText());
            pst.setInt(5, comment.getUserContentItemId());
            pst.executeUpdate();
            Statement st = DBConnectionManager.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
            rs.next();
            int idOfNewComment = rs.getInt(1);
            return idOfNewComment;
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return -1;
        }
    }

    /**
     * Delete a comment from the database.
     * @param commentId The ID of the comment to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    public static boolean deleteComment(int commentId) {
        try {
            String query = "DELETE FROM university_sync.comment WHERE comment_id=?";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, commentId);
            pst.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves replies to a forum post from the database.
     *
     * @param post The forum post to retrieve replies for.
     * @return An ArrayList containing the replies to the specified post.
     */
    public static ArrayList<Reply> getReplies(ForumPost post)
    {
        ArrayList<Reply> result = new ArrayList<Reply>();
        try
        {
            String query = "SELECT FROM university_sync.reply WHERE replies_to_forum_post_id=" + post.getUserContentItemId();
            PreparedStatement st = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                Reply r = new Reply(
                        rs.getInt("owner_student_id"),
                        rs.getString("main_text"),
                        rs.getDate("creation_date"),
                        rs.getDate("last_edit_date"),
                        rs.getInt("replies_to_forum_post_id")
                );
                r.setReplyId(rs.getInt("reply_id"));
                result.add(r);
            }
            return result;
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            return null;
        }
    }

    /**
     * Deletes a forum post from the database.
     *
     * @param postId The ID of the post to delete.
     */
    public static void delete(int postId)
    {
        try
        {
            String query = "DELETE FROM university_sync.forum_post WHERE forum_post_id=" + postId;
            PreparedStatement ps = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet rs = ps.getResultSet();
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            return;
        }
    }

    /**
     * Edits a forum post in the database.
     *
     * @param postId     The ID of the post to edit.
     * @param editedText The edited text to replace the original post content.
     * @return True if the edit was successful, false otherwise.
     */
    public static boolean edit(int postId, String editedText)
    {
        String query = "UPDATE university_sync.forum_post SET main_text='" + editedText + "' WHERE forum_post_id=" + postId;
        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.executeQuery();
            return true;
        }
        catch (SQLException sqle)
        {
            return false;
        }
    }

    /**
     * Adds a forum post to the database.
     *
     * @param forumPost The forum post to add.
     * @return The ID of the newly added post, or -1 if an error occurred.
     */
    public static int addForumPost(ForumPost forumPost)
    {
        String query = "INSERT INTO university_sync.forum_post " +
                "(forum_post_id, owner_student_id, creation_date, last_edit_date, heading, main_text, like_count) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, forumPost.getUserContentItemId());
            pst.setInt(2, forumPost.getOwnerId());
            pst.setDate(3, new java.sql.Date(forumPost.getCreationDate().getTime()));
            pst.setDate(4, new java.sql.Date(forumPost.getLastEditDate().getTime()));
            pst.setString(5, forumPost.getHeading());
            pst.setString(6, forumPost.getMainText());
            pst.setInt(7,  forumPost.getLikeCount());

            pst.executeUpdate();

            Statement st = DBConnectionManager.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
            rs.next();
            int id = rs.getInt(1);
            return id;
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            return -1;
        }
    }
}
