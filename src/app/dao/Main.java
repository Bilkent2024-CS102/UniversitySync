package app.dao; // TODO: be sure to correct this!

import app.model.User;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception{
        String url = "jdbc:mysql://localhost:3306/university_sync";
        String username = "root";
        String password = "ben123**AA"; // TODO: type your own mysql server password here!
        /*
        Class.forName("com.mysql.cj.jdbc.Driver");
        //DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        
        ResultSet students = st.executeQuery("SELECT * FROM university_sync.student");
        while (students.next()){
            System.out.print(students.getInt("id"));
            System.out.print(" " + students.getString("full_name"));
            System.out.print(" " + students.getString("username"));
            System.out.print(" " + students.getString("pass"));
            System.out.print(" " + students.getString("biography"));
            System.out.println(" " + students.getString("major"));
            
        }*/

        DBConnectionManager.initializeConnection(url, username, password);

        //User u = new User("hahahaha", "hahahahaha@gmail.com", "pass12345");
        //UserDao.addUser(u);
        ArrayList<User> users = UserDao.getUsers();
        System.out.println(UserDao.concludeFriendRequest(users.get(0), users.get(3), false));
        System.out.println("lalalaal");
    }
}