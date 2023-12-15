import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Trip {
    // Properties
    private static AtomicLong idCounterT = new AtomicLong(0L);
    private String tripId;
    private Driver driver;
    private int seats;
    private Rider rider;
    private double origin;
    private double destination;
    private double fare;
    private TripStatus tripStatus = TripStatus.SCHEDULED;
    private LocalDateTime TripCreationTime;

    //Methods
    public Trip(Driver driver, int seats, Rider rider, double origin, double destination, double fare) {
        this.tripId = createID();
        this.driver = driver;
        this.seats = seats;
        this.rider = rider;
        this.origin = origin;
        this.destination = destination;
        this.fare = fare;
        this.tripStatus = TripStatus.SCHEDULED;
        this.TripCreationTime = LocalDateTime.now();
    }

    // Methods for random ID generation
    public static String createID()
    {
        return "T0"+String.valueOf(idCounterT.incrementAndGet());
    }
    // Getters and Setters

    public String getTripId() {
        return tripId;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }
    // Update the trip method
    public void updateTrip(final double origin, final double destination, final int seats, final double fare) {
        this.origin = origin;
        this.destination = destination;
        this.seats = seats;
        this.fare = fare;
    }

    public LocalDateTime getTripCreationTime() {
        return TripCreationTime;
    }

    public double getOrigin() {
        return origin;
    }

    public void setOrigin(double origin) {
        this.origin = origin;
    }

    public double getDestination() {
        return destination;
    }

    public void setDestination(double destination) {
        this.destination = destination;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
}
