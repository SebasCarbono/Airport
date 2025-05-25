/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.models.storage;

import airport.core.models.Passenger;
import airport.core.models.storageManagement.Add;
import airport.core.models.storageManagement.GetItem;
import airport.core.models.storageManagement.LoadData;
import java.util.ArrayList;

/**
 *
 * @author Sebas
 */
public class PassengerStorage implements Add<Passenger>, GetItem<Passenger> {
    
    private static PassengerStorage instance;
    private ArrayList<Passenger> passengers;
    
    public PassengerStorage(LoadData<Passenger> loader) {
        this.passengers = new ArrayList<>(loader.load());
    }
    
    public static PassengerStorage getInstance(LoadData<Passenger> loader) {
        if (instance == null) {
            instance = new PassengerStorage(loader);
        }
        return instance;
    }
    
    @Override
    public boolean addItem(Passenger passenger) {
        for (Passenger p : this.passengers) {
            if (p.getId() == passenger.getId()) {
                return false;
            }
        }
        this.passengers.add(passenger);
        return true;
    }
    
    @Override
    public Passenger getItem(String id) {
        try{
            long idLong = Long.parseLong(id);
            for (Passenger passenger : this.passengers) {
                if (passenger.getId() == idLong) {
                    return passenger;
                }
            }
        }catch(NumberFormatException ex) {
            
        }
        return null;
    }

    @Override
    public ArrayList<Passenger> getAllItems() {
        return new ArrayList<>(this.passengers);
    }
    
}
