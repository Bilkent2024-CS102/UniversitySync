package app.model.userContent.post;

import app.model.User;
import app.model.location.Room;

import java.util.ArrayList;
import java.util.Date;

public class DormTransferPost extends Post{
    Room room;

    /**
     * the constructor that is used when dorm transfer post is just created. this constructor inserts the
     * dorm transfer post to database and assigns
     * its database id
     * @param own
     * @param text
     * @param creation
     * @param lastEdit
     * @param heading
     * @param room
     */
    public DormTransferPost(User own, String text, Date creation, Date lastEdit, String heading, Room room) {
        super(own, text, creation, lastEdit, heading);
        setRoom(room);
        int id = DormTransferPostDao.addEvent(this);
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
     * @param room
     */
    public DormTransferPost(int id, User own, String text, Date creation, Date lastEdit, String heading, Room room) {
        super(own, text, creation, lastEdit, heading);
        setRoom(room);
        setID(id);
    }

    /*
     * Getters
     */
    public Room getRoom()
    {
        return room;
    }

    /*
     * Setters
     */
    public void setRoom(Room room)
    {
        this.room = room;
    }
}
