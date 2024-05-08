package app.dao;

import app.model.userContent.post.EventPost;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EventPostDao {

    public static int addEvent(EventPost post) {
        try{
            String query = "INSERT INTO university_sync.event_post (owner_student_id, creation_date, last_edit_date," +
                    "heading, main_text, location, event_date) VALUES (?, ? ,?, ?, ?, ?, ?);";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, post.getOwnerId());
            pst.setDate(2, new java.sql.Date(post.getCreationDate().getTime()));
            pst.setDate(3, new java.sql.Date(post.getLastEditDate().getTime()));
            pst.setString(4, post.getHeading());
            pst.setString(5, post.getMainText());
            pst.setString(6, post.getLocation());
            pst.setDate(7, new java.sql.Date(post.getEventDate().getTime()));
            pst.executeUpdate();
            Statement st = DBConnectionManager.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
            rs.next();
            int idOfNewEvent = rs.getInt(1);
            return idOfNewEvent;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }
    }

    public static boolean addFollower(int eventId, int userId){
        try {
            String query = "INSERT INTO university_sync.follow_event_post (followed_by_student_id, followed_event_post_id) VALUES (?, ?)";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);

            pst.setInt(1, userId);
            pst.setInt(2, eventId);
            pst.executeUpdate();
            return true;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public static boolean removeFollower(int eventId, int userId){
        try {
            String query = "DELETE FROM university_sync.follow_event_post WHERE followed_by_student_id=? AND followed_event_post_id=?";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, userId);
            pst.setInt(2, eventId);
            pst.executeUpdate();
            return true;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }
}