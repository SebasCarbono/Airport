/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.passengerControllers;

import airport.core.controllers.utils.CheckDigits;
import airport.core.models.Passenger;
import airport.core.models.storage.PassengerStorage;
import airport.core.controllers.utils.Response;
import core.controllers.utils.Status;
import java.time.LocalDate;

/**
 *
 * @author Sebas
 */
public class RegisterPassenger {
    public static Response createPassenger(String id, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
        
        long idLong = Long.parseLong(id), phoneLong = Long.parseLong(phone);
        int phoneCodeInt = Integer.parseInt(phoneCode), dayInt = Integer.parseInt(day), monthInt = Integer.parseInt(month), yearInt = Integer.parseInt(year);
        
        try {
            try {
                if (idLong < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            try {
                if (CheckDigits.digitsInNRange(idLong, 15)) {
                    return new Response("Id must contain max. 15 digitas", Status.BAD_REQUEST);
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
            
            try {
                if (dayInt < 0) {
                    return new Response("Day must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Day must be numeric", Status.BAD_REQUEST);
            }
            
            try {
                if (monthInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            LocalDate birthDate = LocalDate.of(yearInt, monthInt, dayInt);
            
            if (birthDate.isEqual(LocalDate.now()) || birthDate.isAfter(LocalDate.now())) {
                return new Response("Birthdate can not be the same as the current date or pass the current date", Status.BAD_REQUEST);
            } 
            
            PassengerStorage storage = PassengerStorage.getInstance();            
            if (!storage.addPassenger(new Passenger(idLong, firstname, lastname, birthDate, phoneCodeInt, phoneLong, country))) {
                return new Response("A person with that id already exists", Status.BAD_REQUEST);
            } else {
            }
            return new Response("Person created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
