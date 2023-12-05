package ApplicationExceptions;

public class RiderNotFound extends Exception{
    public RiderNotFound(String message) {
        super(message);
    }

    public RiderNotFound(){
        super("404: Rider Not found.");
    }
}
