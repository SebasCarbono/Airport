/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.passengerControllers;

import airport.core.models.Passenger;
import airport.core.models.storage.PassengerStorage;
import airport.core.controllers.utils.Response;
import core.controllers.utils.Status;
import java.time.LocalDate;

/**
 *
 * @author Sebas
 */
public class CreatePassenger {
    public static Response createPassenger(long id, String firstname, String lastname, LocalDate birthDate, int countryPhoneCode, long phone, String country) {
        try {
            try {
                if (id < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            if (firstname.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }
            
            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            //Passenger(long id, String firstname, String lastname, LocalDate birthDate, int countryPhoneCode, long phone, String country)
            PassengerStorage storage = PassengerStorage.getInstance();            
            if (!storage.addPassenger(new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country))) {
                return new Response("A person with that id already exists", Status.BAD_REQUEST);
            } else {
            }
            return new Response("Person created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
