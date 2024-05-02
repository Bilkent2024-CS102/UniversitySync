package app.dao; //TODO: be sure to correct this

import java.sql.*;

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
}
