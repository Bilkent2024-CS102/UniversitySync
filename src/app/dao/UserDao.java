package app.dao;

import java.sql.*;
import java.util.ArrayList;
import app.model.User;

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
    public static User getUserByID(int ID)
    {
        String query = "SELECT * FROM students WHERE student_id = ?;";
        PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
        pst.setInt(1, ID);
        ResultSet resultSet = pst.executeQuery();
        try
        {
            while (resultSet.next())
            {
                User u = new User(resultSet.getInt("student_id"), resultSet.getString("full_name"),
                                resultSet.getString("email"), resultSet.getString("pass"));
                return u;
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            return null;
        }
    }

    public static boolean addFriend(User u1, User u2){

        if(isFriend(u1, u2)){
            System.out.println("they are friends");
            return false;
        }
        try{
            String query = "INSERT INTO university_sync.student_friendship (first_student_id, second_student_id) VALUES (? ,?);";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            int firstId = u1.getUserId();
            int secondId = u2.getUserId();
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

    public static boolean isFriend(User u1, User u2){
        try{
            String query = "SELECT * FROM university_sync.student_friendship WHERE first_student_id = ? AND second_student_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            int firstId = u1.getUserId();
            int secondId = u2.getUserId();
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

    public static boolean addFriendRequest(User sender, User receiver){

        if(isFriend(sender, receiver)){
            return false;
        }
        try {
            String query = "INSERT INTO university_sync.friend_request (sender_id, receiver_id) VALUES (?, ?)";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            int firstId = sender.getUserId();
            int secondId = receiver.getUserId();
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

    public static boolean concludeFriendRequest(User sender, User receiver, boolean isAccepted){

        try {
            String query = "DELETE FROM university_sync.friend_request WHERE sender_id = ? AND receiver_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, sender.getUserId());
            pst.setInt(2, receiver.getUserId());
            pst.executeUpdate();
            if (isAccepted){
                return addFriend(sender, receiver);
            }
            return true;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    //TODO This might not work
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

    //TODO This should work but double-check
    public static User getUserByEmail(String email){
        try
        {
            String query = "SELECT * FROM student WHERE email = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setString(1, email);
            ResultSet resultSet = pst.executeQuery();
            User u = new User(resultSet.getInt("student_id"), resultSet.getString("full_name"),
                                resultSet.getString("email"), resultSet.getString("pass"));
            return u;
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            return null;
        }
    }

    public static User getUserById(int ownerStudentId) {
        return null;
    }
}
