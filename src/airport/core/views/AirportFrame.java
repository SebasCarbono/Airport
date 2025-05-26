/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package airport.core.views;

import airport.core.controllers.passengerControllers.CreatePassenger;
import airport.core.controllers.airplaneControllers.CreateAirplane;
import airport.core.controllers.airplaneControllers.LoadPlaneData;
import airport.core.controllers.flightControllers.AddToFlight;
import airport.core.controllers.flightControllers.CreateFlight;
import airport.core.controllers.flightControllers.DelayFlight;
import airport.core.controllers.flightControllers.LoadFlightData;
import airport.core.controllers.flightControllers.LoadFlightsByPassenger;
import airport.core.controllers.locationControllers.CreateLocation;
import airport.core.controllers.locationControllers.LoadLocationData;
import airport.core.controllers.passengerControllers.LoadPassengerData;
import airport.core.controllers.passengerControllers.UpdatePassenger;
import airport.core.controllers.utils.Response;
import airport.core.models.Passenger;
import airport.core.models.Plane;
import airport.core.models.Flight;
import airport.core.models.Location;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edangulo
 */
public class AirportFrame extends javax.swing.JFrame {

    /**
     * Creates new form AirportFrame
     */
    private int x, y;
    private ArrayList<Passenger> passengers;
    private ArrayList<Plane> planes;
    private ArrayList<Location> locations;
    private ArrayList<Flight> flights;
    private final CreatePassenger createPassengerController;
    private final UpdatePassenger updatePassengerController;
    private final LoadPassengerData loadPassengersController;
    private final CreateAirplane createAirplaneController;
    private final LoadPlaneData loadPlanesController;
    private final LoadFlightData loadFlightsController;
    private final LoadFlightsByPassenger loadFlightsByPassenger;
    private final CreateFlight createFlightController;
    private final DelayFlight delayFlightController;
    private final AddToFlight addToFlightController;
    private final CreateLocation createLocationController;
    private final LoadLocationData loadLocationsController;

    public AirportFrame(CreatePassenger createPassengerController, UpdatePassenger updatePassengerController,LoadPassengerData loadPassengersController, CreateAirplane createAirplaneController, LoadPlaneData loadPlanesController, LoadFlightData loadFlightsController, LoadFlightsByPassenger loadFlightsByPassenger, CreateFlight createFlightController, DelayFlight delayFlightController, AddToFlight addToFlightController, LoadLocationData loadLocationsController, CreateLocation createLocationController) {
        this.createPassengerController = createPassengerController;
        this.updatePassengerController = updatePassengerController;
        this.loadPassengersController = loadPassengersController;
        this.createAirplaneController = createAirplaneController;
        this.loadPlanesController = loadPlanesController;
        this.loadFlightsController = loadFlightsController;
        this.loadFlightsByPassenger = loadFlightsByPassenger;
        this.createFlightController = createFlightController;
        this.delayFlightController = delayFlightController;
        this.addToFlightController = addToFlightController;
        this.loadLocationsController = loadLocationsController;
        this.createLocationController = createLocationController;
        initComponents();

        this.passengers = new ArrayList<>();
        this.planes = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.flights = new ArrayList<>();

        this.setBackground(new Color(0, 0, 0, 0));
        this.setLocationRelativeTo(null);

        this.generateMonths();
        this.generateDays();
        this.generateHours();
        this.generateMinutes();
        this.blockPanels();

        Response rPassenger = loadPassengersController.execute();
        if (rPassenger.getStatus() < 400) {
            @SuppressWarnings("unchecked")
            ArrayList<Passenger> passengers = (ArrayList<Passenger>) rPassenger.getObject();
            for (Passenger passenger : passengers) {
                this.userSelectA.addItem(Long.toString(passenger.getId()));
            }
        }
        
        Response rPlane = loadPlanesController.execute();
        if (rPlane.getStatus() < 400) {
            @SuppressWarnings("unchecked")
            ArrayList<Plane> planes = (ArrayList<Plane>) rPlane.getObject();
            for (Plane plane : planes) {
                this.planeSelectFR.addItem(plane.getId());
            }
        }
        
        Response rFlight = loadFlightsController.execute();
        if (rPlane.getStatus() < 400) {
            @SuppressWarnings("unchecked")
            ArrayList<Flight> flights = (ArrayList<Flight>) rFlight.getObject();
            for (Flight flight : flights) {
                String id = flight.getId();
                this.flightSelectATF.addItem(id);
                this.idSelectDF.addItem(id);
            }
        }
        
        Response rLocation = loadLocationsController.execute();
        if (rLocation.getStatus() < 400) {
            @SuppressWarnings("unchecked")
            ArrayList<Location> locations = (ArrayList<Location>) rLocation.getObject();
            for (Location location : locations) {
                String id = location.getAirportId();
                this.departureLocationSelectFR.addItem(id);
                this.arrivalLocationFR.addItem(id);
                this.scaleLocationSelectFR.addItem(id);
            }
        }
    }

    private void blockPanels() {
        //9, 11
        for (int i = 1; i < mainTabbedPane.getTabCount(); i++) {
            if (i != 9 && i != 11) {
                mainTabbedPane.setEnabledAt(i, false);
            }
        }
    }

    private void generateMonths() {
        for (int i = 1; i < 13; i++) {
            monthSelectPR.addItem("" + i);
            departureMonthSelectFR.addItem("" + i);
            monthSelectUI.addItem("" + i);
        }
    }

    private void generateDays() {
        for (int i = 1; i < 32; i++) {
            daySelectPR.addItem("" + i);
            departureDaySelectFR.addItem("" + i);
            daySelectUI.addItem("" + i);
        }
    }

    private void generateHours() {
        for (int i = 0; i < 24; i++) {
            departureHourSelectFR.addItem("" + i);
            arrivalHourSelectFR.addItem("" + i);
            scaleHourSelectFR.addItem("" + i);
            hourSelectDF.addItem("" + i);
        }
    }

