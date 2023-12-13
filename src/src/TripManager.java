import ApplicationExceptions.DriverNotFound;
import ApplicationExceptions.InvalidTripParamException;
import ApplicationExceptions.TripNotFoundException;
import ApplicationExceptions.TripStatusException;

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
        if (origin >= destination) {
            throw new InvalidTripParamException("Origin should always be greater than exception, try with valid request.");
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
        System.out.println("Trip ID: "+trip.getTripId());
        System.out.println("Rider : "+trip.getRider().name);
        System.out.println("Driver : "+trip.getDriver().name);
    }

    // Update trip
    public void UpdateTrip(final String tripId, final int origin, final int destination, final int seats) throws InvalidTripParamException, DriverNotFound, TripNotFoundException, TripStatusException {
        // Throw exception if origin is greater than destination
        if (origin >= destination) {
            throw new InvalidTripParamException("Origin should always be greater than exception, try with valid request.");
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
                || trip.getTripStatus().equals(TripStatus.WITHDRAWN)) {
            throw new TripStatusException(
                    "Trip has already been completed or withdrawn try with valid Trip Id.");
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
}
