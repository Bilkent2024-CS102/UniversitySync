package app.model.userContent;

import app.dao.MessageDao;
import app.model.User;

public class Message extends UserContentItem {

    private int receiverId;

    /**
     * this constructor is used when a new message is to be inserted into database and id is assigned after
     * the insertion
     * @param senderId
     * @param receiverId
     * @param text
     */
    public Message(int senderId, int receiverId, String text)
    {
        super(senderId, text);
        setReceiverId(receiverId);
        int id = MessageDao.addMessage(this);
        setID(id);
    }

    /**
     * this constructor is for pulling the existing message from the database and when the id is known
     * @param id
     * @param senderId
     * @param receiverId
     * @param text
     */
    public Message(int id, int senderId, int receiverId, String text)
    {
        super(senderId, text);
        setID(id);
        setReceiverId(receiverId);
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getSenderId() {
        return this.getOwnerId();
    }

    public void setSenderId(int senderId) {
        this.setOwnerId(senderId);
    }
}
