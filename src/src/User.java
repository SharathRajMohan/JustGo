import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class User {
    // Properties
    private static AtomicLong idCounterD = new AtomicLong(0L); //Total driver objects instantiated.
    private static AtomicLong idCounterR = new AtomicLong(0L); //Total user objects instantiated.
    String user_id;
    String name;
    String email;
    String contact_no;
    LocalDateTime joinedOn;

    // Methods
    public User(String name, String email, String contact_no,String type) {
        this.user_id = createID(type);
        this.name = name;
        this.email = email;
        this.contact_no = contact_no;
        this.joinedOn = LocalDateTime.now();
    }

    // Methods for random ID generation
    public static String createID(String Type)
    {
        if(Objects.equals(Type, "D")) {
            return "D0"+String.valueOf(idCounterD.getAndIncrement());
        }else{
            return "R0"+String.valueOf(idCounterR.getAndIncrement());
        }
    }
}
