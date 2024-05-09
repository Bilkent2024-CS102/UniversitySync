package app.model.userContent.post;

import app.dao.DormTransferPostDao;
import app.model.User;
import app.model.location.Room;

import java.util.ArrayList;
import java.util.Date;

public class DormTransferPost extends Post{
    int roomId;

    /**
     * the constructor that is used when dorm transfer post is just created. this constructor inserts the
     * dorm transfer post to database and assigns
     * its database id
     * @param own
     * @param text
     * @param creation
     * @param lastEdit
     * @param heading
     * @param roomId
     */
    public DormTransferPost(int own, String text, Date creation, Date lastEdit, String heading, int roomId) {
        super(own, text, creation, lastEdit, heading);
        setRoomId(roomId);
        int id = DormTransferPostDao.addDormTransferPost(this);
        setID(id);
    }

    /**
     * the constructor that is used when event post is just created. this constructor inserts the event post to database and assigns
     * its database id
     * @param own
     * @param text
     * @param creation
     * @param lastEdit
     * @param heading
     * @param roomId
     */
    public DormTransferPost(int id, int own, String text, Date creation, Date lastEdit, String heading, int roomId) {
        super(own, text, creation, lastEdit, heading);
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
