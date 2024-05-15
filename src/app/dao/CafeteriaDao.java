package app.dao;

import app.model.location.Dormitory;
import app.model.location.cafeteria.Cafeteria;
import app.model.location.cafeteria.MenuItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The CafeteriaDao class provides methods for accessing and managing cafeteria data in the database.
 */
public class CafeteriaDao {

    /**
     * @TESTED
     * Retrieves all cafeterias from the database.
     *
     * @return An ArrayList containing all Cafeteria instances.
     */
    public static ArrayList<Cafeteria> getAllCafeterias(){
        String query = "SELECT * FROM university_sync.cafeteria";
        ArrayList<Cafeteria> cafeterias = new ArrayList<>();

        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Cafeteria cafeteria = new Cafeteria(resultSet.getInt("cafeteria_location_id"), null, resultSet.getString("link_to_cafeteria_picture"),
                        resultSet.getString("cafeteria_name"), resultSet.getString("cafeteria_description"), 0, null, null,
                        resultSet.getFloat("min_price"), resultSet.getFloat("max_price"));
                cafeterias.add(cafeteria);
            }
            resultSet.close();
            pst.close();
            return cafeterias;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }

    /**
     * @TESTED
     * Retrieves all cafeterias from the database sorted by their average rating descending
     *
     * @return An ArrayList containing all Dormitory instances.
     */
    public static ArrayList<Cafeteria> getAllCafeteriasByRating(){
        String query = "SELECT c.* " +
                "FROM university_sync.cafeteria c " +
                "ORDER BY (SELECT AVG(rating_given) AS avg_rating FROM university_sync.review WHERE review_to_location_id = c.cafeteria_location_id) DESC;";
        ArrayList<Cafeteria> cafeterias = new ArrayList<>();

        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Cafeteria cafe = new Cafeteria(resultSet.getInt("cafeteria_location_id"), null,
                        resultSet.getString("link_to_cafeteria_picture"),
                        resultSet.getString("cafeteria_name"), resultSet.getString("cafeteria_description"), 0, null, null,
                        resultSet.getDouble("min_price"), resultSet.getDouble("max_price"));
                cafe.setReviews(ReviewDao.getReviewsOf(cafe.getLocationId()));
                cafe.setItems(CafeteriaDao.getMenuItemsIn(cafe.getLocationId()));
                cafeterias.add(cafe);
            }
            resultSet.close();
            pst.close();
            return cafeterias;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }

    /**
     * @TESTED
     * Retrieves all cafeterias from the database sorted by their average rating descending
     *
     * @return An ArrayList containing all Dormitory instances.
     */
    public static ArrayList<Cafeteria> getAllCafeteriasByPrice(){
        String query = "SELECT c.* " +
                "FROM university_sync.cafeteria c " +
                "ORDER BY (SELECT (min_price+max_price)/2) ASC;";
        ArrayList<Cafeteria> cafeterias = new ArrayList<>();

        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Cafeteria cafe = new Cafeteria(resultSet.getInt("cafeteria_location_id"), null,
                        resultSet.getString("link_to_cafeteria_picture"),
                        resultSet.getString("cafeteria_name"), resultSet.getString("cafeteria_description"), 0, null, null,
                        resultSet.getDouble("min_price"), resultSet.getDouble("max_price"));
                cafe.setReviews(ReviewDao.getReviewsOf(cafe.getLocationId()));
                cafe.setItems(CafeteriaDao.getMenuItemsIn(cafe.getLocationId()));
                cafeterias.add(cafe);
            }
            resultSet.close();
            pst.close();
            return cafeterias;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }

    /**
     * TESTED
     * Retrieves a cafeteria by its ID from the database.
     *
     * @param id The ID of the cafeteria to retrieve.
     * @return The Cafeteria object corresponding to the given ID, or null if no such cafeteria exists.
     */
    public static Cafeteria getCafeteriaById(int id){
        String query = "SELECT * FROM university_sync.cafeteria WHERE cafeteria_location_id = ?";
        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            Cafeteria cafe;
            resultSet.next();
            cafe = new Cafeteria(resultSet.getInt("cafeteria_location_id"), null, resultSet.getString("link_to_Cafeteria_picture"),
                    resultSet.getString("cafeteria_name"), resultSet.getString("cafeteria_description"), 0, null, null,
                    resultSet.getFloat("min_price"), resultSet.getFloat("max_price"));
            resultSet.close();
            pst.close();
            return cafe;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }

    /**
     * TESTED
     * Retrieves menu items in a cafeteria from the database.
     *
     * @param cafeteriaId The ID of the cafeteria.
     * @return An ArrayList containing all MenuItem instances in the specified cafeteria.
     */
    public static ArrayList<MenuItem> getMenuItemsIn(int cafeteriaId){
        String query = "SELECT * FROM university_sync.menu_item WHERE menu_item_in_cafeteria_id = ?";
        ArrayList<MenuItem> menuItems = new ArrayList<>();

        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, cafeteriaId);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                MenuItem m = new MenuItem(resultSet.getInt("menu_item_in_cafeteria_id"), resultSet.getInt("menu_item_id"),
                        resultSet.getString("menu_item_name"), resultSet.getFloat("price"),
                        "");
                menuItems.add(m);
            }
            resultSet.close();
            pst.close();
            return menuItems;
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }
}
