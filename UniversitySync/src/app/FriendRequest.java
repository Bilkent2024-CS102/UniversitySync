package app;

public class FriendRequest {
    
    private static int numberOfInstances;

    private int friendRequestId;
    private User sender;
    private User receiver;

    /*
     * Getters
     */
    public int getFriendRequestId()
    {
        return friendRequestId;
    }
    public User getSender()
    {
        return sender;
    }
    public User getReceiver()
    {
        return receiver;
    }

    /*
     * TODO add validation
     */
    public void setSender(User sender)
    {
        this.sender = sender;
    }
    public void setReceiver(User receiver)
    {
        this.receiver = receiver;
    }

    /*
     * Methods
     * TODO Implement the methods
     */
    public void acceptRequest()
    {

    }

    public void rejectRequest()
    {

    }
}
