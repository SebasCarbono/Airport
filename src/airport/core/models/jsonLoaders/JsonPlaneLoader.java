/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.core.models.jsonLoaders;

import airport.core.models.Plane;
import airport.core.models.storageManagement.LoadData;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Karoll
 */
public class JsonPlaneLoader implements LoadData<Plane>{
    
    private final String filePath;

    public JsonPlaneLoader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public ArrayList<Plane> load() {
        ArrayList<Plane> planes = new ArrayList<>();
        try (InputStream is = new FileInputStream(filePath)) {
            JSONArray array = new JSONArray(new JSONTokener(is));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                planes.add(new Plane(
                    obj.getString("id"),
                    obj.getString("brand"),
                    obj.getString("model"),
                    obj.getInt("maxCapacity"),
                    obj.getString("airline")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace(); // puedes agregar logging si lo prefieres
        }
        return planes;
    }
    
}
