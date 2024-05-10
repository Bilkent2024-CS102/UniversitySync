package app.dao;

import app.controller.SessionManager;
import app.model.userContent.Reply;
import app.model.userContent.post.ForumPost;

import java.sql.*;
import java.util.ArrayList;

public class ForumPostDao {
    public static ForumPost getPostById(int id)
    {
        ForumPost post = null;
        try
        {
            String query = "SELECT FROM university_sync.forum_post WHERE forum_post_id=" + id;
            PreparedStatement st = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            rs.next();
            post = new ForumPost(rs.getInt("forum_post_id"), rs.getString("main_text"),
                    rs.getDate("creation_date"), rs.getDate("last_edit_date"), rs.getString("heading"));
            return post;
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

    public static ArrayList<ForumPost> getPostsByRecency() {
        return null;
    }

    /**
     * Add like to the post in DB.
     * @param post
     * @return whether the operation successfull or not.
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
     * Add a reply object for given post.
     *
     * @param comment
     * @return the id of the new reply added to DB.
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
     * return replies of the given ForumPost instance
     * from database.
     * @param post
     * @return
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
