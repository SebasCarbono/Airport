/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.locationControllers;

import airport.core.controllers.utils.Response;
import airport.core.models.Location;
import airport.core.models.storage.LocationStorage;
import airport.core.controllers.utils.Status;

/**
 *
 * @author Karoll
 */
public class CreateLocation {
    public static Response CreateLocation(String id,String name,String city,String country,String latitude,String longitude){
	try{
            
            double doubleLatitude, doubleLongitude;
            
            if(id.length() != 3 || !id.matches("[A-Z]{3}")){
                return new Response("Id must be 3 uppercase letters", Status.BAD_REQUEST);
            }

            if(name.equals("")){
                return new Response("Name must be not empty", Status.BAD_REQUEST);
            }

            if(city.equals("")){
                return new Response("City must be not empty", Status.BAD_REQUEST);
}

            if(country.equals("")){
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }

            try{
                doubleLatitude = Double.parseDouble(latitude);
            }catch(NumberFormatException ex){
                return new Response("Latitude must be numeric", Status.BAD_REQUEST);
            }
            
            if(doubleLatitude < -90 || doubleLatitude > 90){
                return new Response("Latitude must be in range [-90, 90]", Status.BAD_REQUEST);
            }

            try{
                doubleLongitude = Double.parseDouble(longitude);
            }catch(NumberFormatException ex){
                return new Response("Longitude must be numeric", Status.BAD_REQUEST);
            }
            
            if(doubleLongitude < -180 || doubleLongitude > 180){
                return new Response("Latitude must be in range [-180, 180]", Status.BAD_REQUEST);
            }

            LocationStorage storage = LocationStorage.getInstance();
            Location newLocation = new Location(id, name, city, country, doubleLatitude, doubleLongitude);
            if(!storage.addLocation(newLocation)){
                    return new Response("A location with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Location created successfully", Status.CREATED);
	}catch(Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
