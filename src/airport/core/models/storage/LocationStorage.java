/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.models.storage;

import airport.core.models.Location;
import java.util.ArrayList;

/**
 *
 * @author Karoll
 */
public class LocationStorage {
    private static LocationStorage instance;
    private ArrayList<Location> locations;

    private LocationStorage(){
        this.locations = new ArrayList<>();
    }
    
    public static LocationStorage getInstance() {
        if (instance == null) {
            instance = new LocationStorage();
        }
        return instance;
    }
    
    public boolean addLocation(Location location) {
        for (Location l : this.locations) {
            if (l.getAirportId() == location.getAirportId()){
                return false;
            }
        }
        this.locations.add(location);
        return true;
    }
}
