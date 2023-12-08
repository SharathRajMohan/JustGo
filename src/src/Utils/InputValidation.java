package Utils;
import java.util.Scanner;

public class InputValidation {
    // For Numerical input
    public static int ValidNumericalInput(Scanner myScan, String message) {
        while (true) {
            myScan.nextLine();
            System.out.println(message);
            try {
                return myScan.nextInt();
            } catch (Exception ex) {
                System.out.println("You seem to have entered an invalid input! Let's try that again.");
                myScan.nextLine();
            }
        }
    }
    // For string based input
    public static String ValidStringInput(Scanner myScan, String message) {
        while (true) {
            myScan.nextLine();
            System.out.println(message);
            try {
                return myScan.nextLine();
            } catch (Exception ex) {
                System.out.println("You seem to have entered an invalid input! Let's try that again.");
                myScan.nextLine();
            }
        }
    }


}
