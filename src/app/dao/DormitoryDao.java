package app.dao;

import app.model.location.Dormitory;
import app.model.location.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The DormitoryDao class provides methods for accessing and managing dormitory data in the database.
 */
public class DormitoryDao {

    /**
     * Retrieves all dormitories from the database.
     *
     * @return An ArrayList containing all Dormitory instances.
     */
    public static ArrayList<Dormitory> getAllDormitories(){
        String query = "SELECT * FROM dormitory";
        ArrayList<Dormitory> dormitories = new ArrayList<>();

        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Dormitory dorm = new Dormitory(resultSet.getInt("dormitory_id"), null, resultSet.getString("link_to_dormitory_picture"),
                        resultSet.getString("dorm_name"), resultSet.getString("dorm_description"), 0, null, null);
                dormitories.add(dorm);
            }
            resultSet.close();
            pst.close();
            return dormitories;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves a dormitory by its ID from the database.
     *
     * @param id The ID of the dormitory to retrieve.
     * @return The Dormitory object corresponding to the given ID, or null if no such dormitory exists.
     */
    public static Dormitory getDormitoryById(int id){
        String query = "SELECT * FROM dormitory WHERE dormitory_id = ?";
        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            Dormitory dorm;
            resultSet.next();
            dorm = new Dormitory(resultSet.getInt("dormitory_id"), null, resultSet.getString("link_to_dormitory_picture"),
                        resultSet.getString("dorm_name"), resultSet.getString("dorm_description"), 0, null, null);
            dorm.setReviews(ReviewDao.getReviewsOf(id));
            dorm.setRooms(DormitoryDao.getRoomTypesIn(id));
            resultSet.close();
            pst.close();
            return dorm;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves room types in a dormitory from the database.
     *
     * @param dormId The ID of the dormitory.
     * @return An ArrayList containing all Room instances in the specified dormitory.
     */
    public static ArrayList<Room> getRoomTypesIn(int dormId){
        ArrayList<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM room_type WHERE room_in_dormitory_id = ?";
        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, dormId);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Room roomType = new Room(resultSet.getInt("room_type_id"), resultSet.getInt("capacity"), resultSet.getBoolean("is_bunk"),
                        resultSet.getBoolean("has_private_bathroom"), resultSet.getString("room_type_description"), dormId);
                rooms.add(roomType);
            }
            resultSet.close();
            pst.close();
            return rooms;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }
}
