/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.airplaneControllers;

import airport.core.controllers.utils.Response;
import airport.core.controllers.utils.Status;
import airport.core.models.Plane;
import airport.core.models.storageManagement.GetItem;
import java.util.ArrayList;

/**
 *
 * @author Karoll
 */
public class LoadPlaneData {
    
    private final GetItem<Plane> planeGetter;

    public LoadPlaneData(GetItem planeGetter) {
        this.planeGetter = planeGetter;
    }
    
    public Response execute(){
        try {
            ArrayList<Plane> original = planeGetter.getAllItems();
            ArrayList<Plane> copy = new ArrayList<>();

            for (Plane p : original) {
                copy.add(new Plane(
                    p.getId(),
                    p.getBrand(),
                    p.getModel(),
                    p.getMaxCapacity(),
                    p.getAirline()
                ));
            }

            return new Response("Pasajeros cargados correctamente", Status.OK, copy);

        } catch (Exception e) {
            return new Response("Error al cargar pasajeros: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
