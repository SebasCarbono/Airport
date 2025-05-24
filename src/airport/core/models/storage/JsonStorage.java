/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.models.storage;

import airport.core.controllers.utils.Response;
import airport.core.models.Flight;
import airport.core.models.Location;
import airport.core.models.Passenger;
import airport.core.models.Plane;
import core.controllers.utils.Status;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Karoll
 */
public class JsonStorage {
    
    private static JsonStorage instance;
    
    // Atributos del PassengerStorage
    private ArrayList<Passenger> passengers;    
    private ArrayList<Plane> planes;
    private ArrayList<Location> locations;
    private ArrayList<Flight> flights;

    
    private JsonStorage() {
        this.passengers = new ArrayList<>();
        this.planes = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.flights = new ArrayList<>();
    }
    
    public static JsonStorage getInstance() {
        if (instance == null) {
            instance = new JsonStorage();
        }
        return instance;
    }
    
    public Response loadPassengersFromJson() {
        try (InputStream is = new FileInputStream("json/passengers.json")) {
            if (is == null) {
                return new Response("File passengers.json not found.", Status.NOT_FOUND);
            }

            JSONTokener tokener = new JSONTokener(is);
            JSONArray jsonArray = new JSONArray(tokener);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                Passenger passenger = new Passenger(
                        obj.getLong("id"),
                        obj.getString("firstname"),
                        obj.getString("lastname"),
                        LocalDate.parse(obj.getString("birthDate")),
                        obj.getInt("countryPhoneCode"),
                        obj.getLong("phone"),
                        obj.getString("country")
                );

                // Intentar añadirlo solo si no existe
                this.addPassenger(passenger);
            }

            return new Response("Passengers loaded successfully", Status.OK, this.passengers);

        } catch (Exception e) {
            return new Response("Error reading passengers from JSON", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public boolean addPassenger(Passenger passenger) {
        for (Passenger p : this.passengers) {
            if (p.getId() == passenger.getId()) {
                return false;
            }
        }
        this.passengers.add(passenger);
        return true;
    }    
    
    public ArrayList<Passenger> getPassengers(){
        return this.passengers;
    }
}
