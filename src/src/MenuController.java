import Utils.InputValidation;

import java.util.Scanner;

// The static class that has methods for displaying menu and validating input.
public class MenuController {


    static int MainMenu(Scanner input){
        System.out.println("Welcome!\nAre you a rider or a driver?");
        System.out.println("1: Rider");
        System.out.println("2: Driver");
        System.out.println("\nNew to the platform? Let's get you registered..");
        System.out.println("3: Register as a Driver");
        System.out.println("4: Register as a Rider");
        return InputValidation.ValidNumericalInput(input, "Enter a valid choice: ");
    }

    // Register a user. Parameter indicates if it is for registering a Rider or a driver.
    static void Register(boolean isDriver, Scanner input) {
        String username = InputValidation.ValidStringInput(input, "Enter the name of the user: ");
        String useremail = InputValidation.ValidStringInput(input, "Enter the email of the user: ");
        String usernumber = InputValidation.ValidStringInput(input, "Enter the contact number of the user: ");
        if(isDriver) {
            System.out.println("\n Registering you as a driver...");
            Driver new_driver = new Driver(username,useremail,usernumber);
            DriverManager dminstance = DriverManager.getInstance();
            dminstance.registerDriver(new_driver);
            System.out.println("Welcome to JustGo, "+new_driver.name);
            System.out.println("UserID :, "+new_driver.user_id);
            System.out.println("Joined on :"+new_driver.joinedOn);

        }else{
            System.out.println("\n Registering you as a rider...");
            Rider new_rider = new Rider(username,useremail,usernumber);
            RiderManager rdinstance = RiderManager.getInstance();
            rdinstance.registerRider(new_rider);
            System.out.println("Welcome to JustGo, "+new_rider.name);
            System.out.println("UserID : "+new_rider.user_id);
            System.out.println("Joined on :"+new_rider.joinedOn);
        }



    }
}

