/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.models.jsonLoaders;

import airport.core.models.Flight;
import airport.core.models.Location;
import airport.core.models.Plane;
import airport.core.models.storageManagement.GetItem;
import airport.core.models.storageManagement.LoadData;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Karoll
 */
public class JsonFlightLoader implements LoadData<Flight>{
    private final String filePath;
    private final GetItem<Plane> airplaneReader;
    private final GetItem<Location> locationReader;

    public JsonFlightLoader(String filePath, GetItem<Plane> airplaneReader, GetItem<Location> locationReader) {
        this.filePath = filePath;
        this.airplaneReader = airplaneReader;
        this.locationReader = locationReader;
    }

    @Override
    public ArrayList<Flight> load() {
        ArrayList<Flight> flights = new ArrayList<>();
        try (InputStream is = new FileInputStream(filePath)) {
            JSONArray array = new JSONArray(new JSONTokener(is));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String id = obj.getString("id");
                Plane plane = airplaneReader.getItem(obj.getString("plane"));
                Location departure = locationReader.getItem(obj.getString("departureLocation"));
                Location arrival = locationReader.getItem(obj.getString("arrivalLocation"));

                // Fijar escala con lógica robusta
                Location scale = null;
                if (!obj.isNull("scaleLocation")) {
                    String scaleCode = obj.getString("scaleLocation");
                    scale = locationReader.getItem(scaleCode);
                }

                LocalDateTime departureDate = LocalDateTime.parse(obj.getString("departureDate"));
                int hoursArrival = obj.getInt("hoursDurationArrival");
                int minutesArrival = obj.getInt("minutesDurationArrival");

                if (scale != null) {
                    int hoursScale = obj.getInt("hoursDurationScale");
                    int minutesScale = obj.getInt("minutesDurationScale");

                    flights.add(new Flight(id, plane, departure, scale, arrival, departureDate,
                            hoursArrival, minutesArrival, hoursScale, minutesScale));
                } else {
                    flights.add(new Flight(id, plane, departure, arrival, departureDate,
                            hoursArrival, minutesArrival));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // puedes usar logger si lo prefieres
        }
        return flights;
    }
}
