package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class creates the required connection to MySQL database
 */
public class DBConnectionManager {

    private static Connection con;

    /**
     * This method creates the required connection to MySQL database
     * @param connectionURL url of the server, in our application local
     * @param mySQLUsername MySQL username
     * @param mySQLPassword MySQL password
     * @throws Exception in case wrong credentials
     */
    public static void initializeConnection(String connectionURL, String mySQLUsername, String mySQLPassword) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(connectionURL, mySQLUsername, mySQLPassword);
    }

    public static final Connection getConnection(){
        return con;
    }
}