package domain;

import java.util.List;

public class PricingEngine {
    public Assessment assess(AssessedVehicle assessedVehicle, List<Listing> listings) {
        Assessment result = new Assessment();
        result.setAssessedVehicle(assessedVehicle);
        for (Listing listing : listings) {
            Comparison comparison = new VehicleComparator().compare(assessedVehicle, listing);
            if (comparison.getValid()) {
                result.getComparables().add(comparison);
            }
        }
        int sumOfSuggestedPrices = 0;
        for (Comparison comparison : result.getComparables()) {
            sumOfSuggestedPrices += comparison.getComparable().getPrice() - comparison.getPriceDifferenceFromAssessed();
        }
        result.setSuggestedPrice(sumOfSuggestedPrices / result.getComparables().size());
        return result;
    }
}
