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
     * @TESTED
     * Retrieves all dormitories from the database.
     *
     * @return An ArrayList containing all Dormitory instances.
     */
    public static ArrayList<Dormitory> getAllDormitories(){
        String query = "SELECT * FROM university_sync.dormitory ORDER BY dormitory_location_id";
        ArrayList<Dormitory> dormitories = new ArrayList<>();

        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Dormitory dorm = new Dormitory(resultSet.getInt("dormitory_location_id"), null, resultSet.getString("link_to_dormitory_picture"),
                        resultSet.getString("dorm_name"), resultSet.getString("dorm_description"), 0, null, null);
                dorm.setReviews(ReviewDao.getReviewsOf(dorm.getLocationId()));
                dorm.setRooms(DormitoryDao.getRoomTypesIn(dorm.getLocationId()));
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
     * @TESTED
     * Retrieves all dormitories from the database sorted by their average rating descending
     *
     * @return An ArrayList containing all Dormitory instances.
     */
    public static ArrayList<Dormitory> getAllDormitoriesByRating(){
        String query = "SELECT d.* " +
                "FROM university_sync.dormitory d " +
                "ORDER BY (SELECT AVG(rating_given) AS avg_rating FROM university_sync.review WHERE review_to_location_id = d.dormitory_location_id) DESC;";
        ArrayList<Dormitory> dormitories = new ArrayList<>();

        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Dormitory dorm = new Dormitory(resultSet.getInt("dormitory_location_id"), null,
                        resultSet.getString("link_to_dormitory_picture"),
                        resultSet.getString("dorm_name"), resultSet.getString("dorm_description"), 0, null, null);
                dorm.setReviews(ReviewDao.getReviewsOf(dorm.getLocationId()));
                dorm.setRooms(DormitoryDao.getRoomTypesIn(dorm.getLocationId()));
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
     * @TESTED
     * Retrieves a dormitory by its ID from the database.
     *
     * @param id The ID of the dormitory to retrieve.
     * @return The Dormitory object corresponding to the given ID, or null if no such dormitory exists.
     */
    public static Dormitory getDormitoryById(int id){
        String query = "SELECT * FROM university_sync.dormitory WHERE dormitory_location_id = ?";
        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            Dormitory dorm;
            resultSet.next();
            dorm = new Dormitory(resultSet.getInt("dormitory_location_id"), null, resultSet.getString("link_to_dormitory_picture"),
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
     * @TESTED
     * Retrieves room types in a dormitory from the database.
     *
     * @param dormId The ID of the dormitory.
     * @return An ArrayList containing all Room instances in the specified dormitory.
     */
    public static ArrayList<Room> getRoomTypesIn(int dormId){
        ArrayList<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM university_sync.room_type WHERE room_in_dormitory_id = ?";
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
