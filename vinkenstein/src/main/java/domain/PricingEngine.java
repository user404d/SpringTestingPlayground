package domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class PricingEngine {

    public PricingEngine() {

    }

    public PricingEngine(RestTemplate restTemplate, String remoteServerHostname) {
        this.restTemplate = restTemplate;
        this.remoteServerHostname = remoteServerHostname;

    }

    String remoteServerHostname;

    RestTemplate restTemplate;

    private void populateHistoryData(List<AssessedVehicle> assessedVehicles) {
        for (AssessedVehicle assessedVehicle : assessedVehicles) {
            Listing rawListingHistory = restTemplate.getForObject("http://"+remoteServerHostname+":8093/history?vin=" + assessedVehicle.getVin(), Listing.class);
            assessedVehicle.setNumberOfOwners(rawListingHistory.getNumberOfOwners());
            assessedVehicle.setNumberOfAccidents(rawListingHistory.getNumberOfAccidents());
        }
    }

    public Assessment assess(AssessedVehicle assessedVehicle, List<Listing> listings) {
        List<AssessedVehicle> assessedVehicles = new ArrayList<>();
        assessedVehicles.addAll(listings);
        assessedVehicles.add(assessedVehicle);
        populateHistoryData(assessedVehicles);

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
        if (result.getComparables().size() > 0) {
            result.setSuggestedPrice(sumOfSuggestedPrices / result.getComparables().size());
        }
        return result;
    }
}
