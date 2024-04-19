package app.userContent.post;

public class Tag {
    private static int numberOfInstances;
    private int tagId;
    String tag;

    public static int getNumberOfInstances() {
        return numberOfInstances;
    }
    
    public int getTagId() {
        return tagId;
    }
}
