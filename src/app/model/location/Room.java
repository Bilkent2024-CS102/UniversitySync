package app.model.location;

public class Room {
    private static int numberOfInstances;
    private final Dormitory dorm;
    private final int roomNo;
    private final int capacity;
    private int roomId;
    private int availableBed;
    private boolean isBunked;
    private boolean privateBathroom;
    
    public static int getNumberOfInstances() {
        return numberOfInstances;
    }
    public static void setNumberOfInstances(int numberOfInstances) {
        Room.numberOfInstances = numberOfInstances;
    }
    public Dormitory getDorm() {
        return dorm;
    }
    public int getRoomNo() {
        return roomNo;
    }
    public int getCapacity() {
        return capacity;
    }
    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
    public int getAvailableBed() {
        return availableBed;
    }
    public void setAvailableBed(int availableBed) {
        this.availableBed = availableBed;
    }
    public boolean isBunked() {
        return isBunked;
    }
    public void setBunked(boolean isBunked) {
        this.isBunked = isBunked;
    }
    public boolean isPrivateBathroom() {
        return privateBathroom;
    }
    public void setPrivateBathroom(boolean privateBathroom) {
        this.privateBathroom = privateBathroom;
    }

    
}
