package app.model.location;

import java.util.ArrayList;

public class Dormitory extends Location{
    private ArrayList<Room> rooms;

    /*
     * Getters
     */
    public ArrayList<Room> getRooms()
    {
        return rooms;
    }

    /*
     * Setters
     */
    public void setRooms(ArrayList<Room> rooms)
    {
        this.rooms = rooms;
    }
}
