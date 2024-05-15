package app.model.location;

import app.dao.DormitoryDao;

public class Room {
    private static int numberOfInstances;
    private int dormitoryID;
    private int roomNo;
    private int capacity;
    private int roomId;
    private int availableBed;
    private boolean isBunked;
    private boolean privateBathroom;

    private String description;

    public Room(int id, int capacity, boolean bunk, boolean bathroom, String desc, int dormitoryID){
        setRoomId(id);
        setCapacity(capacity);
        setBunked(bunk);
        setPrivateBathroom(bathroom);
        setDescription(desc);
        setDormId(dormitoryID);
    }
    
    public static int getNumberOfInstances() {
        return numberOfInstances;
    }
    public int getRoomId() {
        return roomId;
    }
    public int getDormId() {
        return dormitoryID;
    }
    public int getRoomNo() {
        return roomNo;
    }
    public int getCapacity() {
        return capacity;
    }
    public boolean isPrivateBathroom() {
        return privateBathroom;
    }
    public int getAvailableBed() {
        return availableBed;
    }
    public boolean isBunked() {
        return isBunked;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    public static void setNumberOfInstances(int numberOfInstances) {
        Room.numberOfInstances = numberOfInstances;
    }
    public void setAvailableBed(int availableBed) {
        this.availableBed = availableBed;
    }
    public void setBunked(boolean isBunked) {
        this.isBunked = isBunked;
    }
    public void setPrivateBathroom(boolean privateBathroom) {
        this.privateBathroom = privateBathroom;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setDormId(int dormId) {
        this.dormitoryID = dormId;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        if (this.getRoomId() == 0){
            return "--no room--";
        }
        String retString = this.getCapacity() + " capacity, ";
        retString += (this.isBunked() ? "bunked, " : "flat bed, ");
        retString += (this.isPrivateBathroom() ? "with private bathroom, " : "");
        retString += "in the dormitory" + DormitoryDao.getDormitoryById(this.getDormId()).getName();
        return retString;
    }
}
