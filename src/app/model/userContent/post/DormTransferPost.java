package app.model.userContent.post;

import app.model.location.Room;

public class DormTransferPost extends Post{
    Room room;

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
