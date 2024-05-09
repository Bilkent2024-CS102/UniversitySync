package app.dao;

import app.controller.SessionManager;
import app.model.userContent.Reply;
import app.model.userContent.post.DormTransferPost;
import app.model.userContent.post.ForumPost;

import java.sql.*;
import java.util.ArrayList;

public class DormTransferPostDao {
    public static DormTransferPost getPostById(int id)
    {
        DormTransferPost post = null;
        try
        {
            String query = "SELECT * FROM university_sync.dorm_transfer_post WHERE dorm_transfer_post_id= ? ";
            PreparedStatement st = DBConnectionManager.getConnection().prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            post = new DormTransferPost(rs.getInt("dorm_transfer_post_id"), rs.getInt("owner_student_id"),
                    rs.getString("main_text"),
                    rs.getDate("creation_date"), rs.getDate("last_edit_date"),
                    rs.getString("heading"), rs.getInt("posted_room_id"));
            return post;
        } catch (Exception sqle) {
            sqle.printStackTrace();
            return null;
        }
    }

    public static boolean delete(int postId)
    {
        try
        {
            String query = "DELETE FROM university_sync.dorm_transfer_post WHERE dorm_transfer_post_id= ?";
            PreparedStatement ps = DBConnectionManager.getConnection().prepareStatement(query);
            ps.setInt(1, postId);
            ps.executeUpdate();
            return true;
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            return false;
        }
    }

    public static boolean edit(int postId, String editedText)
    {
        String query = "UPDATE university_sync.forum_post SET main_text= ? WHERE forum_post_id= ?";
        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setString(1, editedText);
            pst.setInt(2, postId);
            pst.executeQuery();
            return true;
        }
        catch (SQLException sqle)
        {
            return false;
        }
    }

    public static int addDormTransferPost(DormTransferPost d){
        return -1;
    }

    public static ArrayList<DormTransferPost> getDormTransferPostOfRoomType(int roomTypeId){
        return null;
    }
}
