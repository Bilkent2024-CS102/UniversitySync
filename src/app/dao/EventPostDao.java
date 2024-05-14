package app.dao;

import app.model.FriendRequest;
import app.model.User;
import app.model.userContent.post.EventPost;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class EventPostDao
{
    /**
     * @TESTED
     * @return all  {@code EventPost} instances by order of recency.
     */
    public static ArrayList<EventPost> getAllEventPosts()
    {
        ArrayList<EventPost> posts = new ArrayList<EventPost>();
        EventPost post = null;
        try
        {
            String query = "SELECT * FROM university_sync.event_post ORDER BY creation_date DESC";
            PreparedStatement st = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                post = new EventPost(
                        rs.getInt("event_post_id"),
                        rs.getInt("owner_student_id"),
                        rs.getString("main_text"),
                        rs.getTimestamp("creation_date"),
                        rs.getTimestamp("last_edit_date"),
                        rs.getString("heading"),
                        rs.getString("location"),
                        rs.getDate("event_date")
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
     * Add the given  {@code EventPost} instance to the database table.
     * @param post the {@code EventPost} instance.
     * @return the id of the added  {@code EventPost} instance.
     * If addition was not successful return -1.
     */
    public static int addEvent(EventPost post) {
        try{
            String query = "INSERT INTO university_sync.event_post (owner_student_id, creation_date, last_edit_date," +
                    "heading, main_text, location, event_date) VALUES (?, ? ,?, ?, ?, ?, ?);";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, post.getOwnerId());
            pst.setTimestamp(2, new java.sql.Timestamp(post.getCreationDate().getTime()));
            pst.setTimestamp(3, new java.sql.Timestamp(post.getLastEditDate().getTime()));
            pst.setString(4, post.getHeading());
            pst.setString(5, post.getMainText());
            pst.setString(6, post.getLocation());
            pst.setTimestamp(7, new java.sql.Timestamp(post.getEventDate().getTime()));
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

    /**
     * @TESTED
     * @param eventPostId is the id of the event post to be deleted.
     * @return Whether the deletion was successful or not.
     */
    public static boolean deleteEvent(int eventPostId)
    {
        try {

            //first remove all followers otherwise it causes sql exception
            String query = "DELETE FROM university_sync.follow_event_post WHERE followed_event_post_id=?";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, eventPostId);
            pst.executeUpdate();

            query = "DELETE FROM university_sync.event_post WHERE event_post_id=?";
            pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, eventPostId);
            pst.executeUpdate();
            return true;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    /**
     * MAYBE TODO.
     * @param editedText is the new text.
     * @return Whether the deletion was successful or not.
     */
    public static boolean editEvent(String editedText)
    {
        return false;
    }

    /**
     * @TESTED
     * Add a {@code User} instance as follower of an {@code EventPost}.
     * @param eventId id of the {@code EventPost}
     * @param userId id of the {@code} User
     * @return Whether the insertion was successful or not.
     */
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

    /**
     * @TESTED
     * Removes the {@code User} instance follower
     * from the {@code EventPost} instance.
     * @param eventId id of the {@code EventPost}
     * @param userId id of the {@code} User
     * @return Whether the deletion was successful or not.
     */
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

    public static EventPost getEventPostById(int ID)
    {
        try
        {
            String query = "SELECT * FROM university_sync.event_post WHERE event_post_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, ID);
            ResultSet rs = pst.executeQuery();
            rs.next();
            EventPost u = new EventPost(rs.getInt("owner_student_id"), rs.getString("main_text"),
                    rs.getDate("creation_date"), rs.getDate("last_edit_date"), rs.getString("heading"),
                    rs.getString("location"), rs.getDate("event_date"));
            return u;
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            return null;
        }
    }

    /**
     * @TESTED
     * Checks if {@code User} instance is
     * following the {@code EventPost} instance
     * @param eventId id of the {@code EventPost}
     * @param userId id of the {@code User}
     * @return Boolean - true if user is following, false if not
     */
    public static boolean doesUserFollow(int eventId, int userId)
    {
        try
        {
            String query = "SELECT followed_by_student_id FROM university_sync.follow_event_post WHERE followed_event_post_id = ? AND followed_by_student_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, eventId);
            pst.setInt(2, userId);

            ResultSet rs = pst.executeQuery();

            boolean isFollowing = false;
            while (rs.next()){
                isFollowing = true;
            }
            return isFollowing;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @NOT TESTED
     *
     * Returns event posts a specific user is following
     * @param userId id of the user
     * @return ArrayList of EventPost objects (that the user is following)
     */
    public static ArrayList<EventPost> getEventsOfUser(int userId)
    {
        try
        {
            ArrayList<EventPost> events = new ArrayList<>();
            String query = "SELECT followed_event_post_id FROM university_sync.follow_event_post WHERE followed_by_student_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1,userId);
            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                EventPost event = getEventPostById(rs.getInt("followed_event_post_id"));
                events.add(event);
            }
            return events;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}