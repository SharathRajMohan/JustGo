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
    public static void RiderMenu(){
            int menuChoice;
            boolean exit = false;
            while (!exit) {
                System.out.println("------------------------------------------------------------------------");
                System.out.println("RIDER MENU\n");
                System.out.println("1: Request a trip.");
                System.out.println("2: Update your trip.");
                System.out.println("3: Get your current trip details.");
                System.out.println("4: Get ride fare receipt.");
                System.out.println("5: Logout");
                menuChoice = InputValidation.ValidNumericalInput("Enter a valid choice: ");
                switch (menuChoice) {
                    case 1:
                        System.out.println("Current Trip");
                        break;
                    case 2:
                        System.out.println("Are you sure you want to complete the trip?");
                        break;
                    case 3:
                        System.out.println("Are you sure you want to withdraw the trip?");
                        break;
                    case 4:
                        System.out.println("Do you want to change your current availability status?");
                        break;
                    case 5:
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
    public static void DriverMenu(Driver loggedDriver){
        int menuChoice;
        boolean exit = false;
        while (!exit){
            System.out.println("------------------------------------------------------------------------");
            System.out.println("DRIVER MENU\n");
            System.out.println("1: Get your current trip details.");
            System.out.println("2: Complete trip.");
            System.out.println("3: Withdraw trip.");
            System.out.println("4: Switch Availability.");
            System.out.println("5: Logout");
            menuChoice = InputValidation.ValidNumericalInput("Enter a valid choice: ");
            switch (menuChoice) {
                case 1:
                    System.out.println("Current Trip");
                    break;
                case 2:
                    System.out.println("Are you sure you want to complete the trip?");
                    break;
                case 3:
                    System.out.println("Are you sure you want to withdraw the trip?");
                    break;
                case 4:
                    System.out.println("Do you want to change your current availability status?");
                    break;
                case 5:
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

