import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DriverManager{

    // Properties
    private HashMap<String,Driver> DriverLedger = new HashMap<String, Driver>(); // this is the ledger that holds information of all drivers. Note String is used to represent Driver Id.
    private static DriverManager driverManagerInstance = null;
    // Methods

    // Private constructor
    private DriverManager(){
        System.out.println("Driver Manager instantiated.");
    }
    // Adding driver object to hashmap
    public void registerDriver(Driver driver) {
        DriverLedger.put(driver.user_id,driver);
    }

    // Updating status of a driver in the hashmap.
    public void updateDriverAvailability(String driverId, boolean Status) {
         Driver tempDriver = this.DriverLedger.get(driverId);
         tempDriver.setAcceptingRider(Status);
    }

    // From the ledger returns the list of available drivers.
    public List<Driver> getallAvailableDrivers(){
        return this.DriverLedger.values().stream().filter(Driver::isAcceptingRider).collect(Collectors.toList());
    }

    // Make the class singleton
    public static DriverManager getInstance(){
        if(driverManagerInstance == null){
            driverManagerInstance = new DriverManager();
        }
        return driverManagerInstance;
    }
}
