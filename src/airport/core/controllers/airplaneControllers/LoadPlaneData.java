/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.airplaneControllers;

import airport.core.controllers.utils.Response;
import airport.core.models.storage.JsonStorage;
import airport.core.controllers.utils.Status;

/**
 *
 * @author Karoll
 */
public class LoadPlaneData {
    public static Response LoadPlaneData(){
        try{
            JsonStorage storage = JsonStorage.getInstance();
            return storage.loadPlanesFromJson();
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
