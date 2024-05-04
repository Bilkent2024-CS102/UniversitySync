package app.dao; //TODO: be sure to correct this

import java.sql.*;
import java.util.ArrayList;
import app.model.User;

public class UserDAO {

    public static boolean addUser(String name, int majorID){
        try{
            String query = "INSERT INTO university_sync.student (student_name, student_major_id) VALUES (?, ?);";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setString(1, name);
            pst.setInt(2, majorID);
            pst.executeUpdate();
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
        return true;
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
                                //TODO DB columns don't match with fields in java and vice versa!
                students.add(u);
            }
            resultSet.close();
            pst.close();
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
}
