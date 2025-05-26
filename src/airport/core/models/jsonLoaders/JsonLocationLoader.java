/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.models.jsonLoaders;

import airport.core.models.Location;
import airport.core.models.Passenger;
import airport.core.models.storageManagement.LoadData;
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
public class JsonLocationLoader implements LoadData<Location> {

    private final String filePath;

    public JsonLocationLoader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public ArrayList<Location> load() {
        ArrayList<Location> locations = new ArrayList<>();
        try (InputStream is = new FileInputStream(filePath)) {
            JSONArray array = new JSONArray(new JSONTokener(is));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                locations.add(new Location(
                    obj.getString("airportId"),
                    obj.getString("airportName"),
                    obj.getString("airportCity"),
                    obj.getString("airportCountry"),
                    obj.getDouble("airportLatitude"),
                    obj.getDouble("airportLongitude")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(); // puedes agregar logging si lo prefieres
        }
        return locations;
    }
    
    
}
