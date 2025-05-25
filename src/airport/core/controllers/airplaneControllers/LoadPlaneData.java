/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.airplaneControllers;

import airport.core.controllers.utils.Response;
import airport.core.controllers.utils.Status;
import airport.core.models.storage.AirplaneStorage;

/**
 *
 * @author Karoll
 */
public class LoadPlaneData {
    public static Response LoadPlaneData(){
        try{
            AirplaneStorage airplaneStorage = AirplaneStorage.getInstance();
            return new Response("Planes loaded successfully", Status.OK, airplaneStorage.getAllItems());
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
