/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.models.jsonLoaders;

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

public class JsonPassengerLoader implements LoadData<Passenger> {

    private final String filePath;

    public JsonPassengerLoader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public ArrayList<Passenger> load() {
        ArrayList<Passenger> passengers = new ArrayList<>();
        try (InputStream is = new FileInputStream(filePath)) {
            JSONArray array = new JSONArray(new JSONTokener(is));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                passengers.add(new Passenger(
                    obj.getLong("id"),
                    obj.getString("firstname"),
                    obj.getString("lastname"),
                    LocalDate.parse(obj.getString("birthDate")),
                    obj.getInt("countryPhoneCode"),
                    obj.getLong("phone"),
                    obj.getString("country")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(); // puedes agregar logging si lo prefieres
        }
        return passengers;
    }
}