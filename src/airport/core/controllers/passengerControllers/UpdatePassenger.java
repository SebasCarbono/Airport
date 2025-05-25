/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.passengerControllers;

import airport.core.models.Passenger;
import airport.core.controllers.utils.Response;
import airport.core.controllers.utils.Status;
import airport.core.models.storageManagement.GetItem;
import java.time.LocalDate;

/**
 *
 * @author Sebas
 */
public class UpdatePassenger {
    
    private final GetItem<Passenger> passengerReader;

    public UpdatePassenger(GetItem<Passenger> passengerReader) {
        this.passengerReader = passengerReader;
    }
    
    public Response execute(String id, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
        
        try {
            long idLong, phoneLong;
            int phoneCodeInt, dayInt, monthInt, yearInt;
            
            try {
                idLong = Long.parseLong(id);
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            if (idLong < 0) {
                return new Response("Id must be gretaer than zero", Status.BAD_REQUEST);
            }

            if (id.length() > 15) {
                return new Response("Id must contain max 15 digits", Status.BAD_REQUEST);
            }
            
            Passenger p = passengerReader.getItem(id);
            if (p == null) {
                return new Response("Passenger not found", Status.NOT_FOUND);
            }
            
            if (firstname.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }
            
            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            try {
                yearInt = Integer.parseInt(year);
            } catch (NumberFormatException ex) {
                return new Response("Year must be numeric", Status.BAD_REQUEST);
            }
            
            if (yearInt < 0) {
                return new Response("Year must be gretaer than zero", Status.BAD_REQUEST);
            }

            if (year.length() != 4) {
                return new Response("Year must contain four digits", Status.BAD_REQUEST);
            }
            
            try {
                monthInt = Integer.parseInt(month);
            } catch (NumberFormatException ex) {
                return new Response("Please, select a month", Status.BAD_REQUEST);
            }
            
            try {
                dayInt = Integer.parseInt(day);
            } catch (NumberFormatException ex) {
                return new Response("Please, select a day", Status.BAD_REQUEST);
            }
            
            LocalDate birthDate = LocalDate.of(yearInt, monthInt, dayInt);
            
            if (birthDate.isEqual(LocalDate.now()) || birthDate.isAfter(LocalDate.now())) {
                return new Response("Birthdate can not be the same as the current date or pass the current date", Status.BAD_REQUEST);
            } 
            
            try {
                phoneCodeInt = Integer.parseInt(phoneCode);
            } catch (NumberFormatException ex) {
                return new Response("Country phone code must be numeric", Status.BAD_REQUEST);
            }
            
            if (phoneCodeInt < 0) {
                return new Response("Country phone code must be positive", Status.BAD_REQUEST);
            }

            if (phoneCode.length() > 3) {
                return new Response("Country phone code must contain max. 3 digitas", Status.BAD_REQUEST);
            }
            
            try {
                phoneLong = Long.parseLong(phone);
            } catch (NumberFormatException ex) {
                return new Response("Phone number must be numeric", Status.BAD_REQUEST);
            }
            
            if (phoneLong < 0) {
                return new Response("Phone number must be gretaer than zero", Status.BAD_REQUEST);
            }

            if (phone.length() > 11) {
                return new Response("Phone number must contain max. 11 digitas", Status.BAD_REQUEST);
            }
            
            if (country.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            p.setBirthDate(birthDate);
            p.setCountry(country);
            p.setCountryPhoneCode(phoneCodeInt);
            p.setPhone(phoneLong);
            p.setFirstname(firstname);
            p.setLastname(lastname);
            
            return new Response("Passenger information updated successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
