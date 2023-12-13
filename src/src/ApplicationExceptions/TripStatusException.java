package ApplicationExceptions;

public class TripStatusException extends Exception{
    public TripStatusException(String message) {
        super(message);
    }

    public TripStatusException(){
        super("Trip Status Error");
    }
}
