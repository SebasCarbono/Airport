package airport.core.models.storage;

import airport.core.models.Passenger;
import airport.core.models.storageManagement.Add;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import airport.core.models.Plane;
import airport.core.models.storageManagement.GetItem;
import airport.core.models.storageManagement.LoadData;
import java.util.Comparator;
/**
 *
 * @author Karoll
 */
public class AirplaneStorage implements Add<Plane>, GetItem<Plane>{
    private static AirplaneStorage instance;
    private ArrayList<Plane> airplanes;

    public AirplaneStorage(LoadData<Plane> loader) {
        this.airplanes = new ArrayList<>(loader.load());
    }
    
    public static AirplaneStorage getInstance(LoadData<Plane> loader) {
        if (instance == null) {
            instance = new AirplaneStorage(loader);
        }
        return instance;
    }
    
    @Override
    public boolean addItem(Plane airplane) {
        for (Plane a : this.airplanes) {
            if(a.getId().equals(airplane.getId())){
                return false;
            }
        }
        this.airplanes.add(airplane);
        return true;
    }

    @Override
    public Plane getItem(String id) {
        try{
            for (Plane p : this.airplanes) {
                if (p.getId().equals(id)) {
                    return p;
                }
            }
            return null;
        }catch(NumberFormatException ex){
            return null;
        }
    }

    @Override
    public ArrayList<Plane> getAllItems() {
        return new ArrayList<>(this.airplanes);
    }

    @Override
    public ArrayList<Plane> getOrderedItems() {
        ArrayList<Plane> orderedPlanes = new ArrayList<>(this.airplanes);
        orderedPlanes.sort(Comparator.comparing(Plane::getId));
        return orderedPlanes;    
    }
}
