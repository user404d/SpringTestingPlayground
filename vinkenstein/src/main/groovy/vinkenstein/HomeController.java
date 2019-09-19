package vinkenstein;

import clients.ListingsClient;
import domain.AssessedVehicle;
import domain.Assessment;
import domain.Listing;
import domain.PricingEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    String remoteServerHostname;

    @Autowired
    ThirdPartyClient thirdPartyClient;

    @Autowired
    ListingsClient listingsClient;

    @RequestMapping("/static")
    public @ResponseBody
    String greeting() {
        return "Hello world from " + thirdPartyClient.get();
    }

    @RequestMapping("/assessment")
    public @ResponseBody
    Assessment assessment(@RequestParam(required = true) String vin) {
        RestTemplate restTemplate = new RestTemplate();
        Listing mommy = restTemplate.getForObject("http://" + remoteServerHostname + ":8092/mommy?vin=" + vin, Listing.class);
        Listing history = restTemplate.getForObject("http://" + remoteServerHostname + ":8093/history?vin=" + vin, Listing.class);
        AssessedVehicle assessedVehicle = new AssessedVehicle(vin, mommy.getMake(), mommy.getModel(), mommy.getYear(), history.getNumberOfAccidents(), history.getNumberOfOwners());

        List<Listing> rawListings = listingsClient.getListings();
        for (Listing rawListing : rawListings) {
            Listing rawListingMommy = restTemplate.getForObject("http://" + remoteServerHostname + ":8092/mommy?vin=" + rawListing.getVin(), Listing.class);
            rawListing.setMake(rawListingMommy.getMake());
            rawListing.setModel(rawListingMommy.getModel());
            rawListing.setYear(rawListingMommy.getYear());

            Listing rawListingHistory = restTemplate.getForObject("http://" + remoteServerHostname + ":8093/history?vin=" + rawListing.getVin(), Listing.class);
            rawListing.setNumberOfOwners(rawListingHistory.getNumberOfOwners());
            rawListing.setNumberOfAccidents(rawListingHistory.getNumberOfAccidents());
        }
        PricingEngine pricingEngine = new PricingEngine();

        return pricingEngine.assess(assessedVehicle, rawListings);
    }

}
