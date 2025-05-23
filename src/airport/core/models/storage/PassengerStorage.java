/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.models.storage;

import airport.core.controllers.utils.Response;
import airport.core.models.Passenger;
import java.util.ArrayList;

/**
 *
 * @author Sebas
 */
public class PassengerStorage {
    
    private static PassengerStorage instance;
    private ArrayList<Passenger> passengers;
    
    private PassengerStorage() {
        JsonStorage jsonStorage = JsonStorage.getInstance();
        Response rPassenger = jsonStorage.loadPassengersFromJson();
        if (rPassenger.getStatus() < 400) {
            ArrayList<Passenger> loadedPassengers = (ArrayList<Passenger>) rPassenger.getObject();
            this.passengers = new ArrayList<>(loadedPassengers);
        } else {
            this.passengers = new ArrayList<>();
        }
    }
    
    public static PassengerStorage getInstance() {
        if (instance == null) {
            instance = new PassengerStorage();
        }
        return instance;
    }
    
    public boolean addPassenger(Passenger passenger) {
        for (Passenger p : this.passengers) {
            if (p.getId() == passenger.getId()) {
                return false;
            }
        }
        this.passengers.add(passenger);
        return true;
    }
    
    public Passenger getPassenger(long id) {
        for (Passenger passenger : this.passengers) {
            if (passenger.getId() == id) {
                return passenger;
            }
        }
        return null;
    }
    
    public ArrayList<Passenger> getPassengers(){
        return this.passengers;
    }
    
}
