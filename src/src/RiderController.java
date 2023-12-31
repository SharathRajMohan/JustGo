import ApplicationExceptions.DriverNotFound;
import ApplicationExceptions.InvalidTripParamException;
import ApplicationExceptions.TripNotFoundException;
import ApplicationExceptions.TripStatusException;
import Utils.InputValidation;

import java.util.List;

public class RiderController {
    // Method to create trip using rider input.
    static void CreateTrip(Rider rider) {
        while (true) {
            double origin = InputValidation.ValidNumericalInput("Enter the origin of the trip: ");
            double destination = InputValidation.ValidNumericalInput("Enter the destination of the trip: ");
            int seats = InputValidation.ValidNumericalInput("Enter the seats required: ");

            System.out.println("Finding a driver.. Creating a trip.. hold on..");
            TripManager tm = TripManager.getInstance();
            try {
                tm.CreateTrip(rider, origin, destination, seats);
                break;
            } catch (InvalidTripParamException | DriverNotFound ex) {
                System.out.println(ex.getMessage());
                System.out.println("Try again.");
            }
        }
    }

    // Method to update the trip.
    static void UpdateTrip(Rider rider) {
        while (true) {
            if (rider.getCurrentTrip() != null) {
                double origin = InputValidation.ValidNumericalInput("Enter the updated origin of the trip: ");
                double destination = InputValidation.ValidNumericalInput("Enter the destination of the trip: ");
                int seats = InputValidation.ValidNumericalInput("Enter the seats required: ");

                System.out.println("Updating your trip.. hold on..");
                TripManager tm = TripManager.getInstance();
                try {
                    tm.UpdateTrip(rider.getCurrentTrip().getTripId(), origin, destination, seats);
                    break;
                } catch (InvalidTripParamException | TripNotFoundException | TripStatusException ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("Try again.");
                }
            } else {
                System.out.println("You currently do not have any ongoing trips.");
                break;
            }
        }
    }

    // Get current Trip details
    static void DisplayTrip(Rider rider) {
        if(rider.getCurrentTrip() != null){
            Trip currTrip = rider.getCurrentTrip();
            System.out.println("Current Trip Details\n");
            System.out.println("||TRIPID||TRIPCREATIONTIME||ORIGIN||DESTINATION||SEATS||DRIVER||DRIVER RATING||FARE||STATUS||");
            System.out.println("||"+currTrip.getTripId()+"||"+currTrip.getTripCreationTime()+"||"+currTrip.getOrigin()
            +"||"+ currTrip.getDestination()+"||"+currTrip.getSeats()+"||"+currTrip.getDriver().name+"||"+currTrip.getDriver().getDriverRating()
            +"||"+currTrip.getFare()+"||"+currTrip.getTripStatus()+"||");
        } else{
            System.out.println("No ongoing trips.");
        }
    }
    // Display Fare and get trip rating.
    static void RateandFare(Rider rider, TripManager tm){
        // Get last completed trip details.
        if(rider.getCurrentTrip().getTripStatus() == TripStatus.COMPLETED){
            double rating = InputValidation.ValidNumericalInput("Please rate your trip driver out of 5");
            rider.getCurrentTrip().getDriver().setDriverRating(rating);
            DisplayTrip(rider);
            System.out.println("\nThe total fare for the ride is: "+rider.getCurrentTrip().getFare());
            rider.setCurrentTrip(null);
        } else{
            System.out.println("You do not have any completed trips.");
        }
    }
}
