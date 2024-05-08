package app.model;

public class FriendRequest {
    
    private static int numberOfInstances;

    private int friendRequestId;
    private User sender;
    private User receiver;

    public FriendRequest(int friendRequestId, User sender, User receiver)
    {
        setFriendRequestId(friendRequestId);
        setSender(sender);
        setReceiver(receiver);
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
    public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }
    public User getReceiver() {
        return receiver;
    }
    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    
}
