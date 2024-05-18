package app.model;

import java.util.ArrayList;

import app.model.location.Dormitory;
import app.model.location.cafeteria.Cafeteria;

public class Campus {
    private int campusId;
    private String name;
    private ArrayList<Dormitory> dormitories;
    private ArrayList<Cafeteria> cafeterias;

    public Campus (int id, String name, ArrayList<Dormitory> dorms, ArrayList<Cafeteria> cafes){
        setCampusId(id);
        setCafeterias(cafes);
        setName(name);
        setDormitories(dorms);
    }
    
    public int getCampusId() {
        return campusId;
    }
    public String getName() {
        return name;
    }
    public ArrayList<Dormitory> getDormitories() {
        return dormitories;
    }
    public ArrayList<Cafeteria> getCafeterias() {
        return cafeterias;
    }

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDormitories(ArrayList<Dormitory> dormitories) {
        this.dormitories = dormitories;
    }
    public void setCafeterias(ArrayList<Cafeteria> cafeterias) {
        this.cafeterias = cafeterias;
    }

}
