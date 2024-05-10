package app.dao;

import app.controller.SessionManager;
import app.model.userContent.Reply;
import app.model.userContent.post.DormTransferPost;
import app.model.userContent.post.ForumPost;

import java.sql.*;
import java.util.ArrayList;

public class DormTransferPostDao {

    /**
     * Takes integer id and makes a 'SELECT' query.
     * @param id
     * @return the DormTransferPost instance by given id from database.
     */
    public static DormTransferPost getPostById(int id)
    {
        DormTransferPost post = null;
        try
        {
            String query = "SELECT * FROM university_sync.dorm_transfer_post WHERE dorm_transfer_post_id=? ";
            PreparedStatement st = DBConnectionManager.getConnection().prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            post = new DormTransferPost(
                    rs.getInt("dorm_transfer_post_id"),
                    rs.getInt("owner_student_id"),
                    rs.getString("main_text"),
                    rs.getDate("creation_date"),
                    rs.getDate("last_edit_date"),
                    rs.getString("heading"),
                    rs.getInt("posted_room_id")
            );
            return post;
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

    /**
     * Deletes from the dorm_transfer_post table if possible.
     * The post to be deleted is indicated by the given integer id.
     * @param id
     * @return if the deletion is done or not. It cannot be done if
     * the given id doesn't correspond to any instance in the table.
     */
    public static boolean delete(int id)
    {
        try
        {
            String query = "DELETE FROM university_sync.dorm_transfer_post WHERE dorm_transfer_post_id= ?";
            PreparedStatement ps = DBConnectionManager.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            return false;
        }
    }

    /**
     * Edit the main text of the dorm_transfer_post identified
     * by the given integer id. New main_text will be {@code editedText}
     * @param id
     * @param editedText
     * @return if the edit was successful or not.
     */
    public static boolean edit(int id, String editedText)
    {
        String query = "UPDATE university_sync.forum_post SET main_text= ? WHERE forum_post_id= ?";
        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setString(1, editedText);
            pst.setInt(2, id);
            pst.executeQuery();
            return true;
        }
        catch (SQLException sqle)
        {
            return false;
        }
    }

    /**
     * Adds the given DormTransferPost object to database.
     * @param post
     * @return whether the adding operation was successful or not.
     */
    public static int addDormTransferPost(DormTransferPost post)
    {
        String query = "INSERT INTO university_sync.dorm_transfer_post (dorm_transfer_post_id, owner_student_id, creation_date, last_edit_date, heading, main_text, posted_room_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, post.getUserContentItemId());
            pst.setInt(2, post.getOwnerId());
            pst.setDate(3, new Date(post.getCreationDate().getTime()));
            pst.setDate(4, new Date(post.getLastEditDate().getTime()));
            pst.setString(5, post.getHeading());
            pst.setString(6, post.getMainText());
            pst.setInt(7, post.getRoomId());
            pst.executeUpdate();
            return post.getUserContentItemId();
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return -1;
        }
    }

    /**
     * Selects the {@code DormTransferPost} instances of the given room type,
     * which is indicated by integer id. 
     * @param roomTypeId
     * @return the ArrayList of {@code DormTransferPost} instances
     * with the given room type id.
     */
    public static ArrayList<DormTransferPost> getDormTransferPostOfRoomType(int roomTypeId)
    {
        ArrayList<DormTransferPost> posts = new ArrayList<DormTransferPost>();
        DormTransferPost post = null;
        try
        {
            String query = "SELECT * FROM university_sync.dorm_transfer_post WHERE posted_room_id=? ";
            PreparedStatement st = DBConnectionManager.getConnection().prepareStatement(query);
            st.setInt(1, roomTypeId);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                post = new DormTransferPost(
                        rs.getInt("dorm_transfer_post_id"),
                        rs.getInt("owner_student_id"),
                        rs.getString("main_text"),
                        rs.getDate("creation_date"),
                        rs.getDate("last_edit_date"),
                        rs.getString("heading"),
                        rs.getInt("posted_room_id")
                );
                posts.add(post);
            }
            return posts;
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return null;
        }
    }
}
