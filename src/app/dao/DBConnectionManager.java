package app.dao; //TODO: be sure to correct this

import java.sql.*;
import java.util.*;

/**
 * DBConnectionManager
 */
public class DBConnectionManager {

    private static Connection con;

    public static void initializeConnection(String connectionURL, String mySQLUsername, String mySQLPassword) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(connectionURL, mySQLUsername, mySQLPassword);
    }

    public static final Connection getConnection(){
        return con;
    }
}