public class DefaultPricingHandler implements PricingStrategy{
    // Method to calculate fare for the ride.
    @Override
    public double calculateFare(int origin, int destination, int seats) {
        double distance = destination - origin;
        double basePrice = PricingStrategy.AMT_PER_KM * distance;
        double seatRate = PricingStrategy.SEAT_PRICE * seats;
        return basePrice + seatRate;
    }
}
