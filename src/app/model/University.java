package app.model;

import java.util.ArrayList;

public class University
{
    private int universityId;
    private ArrayList<Campus> campuses;
    private ArrayList<User> students;

    public University(ArrayList<Campus> campuses, ArrayList<User> students){
        setCampuses(campuses);
        setStudents(students);
    }
    
    public int getUniversityId() {
        return universityId;
    }
    public ArrayList<Campus> getCampuses() {
        return campuses;
    }
    public ArrayList<User> getStudents() {
        return students;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }
    public void setCampuses(ArrayList<Campus> campuses) {
        this.campuses = campuses;
    }
    public void setStudents(ArrayList<User> students) {
        this.students = students;
    }

    
}