public class Driver extends User {

    // Properties
    private double driverRating;
    private boolean isAcceptingRider;
    private Trip CurrentTrip;
    private int TotalRides;

    //Methods
    public Driver(String name, String email, String contact_no) {
        super(name, email, contact_no,"D");
        this.driverRating = 0.0;
        this.isAcceptingRider = true;
        this.CurrentTrip = null;
        this.TotalRides = 0;
    }


    // Getters & Setters
    public double getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(double driverRating) {
        System.out.println("Total driver rides = 1");
        this.driverRating = ((this.driverRating+driverRating)/this.TotalRides);
    }

    public boolean isAcceptingRider() {
        return isAcceptingRider && CurrentTrip == null;
    }

    public void setAcceptingRider(boolean acceptingRider) {
        isAcceptingRider = acceptingRider;
    }

    public Trip getCurrentTrip() {
        return CurrentTrip;
    }

    public void setCurrentTrip(Trip currentTrip) {
        CurrentTrip = currentTrip;
    }

    public int getTotalRides() {
        return TotalRides;
    }

    public void IncrementTotalRides() {
        TotalRides++;
    }
}

