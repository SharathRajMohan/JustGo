import ApplicationExceptions.DriverNotFound;
import ApplicationExceptions.InvalidTripParamException;
import ApplicationExceptions.TripNotFoundException;
import ApplicationExceptions.TripStatusException;
import Utils.InputValidation;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// This is the application mediator class. It holds the objects and data that links application components together.
public class TripManager {
    // Properties
    private HashMap<String,Trip> TripLedger = new HashMap<String, Trip>(); // this is the ledger that holds information of all trips. Note String is used to represent Trip Id.
    private static TripManager TripManagerInstance = null;
    RiderManager rm = RiderManager.getInstance();
    DriverManager dm = DriverManager.getInstance();
    private DefaultStrategyHandler dsh;

    // Methods
    // Constructor
    public TripManager(DefaultStrategyHandler dsh) {
        System.out.println("Trip Manager instantiated.");
        this.dsh = dsh;
    }
    // Singleton
    public static TripManager getInstance(){
        if(TripManagerInstance == null){
            DefaultStrategyHandler dsh = new DefaultStrategyHandler();
            TripManagerInstance = new TripManager(dsh);
        }
        return TripManagerInstance;
    }
    // Fetch trip from ledger
    private Optional<Trip> getTrip(String TripId){
        return this.TripLedger.values().stream().filter(trip -> Objects.equals(trip.getTripId(), TripId)).findFirst();
    }
    // Create a trip
    public void CreateTrip(final Rider rider, final double origin, final double destination, final int seats) throws InvalidTripParamException, DriverNotFound {
        // Throw exception if origin is greater than destination
        if (origin == destination) {
            throw new InvalidTripParamException("Destination can not be same as the origin, try with valid request.");
        }

        // Assign a driver for this ride
        List<Driver> availableDrivers = dm.getallAvailableDrivers();
        Optional<Driver> assignedDriver = dsh.findDriver(rider, availableDrivers, origin, destination);

        if (assignedDriver.isEmpty()) {
            throw new DriverNotFound("Driver not found, Please try after some time");
        }

        // Calculate the fare for the trip
        double fare = dsh.calculateFare(origin, destination, seats);

        // Create trip object
        Trip trip = new Trip(assignedDriver.get(), seats, rider, origin, destination, fare);
        // Register trip to ledger
        TripLedger.put(trip.getTripId(),trip);
        assignedDriver.get().setCurrentTrip(trip);
        rider.setCurrentTrip(trip);
        assignedDriver.get().setAcceptingRider(false);
        System.out.println("Success: A trip has been generated: ");
        System.out.println("Trip ID: "+trip.getTripId());
        System.out.println("Rider : "+trip.getRider().name);
        System.out.println("Driver : "+trip.getDriver().name);
        System.out.println("Status : "+trip.getTripStatus());
    }

    // Update trip
    public void UpdateTrip(final String tripId, final double origin, final double destination, final int seats) throws InvalidTripParamException, TripNotFoundException, TripStatusException {
        // Throw exception if origin is greater than destination
        if (origin == destination) {
            throw new InvalidTripParamException("Origin should not be same as destination, try with valid request.");
        }

        // Load the current trip from ledger.
        Optional<Trip> currentTrip = getTrip(tripId);

        if (currentTrip.isEmpty()) {
            throw new TripNotFoundException(
                    "No Trip found for the given Id = " + tripId + ", please try with valid Trip Id.");
        }
        // load the current trip from ledger.
        Trip trip = currentTrip.get();
        if (trip.getTripStatus().equals(TripStatus.COMPLETED)
                || trip.getTripStatus().equals(TripStatus.WITHDRAWN)
                || trip.getTripStatus().equals(TripStatus.CANCELEDBYRIDER)
                || trip.getTripStatus().equals(TripStatus.CANCELLEDBYDRIVER)) {
            throw new TripStatusException(
                    "Trip has already been completed or cancelled try with valid Trip Id.");
        }

        // Calculate the updated fare for the trip
        double updatedFare = dsh.calculateFare(origin, destination, seats);

        // Update the trip
        trip.updateTrip(origin,destination,seats,updatedFare);

        System.out.println("The trip having Trip ID "+trip.getTripId()+" has been updated.");
        System.out.println("The updated fare is: "+updatedFare);
    }
    // Get trip history of rider
    public List<Trip> tripHistory(final Rider rider) {
        return this.TripLedger.values().stream().filter(trip -> Objects.equals(trip.getRider().user_id, rider.user_id)).toList();
    }
    // Get trip history of driver
    public List<Trip> tripHistory(final Driver driver) {
        return this.TripLedger.values().stream().filter(trip -> Objects.equals(trip.getDriver().user_id, driver.user_id)).toList();
    }

    // Complete the trip.
    public void StartTrip(Driver driver){
        if(driver.getCurrentTrip() == null){
            System.out.println("No ongoing trips.");
        }else{
            if(InputValidation.ValidDecisionInput("Are you sure you want to start the trip "+driver.getCurrentTrip().getTripId())){
                driver.getCurrentTrip().setTripStatus(TripStatus.INPROGRESS);
                System.out.println("Trip "+driver.getCurrentTrip().getTripId() +" has started.");
            }
        }
    }

    // Complete the trip.
    public void CompleteTrip(Driver driver){
        if(driver.getCurrentTrip() == null){
            System.out.println("No ongoing trips.");
        } else if (driver.getCurrentTrip().getTripStatus()!=TripStatus.INPROGRESS) {
            System.out.println("The trip has not started yet. Please start the trip.");
        } else{
            if(InputValidation.ValidDecisionInput("Are you sure you want to end trip "+driver.getCurrentTrip().getTripId())){
                driver.getCurrentTrip().setTripStatus(TripStatus.COMPLETED);
                driver.IncrementTotalRides();
                driver.setCurrentTrip(null);
                driver.setAcceptingRider(true);
                System.out.println("Trip completed.");
            }
        }
    }

    // Cancel the trip from Driver
    public void CancelTrip(Driver driver){
        if(driver.getCurrentTrip() == null){
            System.out.println("No ongoing trips.");
        }else{
            if(InputValidation.ValidDecisionInput("Are you sure you want to cancel trip "+driver.getCurrentTrip().getTripId())){
                driver.getCurrentTrip().setTripStatus(TripStatus.CANCELLEDBYDRIVER);
                driver.getCurrentTrip().getRider().setCurrentTrip(null);
                driver.setCurrentTrip(null);
                driver.setAcceptingRider(true);
                System.out.println("Trip cancelled by driver.");
            }
        }
    }

    // Cancel the trip from rider
    public void CancelTrip(Rider rider){
        if(rider.getCurrentTrip() == null){
            System.out.println("No ongoing trips.");
        }else{
            if(InputValidation.ValidDecisionInput("Are you sure you want to cancel the trip "+rider.getCurrentTrip().getTripId())){
                rider.getCurrentTrip().setTripStatus(TripStatus.CANCELEDBYRIDER);
                rider.getCurrentTrip().getDriver().setCurrentTrip(null);
                rider.getCurrentTrip().getDriver().setAcceptingRider(true);
                rider.setCurrentTrip(null);
                System.out.println("Trip cancelled by rider.");
            }
        }
    }
}
