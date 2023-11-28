import java.util.ArrayList;

public class Trip {
    // Properties
    private String tripId;
    private String driver;
    private int seats;
    private ArrayList<Rider> riderList = new ArrayList<Rider>();
    private double origin;
    private double destination;

    private TripStatus tripStatus = TripStatus.SCHEDULED;

    //Methods
    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }
}
