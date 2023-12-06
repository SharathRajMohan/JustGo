import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DriverManager implements DriverMatchingStrategy {

    // Properties
    private HashMap<String,Driver> DriverLedger = new HashMap<String, Driver>(); // this is the ledger that holds information of all drivers. Note String is used to represent Driver Id.

    // Methods
    @Override
    public Driver findDriver() {
        return null;
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
}
