/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.models.storage;

import airport.core.models.Flight;
import airport.core.models.storageManagement.Add;
import airport.core.models.storageManagement.GetItem;
import airport.core.models.storageManagement.LoadData;
import java.util.ArrayList;

/**
 *
 * @author Sebas
 */
public class FlightStorage implements Add<Flight>, GetItem<Flight>{
    
    private static FlightStorage instance;
    private ArrayList<Flight> flights;
    
    public FlightStorage(LoadData<Flight> loader) {
        this.flights = new ArrayList<>(loader.load());
    }

    public static FlightStorage getInstance(LoadData<Flight> loader) {
        if (instance == null) {
            instance = new FlightStorage(loader);
        }
        return instance;
    }
    
    @Override
    public boolean addItem(Flight flight) {
        for (Flight p : this.flights) {
            if (p.getId().equals(flight.getId())) {
                return false;
            }
        }
        this.flights.add(flight);
        return true;
    }
    
    @Override
    public Flight getItem(String id) {
        for (Flight f : this.flights) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Flight> getAllItems() {
        return new ArrayList<>(this.flights);
    }

}
