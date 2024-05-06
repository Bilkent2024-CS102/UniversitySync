package app.model.location;

import app.model.Campus;
import app.model.userContent.Review;

import java.util.ArrayList;

public class Dormitory extends Location{
    private ArrayList<Room> rooms;

    public Dormitory (int id, ArrayList<Review> revs, String imagePath, String n, String desc, double rate, Campus c, ArrayList<Room> rooms){
        super(id, revs, imagePath, n, desc, rate, c);
        setRooms(rooms);
    }
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
