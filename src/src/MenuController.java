import Utils.InputValidation;

// The static class that has methods for displaying menu and validating input.
public class MenuController {
    static int MainMenu(){
        System.out.println("------------------------------------------------------------------------");
        System.out.println("MAIN MENU\nAre you a rider or a driver?");
        System.out.println("1: Rider");
        System.out.println("2: Driver");
        System.out.println("\nNew to the platform? Let's get you registered..");
        System.out.println("3: Register as a Driver");
        System.out.println("4: Register as a Rider");
        System.out.println("5: Exit");
        return InputValidation.ValidNumericalInput("Enter your choice: ");
    }

    // Register a user. Parameter indicates if it is for registering a Rider or a driver.
    static void Register(boolean isDriver) {
        String username = InputValidation.ValidStringInput( "Enter the name of the user: ");
        String useremail = InputValidation.ValidStringInput( "Enter the email of the user: ");
        String usernumber = InputValidation.ValidStringInput( "Enter the contact number of the user: ");
        if (isDriver) {
            System.out.println("\nRegistering you as a driver...");
            Driver new_driver = new Driver(username, useremail, usernumber);
            DriverManager dminstance = DriverManager.getInstance();
            dminstance.registerDriver(new_driver);
            System.out.println("Welcome to JustGo, " + new_driver.name);
            System.out.println("UserID : " + new_driver.user_id);
            System.out.println("Joined on :" + new_driver.joinedOn);

        } else {
            System.out.println("\nRegistering you as a rider...");
            Rider new_rider = new Rider(username, useremail, usernumber);
            RiderManager rdinstance = RiderManager.getInstance();
            rdinstance.registerRider(new_rider);
            System.out.println("Welcome to JustGo, " + new_rider.name);
            System.out.println("UserID : " + new_rider.user_id);
            System.out.println("Joined on :" + new_rider.joinedOn);
        }
    }
    // Rider Userflow
    public static void RiderMenu(Rider rider, TripManager tm){
            int menuChoice;
            boolean exit = false;
            while (!exit) {
                System.out.println("------------------------------------------------------------------------");
                System.out.println("RIDER MENU\n");
                System.out.println("1: Request a trip.");
                System.out.println("2: Update your trip.");
                System.out.println("3: Cancel trip.");
                System.out.println("4: Get your current trip details.");
                System.out.println("5: Rate driver and generate fare receipt.");
                System.out.println("6: Logout");
                menuChoice = InputValidation.ValidNumericalInput("Enter a valid choice: ");
                switch (menuChoice) {
                    case 1:
                        System.out.println("Create Trip.");
                        if(rider.getCurrentTrip()==null) {
                            RiderController.CreateTrip(rider);
                        } else {
                            System.out.println("You already have an ongoing trip: "+rider.getCurrentTrip().getTripId());
                        }
                        break;
                    case 2:
                        System.out.println("Update current trip.");
                        RiderController.UpdateTrip(rider);
                        break;
                    case 3:
                        System.out.println("Cancel Trip");
                        tm.CancelTrip(rider);
                        break;
                    case 4:
                        System.out.println("Current trip details.");
                        RiderController.DisplayTrip(rider);
                        break;
                    case 5:
                        System.out.println("Get current trip fare.");
                        if(rider.getCurrentTrip()==null){
                            System.out.println("No ongoing trips.");
                        } else if (rider.getCurrentTrip().getTripStatus() == TripStatus.INPROGRESS){
                            System.out.println("The trip is currently in progress.");
                        } else {
                            System.out.println("Generating receipt for trip: "+rider.getCurrentTrip().getTripId());
                            RiderController.RateandFare(rider, tm);
                        }
                        break;
                    case 6:
                        System.out.println("Logging you out..");
                        exit = true;
                        break;
                    default:
                        System.out.println("You seem to have entered an invalid choice. Let's try that again.");
                        break;
                }
            }
    }

    // Driver Userflow
    public static void DriverMenu(Driver loggedDriver, TripManager tm){
        int menuChoice;
        boolean exit = false;
        while (!exit){
            System.out.println("------------------------------------------------------------------------");
            System.out.println("DRIVER MENU\n");
            System.out.println("1: Get your current trip details.");
            System.out.println("2: Start trip.");
            System.out.println("3: Complete trip.");
            System.out.println("4: Cancel trip.");
            System.out.println("5: Switch Availability.");
            System.out.println("6: Logout");
            menuChoice = InputValidation.ValidNumericalInput("Enter a valid choice: ");
            switch (menuChoice) {
                case 1:
                    System.out.println("Current Trip");
                    DriverController.DisplayTrip(loggedDriver);
                    break;
                case 2:
                    System.out.println("Start Trip");
                    tm.StartTrip(loggedDriver);
                    break;
                case 3:
                    System.out.println("Are you sure you want to complete the trip?");
                    tm.CompleteTrip(loggedDriver);
                    break;
                case 4:
                    System.out.println("Are you sure you want to cancel the trip?");
                    tm.CancelTrip(loggedDriver);
                    break;
                case 5:
                    System.out.println("Your current availability status is: "+loggedDriver.isAcceptingRider());
                    if(loggedDriver.getCurrentTrip() == null) {
                        if (InputValidation.ValidDecisionInput("Do you want to change your current availability status?")) {
                            loggedDriver.setAcceptingRider(!loggedDriver.isAcceptingRider());
                            System.out.println("Your current availability status is now : " + loggedDriver.isAcceptingRider());
                        }
                    } else {
                        System.out.println("Can not change current status, since you have an ongoing trip.");
                    }
                    break;
                case 6:
                    System.out.println("Logging you out..");
                    exit = true;
                    break;
                default:
                    System.out.println("You seem to have entered an invalid choice. Let's try that again.");
                    break;
            }
        }
    }


}

