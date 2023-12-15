public class Driver extends User {

    // Properties
    private double driverRating;
    private boolean isAcceptingRider;
    private Trip CurrentTrip;

    //Methods
    public Driver(String name, String email, String contact_no) {
        super(name, email, contact_no,"D");
        this.driverRating = 0.0;
        this.isAcceptingRider = true;
        this.CurrentTrip = null;
    }


    // Getters & Setters
    public double getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(double driverRating) {
        this.driverRating = driverRating;
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
}

