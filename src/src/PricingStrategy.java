// Any class that provides a service needs to implement 'Pricing Strategy' interface.
public interface PricingStrategy {
    double AMT_PER_KM = 20;
    double SEAT_PRICE = 5;
    double calculateFare(double origin, double destination, int seats);
}