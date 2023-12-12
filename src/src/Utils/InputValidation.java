package Utils;
import ApplicationExceptions.StringEmptyException;

import java.util.Objects;
import java.util.Scanner;

public class InputValidation {

    // For Numerical input
    public static int ValidNumericalInput( String message) {
        while (true) {
            Scanner myScan = new Scanner(System.in);
            System.out.println(message);
            try {
                int result;
                result = myScan.nextInt();
                return result;
            } catch (Exception ex) {
                System.out.println("You seem to have entered an invalid input! Let's try that again.");
                myScan.next();
            }
        }
    }
    // For string based input
    public static String ValidStringInput(String message) {
        while (true) {
            Scanner myScan = new Scanner(System.in);
            String result;
            System.out.println(message);
            try {
                result = myScan.nextLine();
                if(result.isEmpty()){
                    throw new StringEmptyException("Field can not be empty!");
                }
                System.out.println(result);
                return result;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("You seem to have entered an invalid input! Let's try that again.");
                myScan.nextLine();
            }

        }
    }


}
