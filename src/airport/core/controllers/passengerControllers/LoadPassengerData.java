/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.passengerControllers;

import airport.core.controllers.utils.Response;
import airport.core.controllers.utils.Status;
import airport.core.models.Passenger;
import airport.core.models.storageManagement.GetItem;
import java.util.ArrayList;

/**
 *
 * @author Karoll
 */
public class LoadPassengerData {
    
    private final GetItem<Passenger> passengerGetter;

    public LoadPassengerData(GetItem<Passenger> passengerGetter) {
        this.passengerGetter = passengerGetter;
    }

    public Response execute() {
        try {
            ArrayList<Passenger> ordered = passengerGetter.getOrderedItems();
            ArrayList<Passenger> copy = new ArrayList<>();

            for (Passenger p : ordered) {
                copy.add(new Passenger(
                    p.getId(),
                    p.getFirstname(),
                    p.getLastname(),
                    p.getBirthDate(),
                    p.getCountryPhoneCode(),
                    p.getPhone(),
                    p.getCountry()
                ));
            }

            return new Response("Passengers loaded successfully", Status.OK, copy);

        } catch (Exception e) {
            return new Response("Error loading passengers", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
