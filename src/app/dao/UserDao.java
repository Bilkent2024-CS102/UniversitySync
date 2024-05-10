package app.dao;

import java.sql.*;
import java.util.ArrayList;
import app.model.User;
import app.model.FriendRequest;

import javax.swing.plaf.nimbus.State;

/**
 * database access class for user and user related data.
 */
public class UserDao {

    public static int addUser(User u){
        try{
            String query = "INSERT INTO university_sync.student (full_name, email, pass) VALUES (?, ? ,?);";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setString(1, u.getName());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getPassword());
            pst.executeUpdate();
            Statement st = DBConnectionManager.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
            rs.next();
            int idOfNewUser = rs.getInt(1);
            return idOfNewUser;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }
    }

    public static boolean updateUser(User u){
        try{
            String query = "UPDATE university_sync.student SET full_name = ?, email = ?, pass = ?, " +
                    "biography = ?, link_to_profile_picture = ?, student_major = ?, student_room_type_id = ?)" +
                    "WHERE student_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setString(1, u.getName());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getPassword());
            pst.setString(4, u.getBiography());
            pst.setString(5, u.getProfilePicturePath());
            pst.setString(6, u.getMajor());
            pst.setInt(7, u.getRoom().getRoomId());
            pst.executeUpdate();
            return true;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }

    public static ArrayList<FriendRequest> getFriendRequests(User u)
    {
        try
        {
            ArrayList<FriendRequest> friendRequests = new ArrayList<>();
            String query = "SELECT * FROM friend_requests WHERE receiver_id = ?";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, u.getUserId());
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                int requestId = rs.getInt("request_id");
                int senderId = rs.getInt("sender_id");
                int receiverId = rs.getInt("receiver_id");
                FriendRequest request = new FriendRequest(requestId, senderId, receiverId);
                friendRequests.add(request);
            }
            return friendRequests;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    //TODO Check if this works properly, this got a bit experimental
    public static ArrayList<User> getFriends(User u)
    {
        try
        {
            ArrayList<User> friends = new ArrayList<>();
            int id = u.getUserId();
            String query = "SELECT * FROM student_friendship WHERE first_student_id = ? OR second_student_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, id);
            pst.setInt(2, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                int firstID = rs.getInt("first_student_id");
                if (u.getUserId() == firstID)
                {
                    friends.add(getUserById(firstID));
                }
                else
                {
                    friends.add(getUserById(rs.getInt("second_student_id")));
                }
            }
            return friends;
        }

        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<User> getUsers()
    {
        String query = "SELECT * FROM student";
        ArrayList<User> students = new ArrayList<>();

        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                User u = new User(resultSet.getInt("student_id"), resultSet.getString("full_name"),
                                resultSet.getString("email"), resultSet.getString("pass"));
                students.add(u);
            }
            resultSet.close();
            pst.close();
            return students;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }

    /*
     * Retrieves user object from DB, matching 
     */
    public static User getUserById(int ID)
    {
        try
        {
            String query = "SELECT * FROM student WHERE student_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, ID);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            User u = new User(resultSet.getInt("student_id"), resultSet.getString("full_name"),
                    resultSet.getString("email"), resultSet.getString("pass"));
            return u;
        }
        catch (SQLException sqle)
        {
            System.out.println("No such user with id " + ID + " exists!");
            return null;
        }
    }

    public static boolean addFriend(int u1, int u2){

        if(isFriend(u1, u2)){
            System.out.println("they are friends");
            return false;
        }
        try{
            String query = "INSERT INTO university_sync.student_friendship (first_student_id, second_student_id) VALUES (? ,?);";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            int firstId = u1;
            int secondId = u2;
            if (firstId == secondId){
                return false;
            }
            pst.setInt(1, Math.min(firstId, secondId));
            pst.setInt(2, Math.max(firstId, secondId));
            pst.executeUpdate();

            return true;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }

    public static boolean isFriend(int u1, int u2){
        try{
            String query = "SELECT * FROM university_sync.student_friendship WHERE first_student_id = ? AND second_student_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            int firstId = u1;
            int secondId = u2;
            if (firstId == secondId){
                return false;
            }
            pst.setInt(1, Math.min(firstId, secondId));
            pst.setInt(2, Math.max(firstId, secondId));
            ResultSet rs = pst.executeQuery();
            boolean isFriend = false;
            while (rs.next()){
                isFriend = true;
            }
            return isFriend;

        }catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }

    public static boolean removeFriend(int u1, int u2){

        if(!isFriend(u1, u2)){
            System.out.println("they are not friends");
            return false;
        }
        try{
            String query = "DELETE FROM university_sync.student_friendship WHERE (first_student_id = ? AND second_student_id = ?)" +
                    " OR (first_student_id = ? AND second_student_id = ?);";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);

            pst.setInt(1, u1);
            pst.setInt(2, u2);
            pst.setInt(3, u2);
            pst.setInt(4, u1);
            pst.executeUpdate();

            return true;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }

    public static boolean addFriendRequest(int sender, int receiver){

        if(isFriend(sender, receiver)){
            return false;
        }
        try {
            String query = "INSERT INTO university_sync.friend_request (sender_id, receiver_id) VALUES (?, ?)";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            int firstId = sender;
            int secondId = receiver;
            if (firstId == secondId){
                return false;
            }
            pst.setInt(1, Math.min(firstId, secondId));
            pst.setInt(2, Math.max(firstId, secondId));
            pst.executeUpdate();
            return true;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public static boolean concludeFriendRequest(int sender, int receiver, boolean isAccepted){

        try {
            String query = "DELETE FROM university_sync.friend_request WHERE sender_id = ? AND receiver_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, sender);
            pst.setInt(2, receiver);
            pst.executeUpdate();
            if (isAccepted){
                return addFriend(sender, receiver);
            }
            return true;

        } catch (SQLException sqle) {
            System.out.println("Unable to conclude friend request/no such friend request!");
            return false;
        }
    }

    public static boolean authenticate(String email, String password){
        try
        {
            String query = "SELECT (email, pass) FROM student WHERE email = ? AND pass = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet resultSet = pst.executeQuery();
            
            if (resultSet.next())
            {
                return true;
            }
            return false;
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            return false;
        }
    }
    
    public static User getUserByEmail(String email){
        try
        {
            String query = "SELECT * FROM student WHERE email = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setString(1, email);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            User u = new User(resultSet.getInt("student_id"), resultSet.getString("full_name"),
                                resultSet.getString("email"), resultSet.getString("pass"));
            return u;
        }
        catch (SQLException sqle)
        {
            System.out.println("Such user does not exist!");
            return null;
        }
    }
}
