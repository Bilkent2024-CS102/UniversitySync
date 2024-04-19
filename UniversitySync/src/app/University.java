package app;

import java.util.ArrayList;

public class University
{
    private static int numberOfInstances;
    private int universityId;
    private ArrayList<Campus> campuses;
    private ArrayList<User> students;

    public int getUniversityId() {
        return universityId;
    }
}