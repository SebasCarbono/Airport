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

/**
 *
 * @author Sebas
 */
public class AddToFlight {
    private final GetItem<Flight> flightReader;
    private final GetItem<Passenger> passengerReader;

    public AddToFlight(GetItem<Flight> flightReader, GetItem<Passenger> passengerReader) {
        this.flightReader = flightReader;
        this.passengerReader = passengerReader;
    }
    
    public Response execute(String passengerId, String flightId){
        try{
            long passengerIdLong;
            Flight flight = null;
            
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
            
            flight = flightReader.getItem(flightId);
            if (flight == null) {
                return new Response("Please, select a flight", Status.NOT_FOUND);
            }
            
            flight.addPassenger(passenger);
            passenger.addFlight(flight);
            
            return new Response("Passenger " + passengerId +  " added successfully to flight " + flightId, Status.OK);
        }catch (Exception ex){
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
