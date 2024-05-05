package app.dao; //TODO: be sure to correct this

import java.sql.*;
import java.util.ArrayList;
import app.model.User;

import javax.swing.plaf.nimbus.State;

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
            System.out.println(idOfNewUser);
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
                                //TODO DB columns don't match with fields in java and vice versa!
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

    public static boolean authenticate(String email, String password){
        return true;
    }

    public static User getUserByEmail(String email){
        return null;
    }
}
