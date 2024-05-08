package app.dao;

import app.model.userContent.post.EventPost;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}