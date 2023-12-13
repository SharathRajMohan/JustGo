import java.util.List;
import java.util.Optional;

public class DefaultStrategyHandler implements PricingStrategy, DriverMatchingStrategy{
    // Method to calculate fare for the ride.
    @Override
    public double calculateFare(double origin, double destination, int seats) {
        double distance = destination - origin;
        double basePrice = PricingStrategy.AMT_PER_KM * distance;
        double seatRate = PricingStrategy.SEAT_PRICE * seats;
        return basePrice + seatRate;
    }

    @Override
    public Optional<Driver> findDriver(Rider rider, List<Driver> availableDrivers, double origin, double destination) {
        return availableDrivers.stream().findAny();
    }
}
