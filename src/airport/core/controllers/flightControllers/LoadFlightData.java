/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.controllers.flightControllers;

import airport.core.controllers.utils.Response;
import airport.core.controllers.utils.Status;
import airport.core.models.Flight;
import airport.core.models.storageManagement.GetItem;
import java.util.ArrayList;

/**
 *
 * @author Karoll
 */
public class LoadFlightData {
    
    private final GetItem<Flight> flightGetter;

    @SuppressWarnings("unchecked")
    public LoadFlightData(GetItem flightGetter) {
        this.flightGetter = flightGetter;
    }
    
    public Response execute() {
        try {
            ArrayList<Flight> original = flightGetter.getAllItems();
            ArrayList<Flight> copy = new ArrayList<>();

            for (Flight f : original) {
                if (f.getScaleLocation() != null) {
                    copy.add(new Flight(
                        f.getId(),
                        f.getPlane(),
                        f.getDepartureLocation(),
                        f.getScaleLocation(),
                        f.getArrivalLocation(),
                        f.getDepartureDate(),
                        f.getHoursDurationArrival(),
                        f.getMinutesDurationArrival(),
                        f.getHoursDurationScale(),
                        f.getMinutesDurationScale()
                    ));
                } else {
                    copy.add(new Flight(
                        f.getId(),
                        f.getPlane(),
                        f.getDepartureLocation(),
                        f.getArrivalLocation(),
                        f.getDepartureDate(),
                        f.getHoursDurationArrival(),
                        f.getMinutesDurationArrival()
                    ));
                }
            }

            return new Response("Flights loaded successfully", Status.OK, copy);

        } catch (Exception e) {
            return new Response("Error loading flights", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