    private void generateMinutes() {
        for (int i = 0; i < 60; i++) {
            departureMinuteSelectFR.addItem("" + i);
            arrivalMinuteSelectFR.addItem("" + i);
            scaleMinuteSelectFR.addItem("" + i);
            minuteSelectDF.addItem("" + i);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        containerPanel = new airport.core.views.PanelRound();
        supPanelRound = new airport.core.views.PanelRound();
        exitButton = new javax.swing.JButton();
        mainTabbedPane = new javax.swing.JTabbedPane();
        administrationPanel = new javax.swing.JPanel();
        userButtonA = new javax.swing.JRadioButton();
        adminButtonA = new javax.swing.JRadioButton();
        userSelectA = new javax.swing.JComboBox<>();
        PassRegistrationPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        phoneCodeFieldPR = new javax.swing.JTextField();
        idFieldPR = new javax.swing.JTextField();
        yearFieldPR = new javax.swing.JTextField();
        countryFieldPR = new javax.swing.JTextField();
        phoneFieldPR = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lastNameFieldPR = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        monthSelectPR = new javax.swing.JComboBox<>();
        firstNameFieldPR = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        daySelectPR = new javax.swing.JComboBox<>();
        registerButtonPR = new javax.swing.JButton();
        airplaneRegistrationPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        idFieldAR = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        brandFieldAR = new javax.swing.JTextField();
        modelFieldAR = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        maxCapFieldAR = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        airlineFieldAR = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        createButtonAR = new javax.swing.JButton();
        LocationRegistrationPanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        airportFieldLR = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        airportNameFieldLR = new javax.swing.JTextField();
        airportCityFieldLR = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        airportCountryFieldLR = new javax.swing.JTextField();
        airportLatitudeFieldLR = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        airportLongitudeFieldLR = new javax.swing.JTextField();
        createButtonLR = new javax.swing.JButton();
        flightRegistrationPanel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        idFieldFR = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        planeSelectFR = new javax.swing.JComboBox<>();
        departureLocationSelectFR = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        arrivalLocationFR = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        scaleLocationSelectFR = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        departureYearFieldFR = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        departureMonthSelectFR = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        departureDaySelectFR = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        departureHourSelectFR = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        departureMinuteSelectFR = new javax.swing.JComboBox<>();
        arrivalHourSelectFR = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        arrivalMinuteSelectFR = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        scaleHourSelectFR = new javax.swing.JComboBox<>();
        scaleMinuteSelectFR = new javax.swing.JComboBox<>();
        createButtonFR = new javax.swing.JButton();
        updateInfoPanel = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        idFieldUI = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        firstNameFieldUI = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        lastNameFieldUI = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        yearFieldUI = new javax.swing.JTextField();
        monthSelectUI = new javax.swing.JComboBox<>();
        daySelectUI = new javax.swing.JComboBox<>();
        phoneFieldUI = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        phoneCodeFieldUI = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        countryFieldUI = new javax.swing.JTextField();
        updateButtonUI = new javax.swing.JButton();
        addToFlightPanel = new javax.swing.JPanel();
        idFieldATF = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        flightSelectATF = new javax.swing.JComboBox<>();
        addButtonATF = new javax.swing.JButton();
        showMyFlightsPanel = new javax.swing.JPanel();
        scrollPanelSMF = new javax.swing.JScrollPane();
        tableSMF = new javax.swing.JTable();
        refreshButtonSMF = new javax.swing.JButton();
        ShowAllPassengersPanel = new javax.swing.JPanel();
        scrollPanelSAPass = new javax.swing.JScrollPane();
        tableSAPass = new javax.swing.JTable();
        refreshButtonSAPass = new javax.swing.JButton();
        showAllFlightsPanel = new javax.swing.JPanel();
        scrollPanelSAF = new javax.swing.JScrollPane();
        tableSAF = new javax.swing.JTable();
        refreshButtonSAF = new javax.swing.JButton();
        showAllPlanesPanel = new javax.swing.JPanel();
        refreshButtonSAPlanes = new javax.swing.JButton();
        scrollPanelSAPlanes = new javax.swing.JScrollPane();
        tableSAPlanes = new javax.swing.JTable();
        showAllLocations = new javax.swing.JPanel();
        scrollPanelSAL = new javax.swing.JScrollPane();
        tableSAL = new javax.swing.JTable();
        refreshButtonSAL = new javax.swing.JButton();
        delayFlightPanel = new javax.swing.JPanel();
        hourSelectDF = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        idSelectDF = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        minuteSelectDF = new javax.swing.JComboBox<>();
        delayButtonDF = new javax.swing.JButton();
        infPanelRound = new airport.core.views.PanelRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        containerPanel.setRadius(40);
        containerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        supPanelRound.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                supPanelRoundMouseDragged(evt);
            }
        });
        supPanelRound.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                supPanelRoundMousePressed(evt);
            }
        });

        exitButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        exitButton.setText("X");
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout supPanelRoundLayout = new javax.swing.GroupLayout(supPanelRound);
        supPanelRound.setLayout(supPanelRoundLayout);
        supPanelRoundLayout.setHorizontalGroup(
            supPanelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, supPanelRoundLayout.createSequentialGroup()
                .addContainerGap(1083, Short.MAX_VALUE)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        supPanelRoundLayout.setVerticalGroup(
            supPanelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supPanelRoundLayout.createSequentialGroup()
                .addComponent(exitButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        containerPanel.add(supPanelRound, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, -1));

        mainTabbedPane.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N

        administrationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userButtonA.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        userButtonA.setText("User");
        userButtonA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userButtonAActionPerformed(evt);
            }
        });
        administrationPanel.add(userButtonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, -1, -1));

        adminButtonA.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        adminButtonA.setText("Administrator");
        adminButtonA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminButtonAActionPerformed(evt);
            }
        });
        administrationPanel.add(adminButtonA, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 164, -1, -1));

        userSelectA.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        userSelectA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select User" }));
        userSelectA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userSelectAActionPerformed(evt);
            }
        });
        administrationPanel.add(userSelectA, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, 130, -1));

        mainTabbedPane.addTab("Administration", administrationPanel);

        PassRegistrationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel1.setText("Country:");
        PassRegistrationPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, -1, -1));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel2.setText("ID:");
        PassRegistrationPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel3.setText("First Name:");
        PassRegistrationPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel4.setText("Last Name:");
        PassRegistrationPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel5.setText("Birthdate:");
        PassRegistrationPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel6.setText("+");
        PassRegistrationPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 20, -1));

        phoneCodeFieldPR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassRegistrationPanel.add(phoneCodeFieldPR, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 50, -1));

        idFieldPR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassRegistrationPanel.add(idFieldPR, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 130, -1));

        yearFieldPR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassRegistrationPanel.add(yearFieldPR, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 90, -1));

        countryFieldPR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassRegistrationPanel.add(countryFieldPR, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 130, -1));

        phoneFieldPR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassRegistrationPanel.add(phoneFieldPR, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 130, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel7.setText("Phone:");
        PassRegistrationPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel8.setText("-");
        PassRegistrationPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 30, -1));

        lastNameFieldPR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassRegistrationPanel.add(lastNameFieldPR, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 130, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel9.setText("-");
        PassRegistrationPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 30, -1));

        monthSelectPR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        monthSelectPR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));
        monthSelectPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthSelectPRActionPerformed(evt);
            }
        });
        PassRegistrationPanel.add(monthSelectPR, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

        firstNameFieldPR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        PassRegistrationPanel.add(firstNameFieldPR, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 130, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel10.setText("-");
        PassRegistrationPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 30, -1));

        daySelectPR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        daySelectPR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));
        PassRegistrationPanel.add(daySelectPR, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, -1, -1));

        registerButtonPR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        registerButtonPR.setText("Register");
        registerButtonPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonPRActionPerformed(evt);
            }
        });
        PassRegistrationPanel.add(registerButtonPR, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 480, -1, -1));

        mainTabbedPane.addTab("Passenger registration", PassRegistrationPanel);

        airplaneRegistrationPanel.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel11.setText("ID:");
        airplaneRegistrationPanel.add(jLabel11);
        jLabel11.setBounds(53, 96, 22, 25);

        idFieldAR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        airplaneRegistrationPanel.add(idFieldAR);
        idFieldAR.setBounds(180, 93, 130, 31);

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel12.setText("Brand:");
        airplaneRegistrationPanel.add(jLabel12);
        jLabel12.setBounds(53, 157, 50, 25);

        brandFieldAR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        airplaneRegistrationPanel.add(brandFieldAR);
        brandFieldAR.setBounds(180, 154, 130, 31);

        modelFieldAR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        airplaneRegistrationPanel.add(modelFieldAR);
        modelFieldAR.setBounds(180, 213, 130, 31);

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel13.setText("Model:");
        airplaneRegistrationPanel.add(jLabel13);
        jLabel13.setBounds(53, 216, 55, 25);

        maxCapFieldAR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        airplaneRegistrationPanel.add(maxCapFieldAR);
        maxCapFieldAR.setBounds(180, 273, 130, 31);

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel14.setText("Max Capacity:");
        airplaneRegistrationPanel.add(jLabel14);
        jLabel14.setBounds(53, 276, 109, 25);

        airlineFieldAR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        airplaneRegistrationPanel.add(airlineFieldAR);
        airlineFieldAR.setBounds(180, 333, 130, 31);

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setText("Airline:");
        airplaneRegistrationPanel.add(jLabel15);
        jLabel15.setBounds(53, 336, 70, 25);

        createButtonAR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        createButtonAR.setText("Create");
        createButtonAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonARActionPerformed(evt);
            }
        });
        airplaneRegistrationPanel.add(createButtonAR);
        createButtonAR.setBounds(490, 480, 120, 40);

        mainTabbedPane.addTab("Airplane registration", airplaneRegistrationPanel);

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel16.setText("Airport ID:");

        airportFieldLR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel17.setText("Airport name:");

        airportNameFieldLR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        airportCityFieldLR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel18.setText("Airport city:");

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel19.setText("Airport country:");

        airportCountryFieldLR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        airportLatitudeFieldLR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel20.setText("Airport latitude:");

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel21.setText("Airport longitude:");

        airportLongitudeFieldLR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        createButtonLR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        createButtonLR.setText("Create");
        createButtonLR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonLRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LocationRegistrationPanelLayout = new javax.swing.GroupLayout(LocationRegistrationPanel);
        LocationRegistrationPanel.setLayout(LocationRegistrationPanelLayout);
        LocationRegistrationPanelLayout.setHorizontalGroup(
            LocationRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LocationRegistrationPanelLayout.createSequentialGroup()
                .addGroup(LocationRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LocationRegistrationPanelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(LocationRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(80, 80, 80)
                        .addGroup(LocationRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(airportLongitudeFieldLR, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(airportFieldLR, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(airportNameFieldLR, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(airportCityFieldLR, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(airportCountryFieldLR, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(airportLatitudeFieldLR, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(LocationRegistrationPanelLayout.createSequentialGroup()
                        .addGap(515, 515, 515)
                        .addComponent(createButtonLR, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(515, 515, 515))
        );
        LocationRegistrationPanelLayout.setVerticalGroup(
            LocationRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LocationRegistrationPanelLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(LocationRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(LocationRegistrationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel17)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel18)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel19)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel20))
                    .addGroup(LocationRegistrationPanelLayout.createSequentialGroup()
                        .addComponent(airportFieldLR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(airportNameFieldLR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(airportCityFieldLR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(airportCountryFieldLR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(airportLatitudeFieldLR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(LocationRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(airportLongitudeFieldLR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(createButtonLR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        mainTabbedPane.addTab("Location registration", LocationRegistrationPanel);

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel22.setText("ID:");

        idFieldFR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel23.setText("Plane:");

        planeSelectFR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        planeSelectFR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plane" }));

        departureLocationSelectFR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        departureLocationSelectFR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel24.setText("Departure location:");

        arrivalLocationFR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        arrivalLocationFR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel25.setText("Arrival location:");

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel26.setText("Scale location:");

        scaleLocationSelectFR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        scaleLocationSelectFR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));

        jLabel27.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel27.setText("Duration:");

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel28.setText("Duration:");

        jLabel29.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel29.setText("Departure date:");

        departureYearFieldFR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel30.setText("-");

        departureMonthSelectFR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        departureMonthSelectFR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));

        jLabel31.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel31.setText("-");

        departureDaySelectFR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        departureDaySelectFR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));

        jLabel32.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel32.setText("-");

        departureHourSelectFR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        departureHourSelectFR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel33.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel33.setText("-");

        departureMinuteSelectFR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        departureMinuteSelectFR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        arrivalHourSelectFR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        arrivalHourSelectFR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel34.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel34.setText("-");

        arrivalMinuteSelectFR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        arrivalMinuteSelectFR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        jLabel35.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel35.setText("-");

        scaleHourSelectFR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        scaleHourSelectFR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        scaleMinuteSelectFR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        scaleMinuteSelectFR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        createButtonFR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        createButtonFR.setText("Create");
        createButtonFR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonFRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout flightRegistrationPanelLayout = new javax.swing.GroupLayout(flightRegistrationPanel);
        flightRegistrationPanel.setLayout(flightRegistrationPanelLayout);
        flightRegistrationPanelLayout.setHorizontalGroup(
            flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scaleLocationSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, flightRegistrationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(arrivalLocationFR, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(46, 46, 46)
                        .addComponent(departureLocationSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                        .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(idFieldFR)
                            .addComponent(planeSelectFR, 0, 130, Short.MAX_VALUE))))
                .addGap(45, 45, 45)
                .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                        .addComponent(departureYearFieldFR, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(departureMonthSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(departureDaySelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(departureHourSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(departureMinuteSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))
                    .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                        .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                                .addComponent(arrivalHourSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(arrivalMinuteSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                                .addComponent(scaleHourSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(scaleMinuteSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, flightRegistrationPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(createButtonFR, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(530, 530, 530))
        );
        flightRegistrationPanelLayout.setVerticalGroup(
            flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel22))
                    .addComponent(idFieldFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(planeSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(departureHourSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(departureMinuteSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(flightRegistrationPanelLayout.createSequentialGroup()
                        .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel24)
                                .addComponent(departureLocationSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel29))
                            .addComponent(departureYearFieldFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(departureMonthSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(departureDaySelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(arrivalLocationFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28))
                            .addComponent(arrivalHourSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(arrivalMinuteSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scaleHourSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(scaleMinuteSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(flightRegistrationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(scaleLocationSelectFR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                .addComponent(createButtonFR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        mainTabbedPane.addTab("Flight registration", flightRegistrationPanel);

        jLabel36.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel36.setText("ID:");

        idFieldUI.setEditable(false);
        idFieldUI.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        idFieldUI.setEnabled(false);

        jLabel37.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel37.setText("First Name:");

        firstNameFieldUI.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel38.setText("Last Name:");

        lastNameFieldUI.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel39.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel39.setText("Birthdate:");

        yearFieldUI.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        monthSelectUI.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        monthSelectUI.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));

        daySelectUI.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        daySelectUI.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));

        phoneFieldUI.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel40.setText("-");

        phoneCodeFieldUI.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel41.setText("+");

        jLabel42.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel42.setText("Phone:");

        jLabel43.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel43.setText("Country:");

        countryFieldUI.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        updateButtonUI.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        updateButtonUI.setText("Update");
        updateButtonUI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonUIActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateInfoPanelLayout = new javax.swing.GroupLayout(updateInfoPanel);
        updateInfoPanel.setLayout(updateInfoPanelLayout);
        updateInfoPanelLayout.setHorizontalGroup(
            updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateInfoPanelLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addGap(108, 108, 108)
                                .addComponent(idFieldUI, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(41, 41, 41)
                                .addComponent(firstNameFieldUI, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(43, 43, 43)
                                .addComponent(lastNameFieldUI, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(55, 55, 55)
                                .addComponent(yearFieldUI, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(monthSelectUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(daySelectUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(phoneCodeFieldUI, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(phoneFieldUI, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addGap(63, 63, 63)
                                .addComponent(countryFieldUI, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(updateInfoPanelLayout.createSequentialGroup()
                        .addGap(507, 507, 507)
                        .addComponent(updateButtonUI)))
                .addContainerGap(586, Short.MAX_VALUE))
        );
        updateInfoPanelLayout.setVerticalGroup(
            updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(idFieldUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(firstNameFieldUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(lastNameFieldUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(yearFieldUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthSelectUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(daySelectUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jLabel41)
                    .addComponent(phoneCodeFieldUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(phoneFieldUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(countryFieldUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(updateButtonUI)
                .addGap(113, 113, 113))
        );

        mainTabbedPane.addTab("Update info", updateInfoPanel);

        idFieldATF.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        idFieldATF.setEnabled(false);

        jLabel44.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel44.setText("ID:");

        jLabel45.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel45.setText("Flight:");

        flightSelectATF.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        flightSelectATF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Flight" }));

        addButtonATF.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        addButtonATF.setText("Add");
        addButtonATF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonATFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addToFlightPanelLayout = new javax.swing.GroupLayout(addToFlightPanel);
        addToFlightPanel.setLayout(addToFlightPanelLayout);
        addToFlightPanelLayout.setHorizontalGroup(
            addToFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addToFlightPanelLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(addToFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45))
                .addGap(79, 79, 79)
                .addGroup(addToFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(flightSelectATF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idFieldATF, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(860, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addToFlightPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addButtonATF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(509, 509, 509))
        );
        addToFlightPanelLayout.setVerticalGroup(
            addToFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addToFlightPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(addToFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addToFlightPanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel44))
                    .addComponent(idFieldATF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(addToFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(flightSelectATF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
                .addComponent(addButtonATF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );

        mainTabbedPane.addTab("Add to flight", addToFlightPanel);

        tableSMF.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tableSMF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Departure Date", "Arrival Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPanelSMF.setViewportView(tableSMF);

        refreshButtonSMF.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        refreshButtonSMF.setText("Refresh");
        refreshButtonSMF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonSMFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout showMyFlightsPanelLayout = new javax.swing.GroupLayout(showMyFlightsPanel);
        showMyFlightsPanel.setLayout(showMyFlightsPanelLayout);
        showMyFlightsPanelLayout.setHorizontalGroup(
            showMyFlightsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showMyFlightsPanelLayout.createSequentialGroup()
                .addGroup(showMyFlightsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(showMyFlightsPanelLayout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(scrollPanelSMF, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(showMyFlightsPanelLayout.createSequentialGroup()
                        .addGap(521, 521, 521)
                        .addComponent(refreshButtonSMF)))
                .addContainerGap(322, Short.MAX_VALUE))
        );
        showMyFlightsPanelLayout.setVerticalGroup(
            showMyFlightsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showMyFlightsPanelLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(scrollPanelSMF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(refreshButtonSMF)
                .addContainerGap())
        );

        mainTabbedPane.addTab("Show my flights", showMyFlightsPanel);

        tableSAPass.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tableSAPass.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Birthdate", "Age", "Phone", "Country", "Num Flight"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPanelSAPass.setViewportView(tableSAPass);

        refreshButtonSAPass.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        refreshButtonSAPass.setText("Refresh");
        refreshButtonSAPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonSAPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ShowAllPassengersPanelLayout = new javax.swing.GroupLayout(ShowAllPassengersPanel);
        ShowAllPassengersPanel.setLayout(ShowAllPassengersPanelLayout);
        ShowAllPassengersPanelLayout.setHorizontalGroup(
            ShowAllPassengersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowAllPassengersPanelLayout.createSequentialGroup()
                .addGroup(ShowAllPassengersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ShowAllPassengersPanelLayout.createSequentialGroup()
                        .addGap(489, 489, 489)
                        .addComponent(refreshButtonSAPass))
                    .addGroup(ShowAllPassengersPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(scrollPanelSAPass, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        ShowAllPassengersPanelLayout.setVerticalGroup(
            ShowAllPassengersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ShowAllPassengersPanelLayout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(scrollPanelSAPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(refreshButtonSAPass)
                .addContainerGap())
        );

        mainTabbedPane.addTab("Show all passengers", ShowAllPassengersPanel);

        tableSAF.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tableSAF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Departure Airport ID", "Arrival Airport ID", "Scale Airport ID", "Departure Date", "Arrival Date", "Plane ID", "Number Passengers"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPanelSAF.setViewportView(tableSAF);

        refreshButtonSAF.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        refreshButtonSAF.setText("Refresh");
        refreshButtonSAF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonSAFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout showAllFlightsPanelLayout = new javax.swing.GroupLayout(showAllFlightsPanel);
        showAllFlightsPanel.setLayout(showAllFlightsPanelLayout);
        showAllFlightsPanelLayout.setHorizontalGroup(
            showAllFlightsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showAllFlightsPanelLayout.createSequentialGroup()
                .addGroup(showAllFlightsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(showAllFlightsPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(scrollPanelSAF, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(showAllFlightsPanelLayout.createSequentialGroup()
                        .addGap(521, 521, 521)
                        .addComponent(refreshButtonSAF)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        showAllFlightsPanelLayout.setVerticalGroup(
            showAllFlightsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showAllFlightsPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(scrollPanelSAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(refreshButtonSAF)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Show all flights", showAllFlightsPanel);

        refreshButtonSAPlanes.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        refreshButtonSAPlanes.setText("Refresh");
        refreshButtonSAPlanes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonSAPlanesActionPerformed(evt);
            }
        });

        tableSAPlanes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Brand", "Model", "Max Capacity", "Airline", "Number Flights"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPanelSAPlanes.setViewportView(tableSAPlanes);

        javax.swing.GroupLayout showAllPlanesPanelLayout = new javax.swing.GroupLayout(showAllPlanesPanel);
        showAllPlanesPanel.setLayout(showAllPlanesPanelLayout);
        showAllPlanesPanelLayout.setHorizontalGroup(
            showAllPlanesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showAllPlanesPanelLayout.createSequentialGroup()
                .addGroup(showAllPlanesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(showAllPlanesPanelLayout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(refreshButtonSAPlanes))
                    .addGroup(showAllPlanesPanelLayout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(scrollPanelSAPlanes, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(220, Short.MAX_VALUE))
        );
        showAllPlanesPanelLayout.setVerticalGroup(
            showAllPlanesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showAllPlanesPanelLayout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(scrollPanelSAPlanes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(refreshButtonSAPlanes)
                .addGap(17, 17, 17))
        );

        mainTabbedPane.addTab("Show all planes", showAllPlanesPanel);

        tableSAL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Airport ID", "Airport Name", "City", "Country"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPanelSAL.setViewportView(tableSAL);

        refreshButtonSAL.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        refreshButtonSAL.setText("Refresh");
        refreshButtonSAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonSALActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout showAllLocationsLayout = new javax.swing.GroupLayout(showAllLocations);
        showAllLocations.setLayout(showAllLocationsLayout);
        showAllLocationsLayout.setHorizontalGroup(
            showAllLocationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showAllLocationsLayout.createSequentialGroup()
                .addGroup(showAllLocationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(showAllLocationsLayout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(refreshButtonSAL))
                    .addGroup(showAllLocationsLayout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(scrollPanelSAL, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(303, Short.MAX_VALUE))
        );
        showAllLocationsLayout.setVerticalGroup(
            showAllLocationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showAllLocationsLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(scrollPanelSAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(refreshButtonSAL)
                .addGap(17, 17, 17))
        );

        mainTabbedPane.addTab("Show all locations", showAllLocations);

        hourSelectDF.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        hourSelectDF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel46.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel46.setText("Hours:");

        jLabel47.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel47.setText("ID:");

        idSelectDF.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        idSelectDF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID" }));

        jLabel48.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel48.setText("Minutes:");

        minuteSelectDF.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        minuteSelectDF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        delayButtonDF.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        delayButtonDF.setText("Delay");
        delayButtonDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delayButtonDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout delayFlightPanelLayout = new javax.swing.GroupLayout(delayFlightPanel);
        delayFlightPanel.setLayout(delayFlightPanelLayout);
        delayFlightPanelLayout.setHorizontalGroup(
            delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(delayFlightPanelLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(delayFlightPanelLayout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(minuteSelectDF, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(delayFlightPanelLayout.createSequentialGroup()
                        .addGroup(delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel46))
                        .addGap(79, 79, 79)
                        .addGroup(delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hourSelectDF, 0, 136, Short.MAX_VALUE)
                            .addComponent(idSelectDF, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(820, 820, 820))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, delayFlightPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(delayButtonDF)
                .addGap(531, 531, 531))
        );
        delayFlightPanelLayout.setVerticalGroup(
            delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(delayFlightPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(idSelectDF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(hourSelectDF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(minuteSelectDF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 317, Short.MAX_VALUE)
                .addComponent(delayButtonDF)
                .addGap(33, 33, 33))
        );

        mainTabbedPane.addTab("Delay flight", delayFlightPanel);

        containerPanel.add(mainTabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, -1, 630));

        javax.swing.GroupLayout infPanelRoundLayout = new javax.swing.GroupLayout(infPanelRound);
        infPanelRound.setLayout(infPanelRoundLayout);
        infPanelRoundLayout.setHorizontalGroup(
            infPanelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        infPanelRoundLayout.setVerticalGroup(
            infPanelRoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        containerPanel.add(infPanelRound, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 660, 1150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(containerPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(containerPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void supPanelRoundMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supPanelRoundMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_supPanelRoundMousePressed

    private void supPanelRoundMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supPanelRoundMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_supPanelRoundMouseDragged

    private void adminButtonAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminButtonAActionPerformed
        if (userButtonA.isSelected()) {
            userButtonA.setSelected(false);
            userSelectA.setSelectedIndex(0);
        }
        for (int i = 1; i < mainTabbedPane.getTabCount(); i++) {
                mainTabbedPane.setEnabledAt(i, true);
        }
        mainTabbedPane.setEnabledAt(5, false);
        mainTabbedPane.setEnabledAt(6, false);
        mainTabbedPane.setEnabledAt(7, false);
        
        if(!adminButtonA.isSelected() && !userButtonA.isSelected()){
            for (int i = 0; i < mainTabbedPane.getTabCount(); i++) {
                mainTabbedPane.setEnabledAt(i, false);
            }
            mainTabbedPane.setEnabledAt(9, true);
            mainTabbedPane.setEnabledAt(11, true);
        }
    }//GEN-LAST:event_adminButtonAActionPerformed

    private void userButtonAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userButtonAActionPerformed
        if (adminButtonA.isSelected()) {
            adminButtonA.setSelected(false);
        }
        for (int i = 1; i < mainTabbedPane.getTabCount(); i++) {

            mainTabbedPane.setEnabledAt(i, false);

        }
        mainTabbedPane.setEnabledAt(9, true);
        mainTabbedPane.setEnabledAt(5, true);
        mainTabbedPane.setEnabledAt(6, true);
        mainTabbedPane.setEnabledAt(7, true);
        mainTabbedPane.setEnabledAt(11, true);
        
        if(!adminButtonA.isSelected() && !userButtonA.isSelected()){
            for (int i = 0; i < mainTabbedPane.getTabCount(); i++) {
                mainTabbedPane.setEnabledAt(i, false);
            }
            mainTabbedPane.setEnabledAt(9, true);
            mainTabbedPane.setEnabledAt(11, true);
        }
    }//GEN-LAST:event_userButtonAActionPerformed

    private void registerButtonPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonPRActionPerformed
        // TODO add your handling code here:
        String id = idFieldPR.getText();
        String firstname = firstNameFieldPR.getText();
        String lastname = lastNameFieldPR.getText();
        String year = yearFieldPR.getText();
        String month = monthSelectPR.getItemAt(monthSelectPR.getSelectedIndex());
        String day = daySelectPR.getItemAt(daySelectPR.getSelectedIndex());
        String phoneCode = phoneCodeFieldPR.getText();
        String phone = phoneFieldPR.getText();
        String country = countryFieldPR.getText();

        Response response = createPassengerController.execute(id, firstname, lastname, year, month, day, phoneCode, phone, country);
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);

            this.userSelectA.addItem("" + id);
            
            countryFieldPR.setText("");
            phoneFieldPR.setText("");
            phoneCodeFieldPR.setText("");
            yearFieldPR.setText("");
            lastNameFieldPR.setText("");
            firstNameFieldPR.setText("");
            idFieldPR.setText("");
            
            daySelectPR.setSelectedIndex(0);
            monthSelectPR.setSelectedIndex(0);
                        
        }

    }//GEN-LAST:event_registerButtonPRActionPerformed

    private void createButtonARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonARActionPerformed
        // TODO add your handling code here:
        String id = idFieldAR.getText();
        String brand = brandFieldAR.getText();
        String model = modelFieldAR.getText();
        String maxCapacity = maxCapFieldAR.getText();
        String airline = airlineFieldAR.getText();
        
        Response response = createAirplaneController.execute(id, brand, model, maxCapacity, airline);
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
            this.planeSelectFR.addItem(id);
            
            idFieldAR.setText("");
            brandFieldAR.setText("");
            modelFieldAR.setText("");
            maxCapFieldAR.setText("");
            airlineFieldAR.setText("");
        }
    }//GEN-LAST:event_createButtonARActionPerformed

    private void createButtonLRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonLRActionPerformed
        // TODO add your handling code here:
        String id = airportFieldLR.getText();
        String name = airportNameFieldLR.getText();
        String city = airportCityFieldLR.getText();
        String country = airportCountryFieldLR.getText();
        String latitude = airportLatitudeFieldLR.getText();
        String longitude = airportLongitudeFieldLR.getText();

        Response response = createLocationController.execute(id, name, city, country, latitude, longitude);
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);

            this.departureLocationSelectFR.addItem(id);
            this.arrivalLocationFR.addItem(id);
            this.scaleLocationSelectFR.addItem(id);

            airportFieldLR.setText("");
            airportNameFieldLR.setText("");
            airportCityFieldLR.setText("");
            airportCountryFieldLR.setText("");
            airportLatitudeFieldLR.setText("");
            airportLongitudeFieldLR.setText("");
        }
    }//GEN-LAST:event_createButtonLRActionPerformed

    private void createButtonFRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonFRActionPerformed
        // TODO add your handling code here:
        String id = idFieldFR.getText();
        String planeId = planeSelectFR.getItemAt(planeSelectFR.getSelectedIndex());
        String departureLocationId = departureLocationSelectFR.getItemAt(departureLocationSelectFR.getSelectedIndex());
        String arrivalLocationId = arrivalLocationFR.getItemAt(arrivalLocationFR.getSelectedIndex());
        String scaleLocationId = scaleLocationSelectFR.getItemAt(scaleLocationSelectFR.getSelectedIndex());
        String year = departureYearFieldFR.getText();
        String month = departureMonthSelectFR.getItemAt(departureMonthSelectFR.getSelectedIndex());
        String day = departureDaySelectFR.getItemAt(departureDaySelectFR.getSelectedIndex());
        String hour = departureHourSelectFR.getItemAt(departureHourSelectFR.getSelectedIndex());
        String minutes = departureMinuteSelectFR.getItemAt(departureMinuteSelectFR.getSelectedIndex());
        String hoursDurationsArrival = arrivalHourSelectFR.getItemAt(arrivalHourSelectFR.getSelectedIndex());
        String minutesDurationsArrival = arrivalMinuteSelectFR.getItemAt(arrivalMinuteSelectFR.getSelectedIndex());
        String hoursDurationsScale = scaleHourSelectFR.getItemAt(scaleHourSelectFR.getSelectedIndex());
        String minutesDurationsScale = scaleMinuteSelectFR.getItemAt(scaleMinuteSelectFR.getSelectedIndex());
        
        Response response = createFlightController.execute(id, planeId, departureLocationId, arrivalLocationId, scaleLocationId, 
          year, month, day, hour, minutes, hoursDurationsArrival, minutesDurationsArrival, hoursDurationsScale, 
          minutesDurationsScale);
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);

            this.flightSelectATF.addItem(id);
            
            idFieldFR.setText("");
            departureYearFieldFR.setText("");
            
            departureLocationSelectFR.setSelectedIndex(0);
            arrivalLocationFR.setSelectedIndex(0);
            scaleLocationSelectFR.setSelectedIndex(0);
            departureMonthSelectFR.setSelectedIndex(0);
            departureDaySelectFR.setSelectedIndex(0);
            departureHourSelectFR.setSelectedIndex(0);
            departureMinuteSelectFR.setSelectedIndex(0);
            arrivalHourSelectFR.setSelectedIndex(0);
            arrivalMinuteSelectFR.setSelectedIndex(0);
            scaleHourSelectFR.setSelectedIndex(0);
            scaleMinuteSelectFR.setSelectedIndex(0);
            planeSelectFR.setSelectedIndex(0);
        }
        
    }//GEN-LAST:event_createButtonFRActionPerformed

    private void updateButtonUIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonUIActionPerformed
        // TODO add your handling code here:
        String id = idFieldUI.getText();
        String firstname = firstNameFieldUI.getText();
        String lastname = lastNameFieldUI.getText();
        String year = yearFieldUI.getText();
        String month = monthSelectPR.getItemAt(monthSelectUI.getSelectedIndex());
        String day = daySelectPR.getItemAt(daySelectUI.getSelectedIndex());
        String phoneCode = phoneCodeFieldUI.getText();
        String phone = phoneFieldUI.getText();
        String country = countryFieldUI.getText();

        Response response = updatePassengerController.execute(id, firstname, lastname, year, month, day, phoneCode, phone, country);
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
            
            firstNameFieldUI.setText("");
            lastNameFieldUI.setText("");
            yearFieldUI.setText("");
            phoneCodeFieldUI.setText("");
            phoneFieldUI.setText("");
            countryFieldUI.setText("");
            
            monthSelectPR.setSelectedIndex(0);
            daySelectPR.setSelectedIndex(0);
            
        }
    }//GEN-LAST:event_updateButtonUIActionPerformed

    private void addButtonATFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonATFActionPerformed
        // TODO add your handling code here:
        String passengerId = idFieldATF.getText();
        String flightId = flightSelectATF.getItemAt(flightSelectATF.getSelectedIndex());
        
        Response response = addToFlightController.execute(passengerId, flightId);
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
            
            idFieldATF.setText("");
            
            flightSelectATF.setSelectedIndex(0);
        }
    }//GEN-LAST:event_addButtonATFActionPerformed

    private void delayButtonDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delayButtonDFActionPerformed
        // TODO add your handling code here:
        String flightId = idSelectDF.getItemAt(idSelectDF.getSelectedIndex());
        String hours = hourSelectDF.getItemAt(hourSelectDF.getSelectedIndex());
        String minutes = minuteSelectDF.getItemAt(minuteSelectDF.getSelectedIndex());

        Response response = delayFlightController.execute(flightId, hours, minutes);
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
            
            idSelectDF.setSelectedIndex(0);
            hourSelectDF.setSelectedIndex(0);
            minuteSelectDF.setSelectedIndex(0);
        }
    }//GEN-LAST:event_delayButtonDFActionPerformed

    private void refreshButtonSMFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonSMFActionPerformed
        // TODO add your handling code here:
        String passengerId = userSelectA.getItemAt(userSelectA.getSelectedIndex());
        
        Response response = loadFlightsByPassenger.execute(passengerId);

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Warning " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
            DefaultTableModel model = (DefaultTableModel) tableSMF.getModel();
            model.setRowCount(0);
        } else {
            DefaultTableModel model = (DefaultTableModel) tableSMF.getModel();
            model.setRowCount(0); // Limpiar tabla

            this.flights = (ArrayList<Flight>) response.getObject();

            for (Flight flight : this.flights) {
                model.addRow(new Object[] {
                    flight.getId(),
                    flight.getDepartureDate(),
                    flight.calculateArrivalDate()
                });
            }

            JOptionPane.showMessageDialog(null, response.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);

        }
    }//GEN-LAST:event_refreshButtonSMFActionPerformed

    @SuppressWarnings("unchecked")
    private void refreshButtonSAPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonSAPassActionPerformed
        // TODO add your handling code here:
        Response response = loadPassengersController.execute();
    
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Advertencia " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            DefaultTableModel model = (DefaultTableModel) tableSAPass.getModel();
            model.setRowCount(0);

            this.passengers = (ArrayList<Passenger>) response.getObject();
            for (Passenger passenger : this.passengers) {
                model.addRow(new Object[]{
                    passenger.getId(),
                    passenger.getFullname(),
                    passenger.getBirthDate(),
                    passenger.calculateAge(),
                    passenger.generateFullPhone(),
                    passenger.getCountry(),
                    passenger.getNumFlights()
                });
            }

            JOptionPane.showMessageDialog(null, response.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_refreshButtonSAPassActionPerformed

    @SuppressWarnings("unchecked")
    private void refreshButtonSAFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonSAFActionPerformed
        // TODO add your handling code here:
        Response response = loadFlightsController.execute();

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Warning " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else{
            DefaultTableModel model = (DefaultTableModel) tableSAF.getModel();
            model.setRowCount(0); // Limpiar tabla

            this.flights = (ArrayList<Flight>) response.getObject();

            for (Flight flight : this.flights) {// Debug individual
                model.addRow(new Object[] {
                    flight.getId(),
                    flight.getDepartureLocation().getAirportId(),
                    flight.getArrivalLocation().getAirportId(),
                    (flight.getScaleLocation() == null ? "-" : flight.getScaleLocation().getAirportId()),
                    flight.getDepartureDate(),
                    flight.calculateArrivalDate(),
                    flight.getPlane().getId(),
                    flight.getNumPassengers()
                });
            }

            JOptionPane.showMessageDialog(null, response.getMessage(), "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_refreshButtonSAFActionPerformed

    @SuppressWarnings("unchecked")
    private void refreshButtonSAPlanesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonSAPlanesActionPerformed
        // TODO add your handling code here:
        Response response = loadPlanesController.execute();
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            DefaultTableModel model = (DefaultTableModel) tableSAPlanes.getModel();
            model.setRowCount(0);
            this.planes = (ArrayList<Plane>) response.getObject();
            for (Plane plane : this.planes) {
                model.addRow(new Object[]{plane.getId(), plane.getBrand(), plane.getModel(), plane.getMaxCapacity(), plane.getAirline(), plane.getNumFlights()});
            }
            
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_refreshButtonSAPlanesActionPerformed

    @SuppressWarnings("unchecked")
    private void refreshButtonSALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonSALActionPerformed
        // TODO add your handling code here:
        
        Response response = loadLocationsController.execute();
        
        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(null, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            DefaultTableModel model = (DefaultTableModel) tableSAL.getModel();
            model.setRowCount(0);
            this.locations = (ArrayList<Location>) response.getObject();
            for (Location location : this.locations) {
                model.addRow(new Object[]{location.getAirportId(), location.getAirportName(), location.getAirportCity(), location.getAirportCountry()});
            }
            
            JOptionPane.showMessageDialog(null, response.getMessage(), "Response Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_refreshButtonSALActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void userSelectAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userSelectAActionPerformed
        try {
            String id = userSelectA.getSelectedItem().toString();
            if (! id.equals(userSelectA.getItemAt(0))) {
                idFieldUI.setText(id);
                idFieldATF.setText(id);
            }
            else{
                idFieldUI.setText("");
                idFieldATF.setText("");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_userSelectAActionPerformed

    private void monthSelectPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthSelectPRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monthSelectPRActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LocationRegistrationPanel;
    private javax.swing.JPanel PassRegistrationPanel;
    private javax.swing.JPanel ShowAllPassengersPanel;
    private javax.swing.JButton addButtonATF;
    private javax.swing.JPanel addToFlightPanel;
    private javax.swing.JRadioButton adminButtonA;
    private javax.swing.JPanel administrationPanel;
    private javax.swing.JTextField airlineFieldAR;
    private javax.swing.JPanel airplaneRegistrationPanel;
    private javax.swing.JTextField airportCityFieldLR;
    private javax.swing.JTextField airportCountryFieldLR;
    private javax.swing.JTextField airportFieldLR;
    private javax.swing.JTextField airportLatitudeFieldLR;
    private javax.swing.JTextField airportLongitudeFieldLR;
    private javax.swing.JTextField airportNameFieldLR;
    private javax.swing.JComboBox<String> arrivalHourSelectFR;
    private javax.swing.JComboBox<String> arrivalLocationFR;
    private javax.swing.JComboBox<String> arrivalMinuteSelectFR;
    private javax.swing.JTextField brandFieldAR;
    private airport.core.views.PanelRound containerPanel;
    private javax.swing.JTextField countryFieldPR;
    private javax.swing.JTextField countryFieldUI;
    private javax.swing.JButton createButtonAR;
    private javax.swing.JButton createButtonFR;
    private javax.swing.JButton createButtonLR;
    private javax.swing.JComboBox<String> daySelectPR;
    private javax.swing.JComboBox<String> daySelectUI;
    private javax.swing.JButton delayButtonDF;
    private javax.swing.JPanel delayFlightPanel;
    private javax.swing.JComboBox<String> departureDaySelectFR;
    private javax.swing.JComboBox<String> departureHourSelectFR;
    private javax.swing.JComboBox<String> departureLocationSelectFR;
    private javax.swing.JComboBox<String> departureMinuteSelectFR;
    private javax.swing.JComboBox<String> departureMonthSelectFR;
    private javax.swing.JTextField departureYearFieldFR;
    private javax.swing.JButton exitButton;
    private javax.swing.JTextField firstNameFieldPR;
    private javax.swing.JTextField firstNameFieldUI;
    private javax.swing.JPanel flightRegistrationPanel;
    private javax.swing.JComboBox<String> flightSelectATF;
    private javax.swing.JComboBox<String> hourSelectDF;
    private javax.swing.JTextField idFieldAR;
    private javax.swing.JTextField idFieldATF;
    private javax.swing.JTextField idFieldFR;
    private javax.swing.JTextField idFieldPR;
    private javax.swing.JTextField idFieldUI;
    private javax.swing.JComboBox<String> idSelectDF;
    private airport.core.views.PanelRound infPanelRound;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField lastNameFieldPR;
    private javax.swing.JTextField lastNameFieldUI;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JTextField maxCapFieldAR;
    private javax.swing.JComboBox<String> minuteSelectDF;
    private javax.swing.JTextField modelFieldAR;
    private javax.swing.JComboBox<String> monthSelectPR;
    private javax.swing.JComboBox<String> monthSelectUI;
    private javax.swing.JTextField phoneCodeFieldPR;
    private javax.swing.JTextField phoneCodeFieldUI;
    private javax.swing.JTextField phoneFieldPR;
    private javax.swing.JTextField phoneFieldUI;
    private javax.swing.JComboBox<String> planeSelectFR;
    private javax.swing.JButton refreshButtonSAF;
    private javax.swing.JButton refreshButtonSAL;
    private javax.swing.JButton refreshButtonSAPass;
    private javax.swing.JButton refreshButtonSAPlanes;
    private javax.swing.JButton refreshButtonSMF;
    private javax.swing.JButton registerButtonPR;
    private javax.swing.JComboBox<String> scaleHourSelectFR;
    private javax.swing.JComboBox<String> scaleLocationSelectFR;
    private javax.swing.JComboBox<String> scaleMinuteSelectFR;
    private javax.swing.JScrollPane scrollPanelSAF;
    private javax.swing.JScrollPane scrollPanelSAL;
    private javax.swing.JScrollPane scrollPanelSAPass;
    private javax.swing.JScrollPane scrollPanelSAPlanes;
    private javax.swing.JScrollPane scrollPanelSMF;
    private javax.swing.JPanel showAllFlightsPanel;
    private javax.swing.JPanel showAllLocations;
    private javax.swing.JPanel showAllPlanesPanel;
    private javax.swing.JPanel showMyFlightsPanel;
    private airport.core.views.PanelRound supPanelRound;
    private javax.swing.JTable tableSAF;
    private javax.swing.JTable tableSAL;
    private javax.swing.JTable tableSAPass;
    private javax.swing.JTable tableSAPlanes;
    private javax.swing.JTable tableSMF;
    private javax.swing.JButton updateButtonUI;
    private javax.swing.JPanel updateInfoPanel;
    private javax.swing.JRadioButton userButtonA;
    private javax.swing.JComboBox<String> userSelectA;
    private javax.swing.JTextField yearFieldPR;
    private javax.swing.JTextField yearFieldUI;
    // End of variables declaration//GEN-END:variables
}
