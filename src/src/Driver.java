public class Driver extends User {

    // Properties
    String driverId;
    double driverRating;

    //Methods
    public Driver(String name, String email, String contact_no, String driverId, double driverRating) {
        super(name, email, contact_no);
        this.driverId = driverId;
        this.driverRating = driverRating;
    }

}
