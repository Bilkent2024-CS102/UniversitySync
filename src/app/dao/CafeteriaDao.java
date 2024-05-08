package app.dao;

import app.model.location.cafeteria.Cafeteria;
import app.model.location.cafeteria.MenuItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CafeteriaDao {
    public static ArrayList<Cafeteria> getAllCafeterias(){
        String query = "SELECT * FROM Cafeteria";
        ArrayList<Cafeteria> cafeterias = new ArrayList<>();

        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Cafeteria cafeteria = new Cafeteria(resultSet.getInt("cafeteria_id"), null, resultSet.getString("link_to_Cafeteria_picture"),
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

    public static Cafeteria getCafeteriaById(int id){
        String query = "SELECT * FROM cafeteria WHERE cafeteria_id = ?";
        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();
            Cafeteria cafe;
            resultSet.next();
            cafe = new Cafeteria(resultSet.getInt("cafeteria_id"), null, resultSet.getString("link_to_Cafeteria_picture"),
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

    public static ArrayList<MenuItem> getMenuItemsIn(int cafeteriaId){
        String query = "SELECT * FROM university_sync.menu_items WHERE cafeteria_id = ?";
        ArrayList<MenuItem> menuItems = new ArrayList<>();

        try{
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, cafeteriaId);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                MenuItem m = new MenuItem(resultSet.getInt("menu_item_in_cafeteria_id"), resultSet.getInt("menu_item_id"),
                        resultSet.getString("menu_item_name"), resultSet.getFloat("price"),
                        resultSet.getString("link_to_menu_item_picture"));
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
