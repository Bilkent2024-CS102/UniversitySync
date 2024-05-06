package app.model;

import java.util.ArrayList;

import app.model.location.Dormitory;
import app.model.location.cafeteria.Cafeteria;

public class Campus {
    private static int numberOfInstances;
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
    
    public static int getNumberOfInstances() {
        return numberOfInstances;
    }
    public static void setNumberOfInstances(int numberOfInstances) {
        Campus.numberOfInstances = numberOfInstances;
    }
    public int getCampusId() {
        return campusId;
    }
    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Dormitory> getDormitories() {
        return dormitories;
    }
    public void setDormitories(ArrayList<Dormitory> dormitories) {
        this.dormitories = dormitories;
    }
    public ArrayList<Cafeteria> getCafeterias() {
        return cafeterias;
    }
    public void setCafeterias(ArrayList<Cafeteria> cafeterias) {
        this.cafeterias = cafeterias;
    }

}
