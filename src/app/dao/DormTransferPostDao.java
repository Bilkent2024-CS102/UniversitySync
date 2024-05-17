package app.dao;

import app.model.userContent.post.DormTransferPost;

import java.sql.*;
import java.util.ArrayList;

public class DormTransferPostDao {

    /**
     * @TESTED
     * Takes integer id and makes a 'SELECT' query.
     * @param id id of the dorm transfer post in the database
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
                    rs.getTimestamp("creation_date"),
                    rs.getTimestamp("last_edit_date"),
                    rs.getString("heading"),
                    rs.getInt("posted_room_id")
            );
            return post;
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

    public static ArrayList<DormTransferPost> getAllPosts()
    {
        ArrayList<DormTransferPost> posts = new ArrayList<DormTransferPost>();
        try
        {
            String query = "SELECT * FROM university_sync.dorm_transfer_post";
            PreparedStatement st = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next())
            {
                DormTransferPost post = new DormTransferPost(
                        rs.getInt("dorm_transfer_post_id"),
                        rs.getInt("owner_student_id"),
                        rs.getString("main_text"),
                        rs.getTimestamp("creation_date"),
                        rs.getTimestamp("last_edit_date"),
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

    /**
     * @TESTED
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
     * @TESTED
     * Edit the main text of the dorm_transfer_post identified
     * by the given integer id. New main_text will be {@code editedText}
     * @param id
     * @param editedText
     * @return if the edit was successful or not.
     */
    public static boolean edit(int id, String editedText)
    {
        String query = "UPDATE university_sync.dorm_transfer_post SET main_text= ? WHERE dorm_transfer_post_id= ?";
        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setString(1, editedText);
            pst.setInt(2, id);
            pst.executeUpdate();
            return true;
        }
        catch (SQLException sqle)
        {
            return false;
        }
    }

    /**
     * @TESTED
     * Adds the given DormTransferPost object to database.
     * @param post
     * @return whether the adding operation was successful or not.
     */
    public static int addDormTransferPost(DormTransferPost post)
    {
        String query = "INSERT INTO university_sync.dorm_transfer_post " +
                "(owner_student_id, creation_date, last_edit_date, heading, main_text, posted_room_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, post.getOwnerId());
            pst.setTimestamp(2, new Timestamp(post.getCreationDate().getTime()));
            pst.setTimestamp(3, new Timestamp(post.getLastEditDate().getTime()));
            pst.setString(4, post.getHeading());
            pst.setString(5, post.getMainText());
            pst.setInt(6, post.getRoomId());
            pst.executeUpdate();

            Statement st = DBConnectionManager.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID();");
            rs.next();
            int idOfNewPost = rs.getInt(1);
            return idOfNewPost;
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return -1;
        }
    }

    /**
     * @TESTED
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
                        rs.getTimestamp("creation_date"),
                        rs.getTimestamp("last_edit_date"),
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
