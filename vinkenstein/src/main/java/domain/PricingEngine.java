package domain;

import java.util.List;

public class PricingEngine {
    public Assessment assess(AssessedVehicle assessedVehicle, List<Listing> listings) {
        Assessment result = new Assessment();
        result.setAssessedVehicle(assessedVehicle);
        result.setSuggestedPrice(listings.get(0).getPrice());
        result.getComparables().add(new ComparableListing(listings.get(0), 100));
        return result;
    }
}
