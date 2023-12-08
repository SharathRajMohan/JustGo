public class Driver extends User {

    // Properties
    private double driverRating;
    private boolean isAcceptingRider;

    //Methods
    public Driver(String name, String email, String contact_no) {
        super(name, email, contact_no,"D");
        this.driverRating = 0.0;
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
