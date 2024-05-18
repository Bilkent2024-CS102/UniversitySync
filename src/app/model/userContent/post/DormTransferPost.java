package app.model.userContent.post;

import app.dao.DormTransferPostDao;

import java.util.Date;

public class DormTransferPost extends Post{
    int roomId;

    /**
     * the constructor that is used when dorm transfer post is just created. this constructor inserts the
     * dorm transfer post to database and assigns
     * its database id
     * @param ownerId
     * @param text
     * @param creation
     * @param lastEdit
     * @param heading
     * @param roomId
     */
    public DormTransferPost(int ownerId, String text, Date creation, Date lastEdit, String heading, int roomId) {
        super(ownerId, text, creation, lastEdit, heading);
        setRoomId(roomId);
        int id = DormTransferPostDao.addDormTransferPost(this);
        setID(id);
    }

    /**
     * the constructor that is used when event post is just created. this constructor inserts the event post to database and assigns
     * its database id
     * @param ownerId
     * @param text
     * @param creation
     * @param lastEdit
     * @param heading
     * @param roomId
     */
    public DormTransferPost(int id, int ownerId, String text, Date creation, Date lastEdit, String heading, int roomId) {
        super(ownerId, text, creation, lastEdit, heading);
        setRoomId(roomId);
        setID(id);
    }

    /*
     * Getters
     */
    public int getRoomId()
    {
        return roomId;
    }

    /*
     * Setters
     */
    public void setRoomId(int roomId)
    {
        this.roomId = roomId;
    }
}
