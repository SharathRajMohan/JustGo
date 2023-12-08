import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RiderManager {
    // Properties
    private HashMap<String,Rider> RiderLedger = new HashMap<String, Rider>(); // this is the ledger that holds information of all riders. Note String is used to represent Rider Id.
    private static RiderManager riderManagerInstance = null;

    // Methods
    private RiderManager(){
        System.out.println("Rider Manager instantiated.");
    }

    // Adding rider object to hashmap
    public void registerRider(Rider rider) {
        RiderLedger.put(rider.user_id,rider);
    }

    // Get all riders requesting for a ride.
    // From the ledger returns the list of available riders.
    public List<Rider> getallRequestingRiders(){
        return this.RiderLedger.values().stream().filter(Rider::isRequesting).collect(Collectors.toList());
    }

    // Make the class singleton
    public static RiderManager getInstance(){
        if(riderManagerInstance == null){
            riderManagerInstance = new RiderManager();
        }
        return riderManagerInstance;
    }

}
