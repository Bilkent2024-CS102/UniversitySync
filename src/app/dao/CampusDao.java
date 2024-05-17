package app.dao;

import app.model.Campus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CampusDao{

    /**
     * Returns a campus object from db with ID
     *
     * @param Id id of the campus desired
     * @return Campus matching with id input
     */
    public static Campus getCampusById(int Id)
    {
        try
        {
            String query = "SELECT * FROM university_sync.campus WHERE campus_id = ?;";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, Id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            Campus c = new Campus(rs.getInt("campus_id"), rs.getString("campus_name"), null, null);
            return c;
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
            return null;
        }
    }
}
