import java.time.LocalDateTime;

public class Driver extends User {

    // Properties
    private double driverRating;
    private boolean isAcceptingRider;

    //Methods
    public Driver(String name, String email, String contact_no, String driverId, double driverRating) {
        super(name, email, contact_no,"D");
        this.driverRating = driverRating;
        this.isAcceptingRider = true;
    }
    // Getters & Setters
    public double getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(double driverRating) {
        this.driverRating = driverRating;
    }

    public boolean isAcceptingRider() {
        return isAcceptingRider;
    }

    public void setAcceptingRider(boolean acceptingRider) {
        isAcceptingRider = acceptingRider;
    }
}
