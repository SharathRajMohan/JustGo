package ApplicationExceptions;

public class DriverNotFound extends Exception{
    public DriverNotFound(String message) {
        super(message);
    }

    public DriverNotFound(){
        super("404: Driver Not found.");
    }
}


