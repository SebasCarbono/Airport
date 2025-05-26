/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.flightControllers;

import airport.core.controllers.utils.Response;
import airport.core.controllers.utils.Status;
import airport.core.models.Flight;
import airport.core.models.Passenger;
import airport.core.models.storageManagement.GetItem;
import java.util.ArrayList;

/**
 *
 * @author Sebas
 */
public class LoadFlightsByPassenger {
    private final GetItem<Passenger> passengerReader;

    public LoadFlightsByPassenger(GetItem<Passenger> passengerReader) {
        this.passengerReader = passengerReader;
    }
    
    public Response execute(String passengerId){
        try {
            long passengerIdLong;
            ArrayList<Flight> passengerFlights;
            
            try {
                passengerIdLong = Long.parseLong(passengerId);
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            if (passengerIdLong < 0) {
                return new Response("Id must be gretaer than zero", Status.BAD_REQUEST);
            }

            if (passengerId.length() > 15) {
                return new Response("Id must contain max 15 digits", Status.BAD_REQUEST);
            }
            
            Passenger passenger = passengerReader.getItem(passengerId);
            if (passenger == null) {
                return new Response("Passenger not found", Status.NOT_FOUND);
            }
            
            passengerFlights = passenger.getFlights();
            
            return new Response("Flights for passenger loaded", Status.OK, passengerFlights);
        } catch (Exception ex) {
            return new Response("Error loading passenger flights", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
