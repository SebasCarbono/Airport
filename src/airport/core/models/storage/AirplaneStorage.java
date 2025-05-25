package airport.core.models.storage;

import airport.core.controllers.utils.Response;
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
        JsonStorage jsonStorage = JsonStorage.getInstance();
        Response rPlane = jsonStorage.loadPlanesFromJson(); 
        if(rPlane.getStatus() < 400){
            ArrayList<Plane> loadedPassengers = (ArrayList<Plane>) rPlane.getObject();
            this.airplanes = new ArrayList<>(loadedPassengers);
        } else {
            this.airplanes = new ArrayList<>();
        }
    }
    
    public static AirplaneStorage getInstance() {
        if (instance == null) {
            instance = new AirplaneStorage();
        }
        return instance;
    }
    
    public boolean addAirplane(Plane airplane) {
        for (Plane a : this.airplanes) {
            if(a.getId().equals(airplane.getId())){
                return false;
            }
        }
        this.airplanes.add(airplane);
        return true;
    }
    
    public Plane getAirplane(String id) {
        for (Plane p : this.airplanes) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
    
    public ArrayList<Plane> getPlanes(){
        return this.airplanes;
    }
}
