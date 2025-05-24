/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.passengerControllers;

import airport.core.models.Passenger;
import airport.core.models.storage.PassengerStorage;
import airport.core.controllers.utils.Response;
import airport.core.controllers.utils.Status;

/**
 *
 * @author Sebas
 */
public class UpdatePassenger {
    public static Response updatePassenger(long id, String firstname, String lastname) {
        try {
            
            try {
                if (id < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            PassengerStorage storage = PassengerStorage.getInstance();
            
            Passenger person = storage.getPassenger(id);
            if (person == null) {
                return new Response("Person not found", Status.NOT_FOUND);
            }
            
            if (firstname.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }
            
            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            person.setFirstname(firstname);
            person.setLastname(lastname);
            
            return new Response("Person data updated successfully", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
