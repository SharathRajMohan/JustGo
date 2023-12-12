package ApplicationExceptions;

public class StringEmptyException extends Exception{
    public StringEmptyException(String message) {
        super(message);
    }

    public StringEmptyException(){
        super("Input can not be empty.");
    }
}
