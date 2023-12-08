import java.util.List;

public interface DriverMatchingStrategy {
    Driver findDriver(Rider rider, List<Driver> Drivers, float origin, float destination);
}
