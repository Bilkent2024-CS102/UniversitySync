package app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import app.model.*;
import app.model.location.Location;
import app.model.userContent.Review;
import app.model.location.Dormitory;
import app.model.location.cafeteria.Cafeteria;


public class ReviewDao {

    //this method returns the database id of the newly inserted review
    //does that according to type of the Reviewable instance of the Review
    public static int addReview(Review r){
        try {
            String query = "INSERT INTO university_sync.review " +
                    "(owner_student_id, creation_date, last_edit_date, main_text, rating_given, review_to_location_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, r.getOwnerId());
            pst.setDate(2, new java.sql.Date(r.getCreationDate().getTime()));
            pst.setDate(3, new java.sql.Date(r.getLastEditDate().getTime()));
            pst.setString(4, r.getMainText());
            pst.setDouble(5, r.getRateGiven());
            pst.setInt(6, r.getReviewableId());

            pst.executeUpdate();

            Statement st = DBConnectionManager.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID();");
            rs.next();

            int idOfNewReview = rs.getInt(1);
            return idOfNewReview;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return 0;
        }
    }

    //TODO: this method will return whether the operation was successful
    public static boolean removeReview(int reviewId){
        String query = "DELETE FROM university_sync.review WHERE review_id= ?;";
        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, reviewId);
            int affectedRow = pst.executeUpdate();
            System.out.println(affectedRow);
            return true;
        }
        catch (SQLException sqle)
        {
            return false;
        }
    }

    /**
     * access to old review will be via id of currentReview
     * return whether the operation is successful
     * @param reviewId is the id of the review to be edited.
     * @param nexText is the new review text to be set.
     */
    public static boolean editReview(int reviewId, String nexText, double newRating){
        String query = "UPDATE university_sync.review SET main_text = ?, rating_given = ?, last_edit_date = ? WHERE review_id= ?;";
        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setString(1, nexText);
            pst.setFloat(2, (float) newRating);
            pst.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
            pst.setInt(4, reviewId);
            int affectedRow = pst.executeUpdate();
            System.out.println(affectedRow);
            return true;
        }
        catch (SQLException sqle)
        {
            return false;
        }
    }

    /**
     * @param reviewableId
     * @return the arraylist of reviews associated with the given reviewable object r.
     */
    public static ArrayList<Review> getReviewsOf(int reviewableId){
        ArrayList<Review> result = new ArrayList<Review>();
        String query = "";

        query = "SELECT * FROM university_sync.review WHERE review_to_location_id= ?";
        try
        {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, reviewableId);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next())
            {
                Review review = new Review(
                        resultSet.getInt("review_id"),
                        resultSet.getInt("owner_student_id"),
                        resultSet.getString("main_text"),
                        new Date(resultSet.getDate("creation_date").getTime()),
                        new Date(resultSet.getDate("last_edit_date").getTime()),
                        reviewableId,
                        resultSet.getDouble("rating_given")
                );

                result.add(review);
            }
            resultSet.close();
            pst.close();
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }

        return result;
    }
}
