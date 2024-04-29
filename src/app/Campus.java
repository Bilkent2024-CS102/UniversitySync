package app;

import java.util.ArrayList;

import app.location.Dormitory;
import app.location.cafeteria.Cafeteria;

public class Campus {
    private static int numberOfInstances;
    private int campusId;
    private String name;
    private ArrayList<Dormitory> dormitories;
    private ArrayList<Cafeteria> cafeterias;

    public int getCampusId() {
        return campusId;
    }
}
