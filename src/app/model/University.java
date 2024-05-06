package app.model;

import java.util.ArrayList;

public class University
{
    private static int numberOfInstances;
    private int universityId;
    private ArrayList<Campus> campuses;
    private ArrayList<User> students;

    public University(ArrayList<Campus> campuses, ArrayList<User> students){
        setCampuses(campuses);
        setStudents(students);
    }
    
    public static int getNumberOfInstances() {
        return numberOfInstances;
    }
    public static void setNumberOfInstances(int numberOfInstances) {
        University.numberOfInstances = numberOfInstances;
    }
    public int getUniversityId() {
        return universityId;
    }
    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }
    public ArrayList<Campus> getCampuses() {
        return campuses;
    }
    public void setCampuses(ArrayList<Campus> campuses) {
        this.campuses = campuses;
    }
    public ArrayList<User> getStudents() {
        return students;
    }
    public void setStudents(ArrayList<User> students) {
        this.students = students;
    }

    
}