import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Variables
        int menuChoice;
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------------------------------");
        System.out.println("JustGo");
        System.out.println("Ride share application");
        System.out.println("----------------------------------------------------------");
        while(!exit) {
            menuChoice = MenuController.MainMenu(scanner);
            switch (menuChoice) {
                case 1:
                    System.out.println("Rider Login");
                    break;
                case 2:
                    System.out.println("Driver Login");
                    break;
                case 3:
                    System.out.println("\nDriver Registration");
                    MenuController.Register(true, scanner);
                    break;
                case 4:
                    System.out.println("\nRider Registration");
                    MenuController.Register(false, scanner);
                    break;
                case 5:
                    System.out.println("\nThank you for using the application.");
                    exit = true;
                    break;
            }
        }
    }
}