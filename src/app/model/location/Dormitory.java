package app.model.location;

import app.model.Campus;
import app.model.userContent.Review;

import java.util.ArrayList;

public class Dormitory extends Location{
    private ArrayList<Room> rooms;

    public Dormitory (int id, ArrayList<Review> revs, String imagePath,
                      String name, String description, double rate, Campus campus, ArrayList<Room> rooms){
        super(id, revs, imagePath, name, description, rate, campus);
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

    public String toString(){
        return this.getName();
    }
}
