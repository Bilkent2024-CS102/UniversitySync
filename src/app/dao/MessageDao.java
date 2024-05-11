package app.dao;

import app.model.userContent.Message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * The MessageDao class provides methods for interacting with messages in the database.
 */
public class MessageDao {

    /**
     * @TESTED
     * Adds a message to the database.
     *
     * @param message The message to be added.
     * @return The ID of the newly added message, or -1 if an error occurred.
     */
    public static int addMessage(Message message)
    {
        int sender = message.getSenderId();
        int receiver = message.getReceiverId();
        String text = message.getMainText();

        String query = "INSERT INTO university_sync.message (owner_student_id, receiver_student_id, creation_date, last_edit_date, main_text) VALUES (?, ? ,?, ?, ?);";
        try {
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, sender);
            pst.setInt(2, receiver);
            pst.setDate(3, new java.sql.Date(message.getCreationDate().getTime()));
            pst.setDate(4, new java.sql.Date(message.getLastEditDate().getTime()));
            pst.setString(5, text);

            pst.executeUpdate();

            //To return the last insert ID
            Statement st = DBConnectionManager.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()");
            rs.next();
            int id = rs.getInt(1);
            return id;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * @TESTED
     * Retrieves messages exchanged between two users.
     *
     * @param receiverID The ID of the message receiver.
     * @param senderID   The ID of the message sender.
     * @return An ArrayList containing the messages exchanged between the specified users.
     */
    public static ArrayList<Message> getMessagesBetween(int receiverID, int senderID)
    {
        try
        {
            ArrayList<Message> messages = new ArrayList<>();
            String query = "SELECT * FROM university_sync.message WHERE (owner_student_id = ? AND receiver_student_id = ?) OR (owner_student_id = ? AND receiver_student_id = ?);";
            PreparedStatement pst = DBConnectionManager.getConnection().prepareStatement(query);
            pst.setInt(1, senderID);
            pst.setInt(2, receiverID);
            pst.setInt(3, receiverID);
            pst.setInt(4, senderID);

            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next())
            {
                Message m = new Message(resultSet.getInt("owner_student_id"), resultSet.getInt("receiver_student_id"), resultSet.getString("main_text"));
                messages.add(m);
            }

            return messages;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
