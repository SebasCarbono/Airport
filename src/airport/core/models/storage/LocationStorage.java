/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.models.storage;

import airport.core.models.Location;
import airport.core.models.storageManagement.Add;
import airport.core.models.storageManagement.GetItem;
import airport.core.models.storageManagement.LoadData;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Karoll
 */
public class LocationStorage implements Add<Location>, GetItem<Location>{
    private static LocationStorage instance;
    private ArrayList<Location> locations;

    private LocationStorage(LoadData<Location> loader){
        this.locations = new ArrayList<>(loader.load());
    }
    
    public static LocationStorage getInstance(LoadData<Location> loader) {
        if (instance == null) {
            instance = new LocationStorage(loader);
        }
        return instance;
    }
    
    @Override
    public boolean addItem(Location location) {
        for (Location l : this.locations) {
            if (l.getAirportId() == location.getAirportId()){
                return false;
            }
        }
        this.locations.add(location);
        return true;
    }

    @Override
    public Location getItem(String id) {
        try{
            for (Location location : this.locations) {
                if (location.getAirportId().equals(id)) {
                    return location;
                }
            }
            return null;
        }catch(Exception ex) {
            return null;
        }
    }

    @Override
    public ArrayList<Location> getAllItems() {
        return new ArrayList<>(this.locations);
    }

    @Override
    public ArrayList<Location> getOrderedItems() {
        ArrayList<Location> orderedLocations = new ArrayList<>(this.locations);
        orderedLocations.sort(Comparator.comparing(Location::getAirportId));
        return orderedLocations;
    }
    
}
