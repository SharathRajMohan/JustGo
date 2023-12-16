public class DriverController {
    // Get current trip details
    static void DisplayTrip(Driver driver) {
        if (driver.getCurrentTrip() != null) {
            Trip currTrip = driver.getCurrentTrip();
            System.out.println("Current Trip Details\n");
            System.out.println("||TRIPID||TRIPCREATIONTIME||ORIGIN||DESTINATION||RIDER||RIDER CONTACT||FARE||STATUS||");
            System.out.println("||" + currTrip.getTripId() + "||" + currTrip.getTripCreationTime() + "||" + currTrip.getOrigin()
                    + "||" + currTrip.getDestination() + "||" + currTrip.getRider().name + "||" + currTrip.getRider().contact_no
                    + "||" + currTrip.getFare() + "||" + currTrip.getTripStatus() + "||");
        } else {
            System.out.println("There are no ongoing trips.");
        }
    }
}
