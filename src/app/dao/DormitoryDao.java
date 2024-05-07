package app.dao;

import java.sql.*;
import java.util.ArrayList;

import app.model.User;
import app.model.location.Dormitory;
import app.model.location.Room;
import app.model.userContent.Review;

public class DormitoryDao {

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
            dorm.setReviews(ReviewDao.getReviewsOf(dorm));
            dorm.setRooms(DormitoryDao.getRoomTypesIn(dorm));
            resultSet.close();
            pst.close();
            return dorm;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Room> getRoomTypesIn(Dormitory d){
        int id = d.getLocationId();
        ArrayList<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM room_type WHERE room_in_dormitory_id = ?";
        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Room roomType = new Room(resultSet.getInt("room_type_id"), resultSet.getInt("capacity"), resultSet.getBoolean("is_bunk"),
                        resultSet.getBoolean("has_private_bathroom"), resultSet.getString("room_type_description"), d);
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
