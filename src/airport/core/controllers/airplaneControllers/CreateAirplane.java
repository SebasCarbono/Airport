package airport.core.controllers.airplaneControllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import airport.core.controllers.utils.Response;
import airport.core.models.Plane;
import airport.core.models.storage.AirplaneStorage;
import core.controllers.utils.Status;
/**
 *
 * @author Karoll
 */
public class CreateAirplane {
    public static Response CreateAirplane(String id,String brand,String model,String maxCapacity,String airline){
        try{
            int intMaxCapacity;
        
            if(id.length() != 7){
                return new Response("Id must contain min 7 charactes", Status.BAD_REQUEST);
            }
            
            if(!id.matches("^[A-Z]{2}\\d{5}$")){
                return new Response("ID must follow format XXYYYYY (2 uppercase letters + 5 numeric digits)", Status.BAD_REQUEST);
            }
            
            if(brand.equals("")){
                return new Response("Brand must not be empty", Status.BAD_REQUEST);
            }
            
            if(model.equals("")){
                return new Response("Model must not be empty", Status.BAD_REQUEST);
            }
            
            if(maxCapacity.equals("")){
                return new Response("Max Capacity must not be empty", Status.BAD_REQUEST);
            }
            
            try{
                intMaxCapacity = Integer.parseInt(maxCapacity);
            }catch(NumberFormatException ex){
                return new Response("Max Capacity must be numeric", Status.BAD_REQUEST);
            }
            
            if(intMaxCapacity < 0){
                return new Response("Max. Capacity must be gretaer than zero", Status.BAD_REQUEST);
            }
            
            if(airline.equals("")){
                return new Response("Airline must not be empty", Status.BAD_REQUEST);
            }
            
            AirplaneStorage storage = AirplaneStorage.getInstance();
            Plane newPlane = new Plane(id, brand, model, intMaxCapacity, airline);
            if (!storage.addAirplane(newPlane)) {
                return new Response("A airplane with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Airplane created successfully", Status.CREATED);
        }catch(Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
