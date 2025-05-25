/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.passengerControllers;

import airport.core.controllers.utils.Response;
import airport.core.controllers.utils.Status;
import airport.core.models.storage.PassengerStorage;

/**
 *
 * @author Karoll
 */
public class LoadPassengerData {
    
    public static Response LoadPassengerData(){
        try{
            PassengerStorage storage = PassengerStorage.getInstance();
            return new Response("Passengers loaded successfully", Status.OK, storage.getPassengers());
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
