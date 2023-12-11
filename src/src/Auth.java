
public class Auth {
    public static boolean isAuth(Boolean isRider, String user_id){
        if(isRider){
            RiderManager rm = RiderManager.getInstance();
            return rm.checkRider(user_id);
        }else{
            DriverManager dm = DriverManager.getInstance();
            return dm.checkDriver(user_id);
        }
    }
}
