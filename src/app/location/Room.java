package app.location;

public class Room {
    private static int numberOfInstances;
    private final Dormitory dorm;
    private final int roomNo;
    private final int capacity;
    private int roomId;
    private int availableBed;
    private boolean isBunked;
    private boolean privateBathroom;

    public int getRoomId() {
        return roomId;
    }
}
