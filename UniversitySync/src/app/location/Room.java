package location;

public class Room {
    protected static int numberOfInstances;
    final Dormitory dorm;
    final int roomNo;
    final int capacity;
    private int id;
    int availableBed;
    boolean isBunked;
    boolean privateBathroom;
}
