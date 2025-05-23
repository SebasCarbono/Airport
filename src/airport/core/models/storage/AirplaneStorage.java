package airport.core.models.storage;

import airport.core.models.Plane;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import airport.core.models.Plane;
/**
 *
 * @author Karoll
 */
public class AirplaneStorage {
    private static AirplaneStorage instance;
    private ArrayList<Plane> airplanes;

    private AirplaneStorage(){
        this.airplanes = new ArrayList<>();
    }
    
    public static AirplaneStorage getInstance() {
        if (instance == null) {
            instance = new AirplaneStorage();
        }
        return instance;
    }
    
    public boolean addAirplane(Plane airplane) {
        for (Plane a : this.airplanes) {
            if (a.getId() == airplane.getId()){
                return false;
            }
        }
        this.airplanes.add(airplane);
        return true;
    }
}
