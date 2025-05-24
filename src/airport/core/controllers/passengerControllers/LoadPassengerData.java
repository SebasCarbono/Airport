/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.passengerControllers;

import airport.core.controllers.utils.Response;
import airport.core.models.storage.JsonStorage;
import airport.core.controllers.utils.Status;

/**
 *
 * @author Karoll
 */
public class LoadPassengerData {
    
    public static Response LoadPassengerData(){
        try{
            JsonStorage storage = JsonStorage.getInstance();
            return storage.loadPassengersFromJson();
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
