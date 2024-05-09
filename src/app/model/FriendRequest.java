package app.model;

public class FriendRequest {
    
    private static int numberOfInstances;

    private int friendRequestId;
    private int senderId;
    private int receiverId;

    public FriendRequest(int friendRequestId, int senderId, int receiverId)
    {
        setFriendRequestId(friendRequestId);
        setSenderId(senderId);
        setReceiver(receiverId);
    }
    
    public static int getNumberOfInstances() {
        return numberOfInstances;
    }
    public static void setNumberOfInstances(int numberOfInstances) {
        FriendRequest.numberOfInstances = numberOfInstances;
    }
    public int getFriendRequestId() {
        return friendRequestId;
    }
    public void setFriendRequestId(int friendRequestId) {
        this.friendRequestId = friendRequestId;
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
