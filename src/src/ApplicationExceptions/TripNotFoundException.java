package ApplicationExceptions;

public class TripNotFoundException extends Exception{
    public TripNotFoundException(String message) {
        super(message);
    }

    public TripNotFoundException(){
        super("404: Trip Not found.");
    }
}
