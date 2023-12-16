import ApplicationExceptions.DriverNotFound;
import ApplicationExceptions.RiderNotFound;
import Utils.InputValidation;

public class Main {
    public static void main(String[] args) throws RiderNotFound {
        // Application Initialization
        RiderManager rm = RiderManager.getInstance();
        DriverManager dm = DriverManager.getInstance();
        TripManager tm = TripManager.getInstance();

        // Make some dummy riders and drivers for testing.
        Driver new_driver1 = new Driver("Josh", "josh@test.com", "98673521");
        dm.registerDriver(new_driver1);
        Driver new_driver2 = new Driver("Sam", "sam@test.com", "986723951");
        dm.registerDriver(new_driver2);
        Rider new_rider1 = new Rider("Nick", "nick@test.com", "9867229411");
        rm.registerRider(new_rider1);

        // Variables
        int menuChoice;
        String user_id;
        boolean exit = false;
        System.out.println("------------------------------------------------------------------------");
        System.out.println("JustGo");
        System.out.println("Ride share application");
        System.out.println("------------------------------------------------------------------------");
        while(!exit) {
            menuChoice = MenuController.MainMenu();
            switch (menuChoice) {
                case 1:
                    System.out.println("Rider Login");
                    user_id = InputValidation.ValidStringInput("Enter Rider UserID.");
                    if(Auth.isAuth(true,user_id)){
                        Rider rider = rm.getRiderByUsername(user_id);
                        System.out.println("Hi, "+ rider.name+"!");
                        MenuController.RiderMenu(rider,tm);
                    } else {
                        try {
                            throw new RiderNotFound();
                        } catch (RiderNotFound e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Driver Login");
                    user_id = InputValidation.ValidStringInput("Enter Driver UserID.");
                    if(Auth.isAuth(false,user_id)){
                        Driver driver = dm.getDriverByUsername(user_id);
                        System.out.println("Hi, "+ driver.name+"!\n");
                        MenuController.DriverMenu(driver,tm);
                    } else {
                        try {
                            throw new DriverNotFound();
                        } catch (DriverNotFound e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 3:
                    System.out.println("\nDriver Registration");
                    MenuController.Register(true);
                    break;
                case 4:
                    System.out.println("\nRider Registration");
                    MenuController.Register(false);
                    break;
                case 5:
                    System.out.println("\nThank you for using the application.");
                    exit = true;
                    break;
                default:
                    System.out.println("\n You seem to have entered an invalid choice. Let's try that again.");
                    break;
            }
        }
    }
}