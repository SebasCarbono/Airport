/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.main;

import airport.core.controllers.airplaneControllers.CreateAirplane;
import airport.core.controllers.airplaneControllers.LoadPlaneData;
import airport.core.controllers.flightControllers.AddToFlight;
import airport.core.controllers.flightControllers.CreateFlight;
import airport.core.controllers.flightControllers.DelayFlight;
import airport.core.controllers.flightControllers.LoadFlightData;
import airport.core.controllers.flightControllers.LoadFlightsByPassenger;
import airport.core.controllers.locationControllers.CreateLocation;
import airport.core.controllers.locationControllers.LoadLocationData;
import airport.core.controllers.passengerControllers.CreatePassenger;
import airport.core.controllers.passengerControllers.LoadPassengerData;
import airport.core.controllers.passengerControllers.UpdatePassenger;
import airport.core.models.Flight;
import airport.core.models.Location;
import airport.core.models.Passenger;
import airport.core.models.Plane;
import airport.core.models.jsonLoaders.JsonFlightLoader;
import airport.core.models.jsonLoaders.JsonLocationLoader;
import airport.core.models.jsonLoaders.JsonPassengerLoader;
import airport.core.models.jsonLoaders.JsonPlaneLoader;
import airport.core.models.storageManagement.Add;
import airport.core.models.storageManagement.GetItem;
import airport.core.models.storage.AirplaneStorage;
import airport.core.models.storage.FlightStorage;
import airport.core.models.storage.LocationStorage;
import airport.core.models.storage.PassengerStorage;
import airport.core.models.storageManagement.LoadData;
import airport.core.views.AirportFrame;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;

/**
 *
 * @author Sebas
 */
public class Main {
    public static void main(String args[]) {
        System.setProperty("flatlaf.useNativeLibrary", "false");

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
        //---------------------------------passengers-----------------------------------------
        LoadData<Passenger> passengerLoader = new JsonPassengerLoader("json/passengers.json");
        // Obtener instancia única del storage ya inicializado con el loader
        PassengerStorage passengerStorage = PassengerStorage.getInstance(passengerLoader);
        // Controladores que usan Add o GetItem
        CreatePassenger createPassengerController = new CreatePassenger(passengerStorage);
        UpdatePassenger updatePassengerController = new UpdatePassenger(passengerStorage);
        LoadPassengerData loadPassengersController = new LoadPassengerData(passengerStorage);
        

        //---------------------------------airplanes-----------------------------------------
        LoadData<Plane> planeLoader = new JsonPlaneLoader("json/planes.json");
        //instancia de storage
        AirplaneStorage airplaneStorage = AirplaneStorage.getInstance(planeLoader);
        //controladores
        CreateAirplane createAirplaneController = new CreateAirplane(airplaneStorage);
        LoadPlaneData loadPlanesController = new LoadPlaneData(airplaneStorage);
        
        
        //---------------------------------flights-----------------------------------------
        GetItem<Plane> airplaneReader = AirplaneStorage.getInstance(planeLoader);
        LoadData<Location> locationLoader = new JsonLocationLoader("json/locations.json");
        GetItem<Location> locationReader = LocationStorage.getInstance(locationLoader);
        LoadData<Flight> flightLoader = new JsonFlightLoader("json/flights.json", airplaneReader, locationReader);
        GetItem<Flight> flightReader = FlightStorage.getInstance(flightLoader);
        GetItem<Passenger> passengerReader = PassengerStorage.getInstance(passengerLoader);
        Add<Flight> flightWriter = FlightStorage.getInstance(flightLoader);
        
        CreateFlight createFlightController = new CreateFlight(flightWriter, airplaneReader, locationReader);
        DelayFlight delayFlightController = new DelayFlight(flightReader);
        AddToFlight addToFlightController = new AddToFlight(flightReader, passengerReader);
        FlightStorage flightStorage = FlightStorage.getInstance(flightLoader);
        LoadFlightData loadFlightsController = new LoadFlightData(flightStorage);
        LoadFlightsByPassenger loadFlightsByPassenger = new LoadFlightsByPassenger(passengerReader);
        
        
        //---------------------------------locations-----------------------------------------
        Add<Location> locationWriter = LocationStorage.getInstance(locationLoader);
        CreateLocation createLocationController = new CreateLocation(locationWriter);
        
        LocationStorage locationStorage = LocationStorage.getInstance(locationLoader);
        LoadLocationData loadLocationsController = new LoadLocationData(locationStorage);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AirportFrame(createPassengerController, updatePassengerController, loadPassengersController, createAirplaneController, loadPlanesController, loadFlightsController, loadFlightsByPassenger, createFlightController, delayFlightController, addToFlightController, loadLocationsController, createLocationController).setVisible(true);
            }
        });
    }
}
