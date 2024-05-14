package app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import app.model.User;
import app.model.FriendRequest;

/**
 * The UserDao class provides methods for accessing user-related data in the database.
 */
public class UserDao {

    /**
     * @TESTED
     * Adds a user to the database.
     *
     * @param u The user to be added.
     * @return The ID of the newly added user, or -1 if an error occurred.
     */
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

    /**
     * @TESTED
     * Updates user information in the database.
     *
     * @param u The user with updated information.
     * @return True if the update was successful, false otherwise.
     */
    public static boolean updateUser(User u){
        try{
            String query = "UPDATE university_sync.student SET full_name = ?, email = ?, pass = ?, " +
                    "biography = ?, link_to_profile_picture = ?, student_major = ?, student_room_type_id = ? " +
                    "WHERE student_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setString(1, u.getName());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getPassword());
            pst.setString(4, u.getBiography());
            pst.setString(5, u.getProfilePicturePath());
            pst.setString(6, u.getMajor());
            if (u.getRoom() == null)
            {
                pst.setNull(7 , Types.INTEGER);
            }
            else{
                pst.setInt(7, u.getRoom().getRoomId());
            }
            pst.setInt(8, u.getUserId());
            pst.executeUpdate();
            return true;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }

    /**
     * @TESTED
     * Retrieves friend requests to a given user.
     *
     * @param receiverId The ID of the user who received the friend requests.
     * @return An ArrayList containing the friend requests received by the user.
     */
    public static ArrayList<FriendRequest> getFriendRequestsTo(int receiverId)
    {
        try
        {
            ArrayList<FriendRequest> friendRequests = new ArrayList<>();
            String query = "SELECT * FROM university_sync.friend_request WHERE receiver_id = ?";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, receiverId);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                FriendRequest request = new FriendRequest(rs.getInt("sender_id"), receiverId);
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

    /**
     * @TESTED
     * Retrieves friends of a given user.
     *
     * @param userId The ID of the user whose friends are to be retrieved.
     * @return An ArrayList containing the friends of the user.
     */
    public static ArrayList<User> getFriends(int userId)
    {
        try
        {
            ArrayList<User> friends = new ArrayList<>();
            String query = "SELECT * FROM university_sync.student_friendship WHERE first_student_id = ? OR second_student_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, userId);
            pst.setInt(2, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                int firstID = rs.getInt("first_student_id");
                if (userId == firstID)
                {
                    friends.add(getUserById(rs.getInt("second_student_id")));
                }
                else
                {
                    friends.add(getUserById(firstID));
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

    /**
     *  @TESTED
     * Retrieves all users from the database.
     *
     * @return An ArrayList containing all users in the database.
     */
    public static ArrayList<User> getUsers()
    {
        String query = "SELECT * FROM university_sync.student";
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

    /**
     * @TESTED
     * Retrieves a user by their ID from the database.
     *
     * @param ID The ID of the user to retrieve.
     * @return The User object corresponding to the given ID, or null if no such user exists.
     */
    public static User getUserById(int ID)
    {
        try
        {
            String query = "SELECT * FROM university_sync.student WHERE student_id = ?;";
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

    /**
     * @TESTED
     * Adds a friendship between two users in the database.
     *
     * @param u1 The ID of the first user.
     * @param u2 The ID of the second user.
     * @return True if the friendship was added successfully, false otherwise.
     */
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

    /**
     * @TESTED
     * Checks if two users are friends.
     *
     * @param u1 The ID of the first user.
     * @param u2 The ID of the second user.
     * @return True if the users are friends, false otherwise.
     */
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

    /**
     * @TESTED
     * Removes a friendship between two users from the database.
     *
     * @param u1 The ID of the first user.
     * @param u2 The ID of the second user.
     * @return True if the friendship was removed successfully, false otherwise.
     */
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

    /**
     * @TESTED
     * Adds a friend request to the database.
     *
     * @param fr The friend request to be added.
     * @return The ID of the newly added friend request, or -1 if an error occurred.
     */
    public static boolean addFriendRequest(FriendRequest fr){

        try {
            String query = "INSERT INTO university_sync.friend_request (sender_id, receiver_id) VALUES (?, ?)";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            int firstId = fr.getSenderId();
            int secondId = fr.getReceiverId();
            if (isFriend(firstId, secondId) || friendRequestExist(firstId, secondId) || firstId == secondId){
                return false;
            }
            pst.setInt(1, firstId);
            pst.setInt(2, secondId);
            pst.executeUpdate();

            Statement st = DBConnectionManager.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
            rs.next();
            int idOfNewFriendRequest = rs.getInt(1);
            return true;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    /**
     * @TESTED
     * Concludes a friend request by either accepting or rejecting it.
     *
     * @param sender    The ID of the sender of the friend request.
     * @param receiver  The ID of the receiver of the friend request.
     * @param isAccepted True if the friend request is accepted, false otherwise.
     * @return True if the friend request was concluded successfully, false otherwise.
     */
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

    /**
     * @TESTED
     * Authenticates a user by checking their email and password against the database records.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return True if the user is authenticated, false otherwise.
     */
    public static boolean authenticate(String email, String password){
        try
        {
            String query = "SELECT email, pass FROM university_sync.student WHERE email = ? AND pass = ?;";
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

    /**
     * @TESTED
     * Retrieves a user by their email from the database.
     *
     * @param email The email of the user to retrieve.
     * @return The User object corresponding to the given email, or null if no such user exists.
     */
    public static User getUserByEmail(String email){
        try
        {
            String query = "SELECT * FROM university_sync.student WHERE email = ?;";
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

    /**
     * adds a request to admin according to the field in the profile page
     * @param userId id of the user that submitted the request
     * @param text the text of the suggestion
     * @return whether the operation is successful
     */
    public static boolean addRequestToAdmin(int userId, String text){
        try{
            String query = "INSERT INTO university_sync.request_to_admin (created_by_student_id, request_text, request_date)" +
                    " VALUES (? ,?, ?);";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, userId);
            pst.setString(2, text);
            pst.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
            pst.executeUpdate();
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * TESTED
     *
     * Checks if a friend request exists between users
     * @param senderId id of the sender
     * @param receiverId id of the receiver
     * @return true boolean if it exists, false if not
     */
    public static boolean friendRequestExist(int senderId, int receiverId)
    {
        try
        {
            String query = "SELECT * FROM university_sync.friend_request WHERE sender_id = ? AND receiver_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, senderId);
            pst.setInt(2, receiverId);
            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {
                return true;
            }
            return false;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
