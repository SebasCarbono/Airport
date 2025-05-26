/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.locationControllers;

import airport.core.controllers.utils.Response;
import airport.core.controllers.utils.Status;
import airport.core.models.Location;
import airport.core.models.storageManagement.GetItem;
import java.util.ArrayList;

/**
 *
 * @author Karoll
 */
public class LoadLocationData {
    private final GetItem<Location> locationGetter;

    public LoadLocationData(GetItem<Location> locationGetter) {
        this.locationGetter = locationGetter;
    }

    public Response execute() {
        try {
            ArrayList<Location> original = locationGetter.getOrderedItems();
            ArrayList<Location> copy = new ArrayList<>();

            for (Location p : original) {
                copy.add(new Location(
                    p.getAirportId(),
                    p.getAirportName(),
                    p.getAirportCity(),
                    p.getAirportCountry(),
                    p.getAirportLatitude(),
                    p.getAirportLongitude()
                ));
            }

            return new Response("Locations loaded successfully", Status.OK, copy);

        } catch (Exception e) {
            return new Response("Error loading passengers", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
