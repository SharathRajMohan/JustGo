import java.util.List;
import java.util.Optional;

public interface DriverMatchingStrategy {
    Optional<Driver> findDriver(Rider rider, List<Driver> Drivers, double origin, double destination);
}
