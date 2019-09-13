package domain;

import java.util.List;

public class PricingEngine {
    public Assessment assess(AssessedVehicle assessedVehicle, List<Listing> listings) {
        Assessment result = new Assessment();
        result.setAssessedVehicle(assessedVehicle);
        Comparison comparison = new VehicleComparator().compare(assessedVehicle, listings.get(0));
        result.setSuggestedPrice(comparison.getComparable().getPrice() - comparison.getPriceDifferenceFromAssessed());
        result.getComparables().add(comparison);
        return result;
    }
}
