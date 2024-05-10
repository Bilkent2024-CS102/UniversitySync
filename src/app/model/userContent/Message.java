package app.model.userContent;

import app.model.User;

public class Message extends UserContentItem {

    private int senderId;
    private int receiverId;

    public Message(int senderId, int receiverId, String text)
    {
        super(senderId, text);
        setReceiverId(receiverId);
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }
}
