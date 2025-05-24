/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.models.storage;

import airport.core.models.Flight;
import java.util.ArrayList;

/**
 *
 * @author Sebas
 */
public class FlightStorage {
    
    private static FlightStorage instance;
    private ArrayList<Flight> flights;
    
//    private FlightStorage() {
//        JsonStorage jsonStorage = JsonStorage.getInstance();
//        Response rFlight = jsonStorage.loadFlightsFromJson();
//        if(rFlight.getStatus() < 400){
//            this.flights = (ArrayList<Flight>) rFlight.getObject();
//        }else{this.flights = new ArrayList<>();}
//    }
    
    public static FlightStorage getInstance() {
        if (instance == null) {
            instance = new FlightStorage();
        }
        return instance;
    }
    
    public boolean addFlight(Flight flight) {
        for (Flight p : this.flights) {
            if (p.getId().equals(flight.getId())) {
                return false;
            }
        }
        this.flights.add(flight);
        return true;
    }
    
    public Flight getFlight(String id) {
        for (Flight f : this.flights) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }
        
}
