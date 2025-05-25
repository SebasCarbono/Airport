/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.flightControllers;

import airport.core.controllers.utils.Response;
import airport.core.controllers.utils.Status;
import airport.core.models.Flight;
import airport.core.models.Location;
import airport.core.models.Plane;
import airport.core.models.storageManagement.Add;
import airport.core.models.storageManagement.GetItem;
import java.time.LocalDateTime;

/**
 *
 * @author Sebas
 */
public class CreateFlight {
    
    private final Add<Flight> flightWriter;
    private final GetItem<Plane> airplaneReader;
    private final GetItem<Location> locationReader;

    public CreateFlight(Add<Flight> flightWriter, GetItem<Plane> airplaneReader, GetItem<Location> locationReader) {
        this.flightWriter = flightWriter;
        this.airplaneReader = airplaneReader;
        this.locationReader = locationReader;
    }
    
    public Response execute(String id, String planeId, String departureLocationId, String arrivalLocationId, 
            String scaleLocationId, String year, String month, String day, String hour, String minute, 
            String hoursDurationsArrival, String minutesDurationsArrival, String hoursDurationsScale, String minutesDurationsScale){
        try{
            
            boolean scaleId = false;
            Plane plane = null;
            Flight newFlight = null;
            Location departureLocation, arrivalLocation, scaleLocation = null;
            int dayInt, monthInt, yearInt, hourInt, minuteInt, hoursDurationArrivalInt, 
                    minutesDurationArrivalInt, hoursDurationScaleInt = 0, minutesDurationScaleInt = 0;
        
            if(id.length() != 6){
                return new Response("Id must contain 6 charactes", Status.BAD_REQUEST);
            }
            
            if(!id.matches("^[A-Z]{3}\\d{3}$")){
                return new Response("ID must follow format XXXYYY (3 uppercase letters and 3 numeric digits)", Status.BAD_REQUEST);
            }
            
            try {
                yearInt = Integer.parseInt(year);
            } catch (NumberFormatException ex) {
                return new Response("Departure year must be numeric", Status.BAD_REQUEST);
            }
            
            if (yearInt < 0) {
                return new Response("Departure year must be greater than zero", Status.BAD_REQUEST);
            }

            if (year.length() != 4) {
                return new Response("Departure year must contain four digits", Status.BAD_REQUEST);
            }
            
            try {
                plane = airplaneReader.getItem(planeId);
            } catch (NumberFormatException ex) {
                return new Response("Please, select a plane ID", Status.BAD_REQUEST);
            }
            
            try{
                departureLocation = locationReader.getItem(departureLocationId);
            }catch (NumberFormatException ex) {
                return new Response("Please, select a departure location ID", Status.BAD_REQUEST);
            }
            
            try{
                arrivalLocation = locationReader.getItem(arrivalLocationId);
            }catch (NumberFormatException ex) {
                return new Response("Please, select an arrival location ID", Status.BAD_REQUEST);
            }
            
            if(departureLocation.equals(arrivalLocation)){
                return new Response("Departure and arrival locations can not be the same", Status.BAD_REQUEST);
            }
            
            try{
                scaleLocation = locationReader.getItem(scaleLocationId);
                scaleId = true;
                
                if(departureLocation.equals(scaleLocation)){
                    return new Response("Departure and scale locations can not be the same", Status.BAD_REQUEST);
                }
                
                if(arrivalLocation.equals(scaleLocation)){
                    return new Response("Arrival and scale locations can not be the same", Status.BAD_REQUEST);
                }
            }catch (NumberFormatException ex) {
                hoursDurationsScale = "0";
                minutesDurationsScale = "0";
            }
            
            try {
                monthInt = Integer.parseInt(month);
            } catch (NumberFormatException ex) {
                return new Response("Please, select a departure month", Status.BAD_REQUEST);
            }
            
            try {
                dayInt = Integer.parseInt(day);
            } catch (NumberFormatException ex) {
                return new Response("Please, select a departure day", Status.BAD_REQUEST);
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
            
            LocalDateTime departureDate = LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, minuteInt);
            
            if (departureDate.isEqual(LocalDateTime.now()) || departureDate.isBefore(LocalDateTime.now())) {
                return new Response("Departure date can not be the same or before the current date and hour", Status.BAD_REQUEST);
            }
            
            try {
                hoursDurationArrivalInt = Integer.parseInt(hoursDurationsArrival);
            } catch (NumberFormatException ex) {
                return new Response("Please, select an arrival duration hour", Status.BAD_REQUEST);
            }
            
            try {
                minutesDurationArrivalInt = Integer.parseInt(minutesDurationsArrival);
            } catch (NumberFormatException ex) {
                return new Response("Please, select an arrival duration minute", Status.BAD_REQUEST);
            }
            
            if((hoursDurationArrivalInt + minutesDurationArrivalInt) <= 0){
                    return new Response("Arrival duration must be greater than 00:00", Status.BAD_REQUEST);
                }
            
            if(scaleId){
                try {
                    hoursDurationScaleInt = Integer.parseInt(hoursDurationsScale);
                } catch (NumberFormatException ex) {
                    return new Response("Please, select a scale duration hour", Status.BAD_REQUEST);
                }
                try {
                    minutesDurationScaleInt = Integer.parseInt(minutesDurationsScale);
                } catch (NumberFormatException ex) {
                    return new Response("Please, select a scale duration minute", Status.BAD_REQUEST);
                }
                
                if((hoursDurationScaleInt + minutesDurationScaleInt) <= 0){
                    return new Response("Scale duration must be greater than 00:00", Status.BAD_REQUEST);
                }
            }

            if(!scaleId){
                newFlight = new Flight(id, plane, departureLocation, arrivalLocation, departureDate, hoursDurationArrivalInt, minutesDurationArrivalInt);
            }else{
                newFlight = new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hoursDurationArrivalInt, minutesDurationArrivalInt, hoursDurationScaleInt, minutesDurationScaleInt);
            }
            if (!flightWriter.addItem(newFlight)) {
                return new Response("A flight with that id already exists", Status.BAD_REQUEST);
            }
            if(!scaleId){
                return new Response("Flight created successfully", Status.CREATED);
            }else{
                return new Response("Stopover flight created successfully.", Status.CREATED);
            }
        }catch(Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
