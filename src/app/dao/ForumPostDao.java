package app.dao;

import app.controller.SessionManager;
import app.model.userContent.Reply;
import app.model.userContent.post.ForumPost;

import java.sql.*;
import java.util.ArrayList;

public class ForumPostDao {
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
        int post = comment.getPostId();
        try {
            String query = "INSERT INTO university_sync.comment (owner_student_id, creation_date, last_edit_date, main_text, replies_to_forum_post_id) VALUES (?,?,?,?,?)";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(2, comment.getOwnerId());
            pst.setDate(3, new Date(post.getCreationDate().getTime()));
            pst.setDate(4, new Date(post.getLastEditDate().getTime()));
            pst.setString(5, comment.getMainText());
            pst.setInt(6, post.getUserContentItemId());
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
     * TODO
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
            PreparedStatement st = DBConnectionManager.getConnection().createStatement(query);
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

    public static void delete(ForumPost post)
    {

    }

    public static void edit(ForumPost post, String editedText)
    {

    }
}
