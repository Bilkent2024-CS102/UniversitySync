package app.model;

import app.dao.UserDao;

public class FriendRequest {
    
    private static int numberOfInstances;

    private int senderId;
    private int receiverId;

    /**
     * this constructor adds newly created friend request to the database and assigns the auto generated id
     * @param senderId
     * @param receiverId
     */
    public FriendRequest(int senderId, int receiverId)
    {
        setSenderId(senderId);
        setReceiver(receiverId);
    }

    
    public static int getNumberOfInstances() {
        return numberOfInstances;
    }
    public static void setNumberOfInstances(int numberOfInstances) {
        FriendRequest.numberOfInstances = numberOfInstances;
    }

    public int getSenderId() {
        return senderId;
    }
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
    public int getReceiverId() {
        return receiverId;
    }
    public void setReceiver(int receiverId) {
        this.receiverId = receiverId;
    }

    
}
