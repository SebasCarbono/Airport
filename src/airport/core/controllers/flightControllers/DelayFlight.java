/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.flightControllers;

import airport.core.controllers.utils.Response;
import airport.core.controllers.utils.Status;
import airport.core.models.Flight;
import airport.core.models.storageManagement.GetItem;

/**
 *
 * @author Sebas
 */
public class DelayFlight {
    private final GetItem<Flight> flightReader;

    public DelayFlight(GetItem<Flight> flightReader) {
        this.flightReader = flightReader;
    }
    
    public Response execute(String flightId, String hour, String minute) {
        try{
            Flight flight = null;
            int hourInt, minuteInt;

            try{
                flight = flightReader.getItem(flightId);

                if (flight == null) {
                    return new Response("Flight not found", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Please, select a flight ID", Status.BAD_REQUEST);
            }

            try {
                hourInt = Integer.parseInt(hour);
            } catch (NumberFormatException ex) {
                return new Response("Please, select a departure hour", Status.BAD_REQUEST);
            }

            try {
                minuteInt = Integer.parseInt(minute);
            } catch (NumberFormatException ex) {
                return new Response("Please, select a departure minute", Status.BAD_REQUEST);
            }

            flight.delay(hourInt, minuteInt);

            return new Response("Flight delayed successfully", Status.OK);
        }catch(Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
