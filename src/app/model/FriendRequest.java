package app.model;


public class FriendRequest {
    

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

    public int getSenderId() {
        return senderId;
    }
    public int getReceiverId() {
        return receiverId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
    public void setReceiver(int receiverId) {
        this.receiverId = receiverId;
    }
}
