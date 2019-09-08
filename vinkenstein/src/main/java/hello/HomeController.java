package hello;

import domain.*;
import clients.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    ThirdPartyClient thirdPartyClient;

    @RequestMapping("/static")
    public @ResponseBody
    String greeting() {
        return "Hello world from " + thirdPartyClient.get();
    }

    @RequestMapping("/listings")
    public @ResponseBody
    Assessment listings(@RequestParam(required = true) String vin) {
        RestTemplate restTemplate = new RestTemplate();
        Listing mommy = restTemplate.getForObject("http://localhost:8092/mommy?vin=" + vin, Listing.class);
        AssessedVehicle assessedVehicle = new AssessedVehicle(vin, mommy.getMake(), mommy.getModel(), mommy.getYear(), 0, 0);
        ResponseEntity<List<Listing>> response = restTemplate.exchange(
                "http://localhost:8091/listings/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Listing>>() {
                });
        List<Listing> rawListings = response.getBody();
        for (Listing rawListing : rawListings) {
            Listing rawListingMommy = restTemplate.getForObject("http://localhost:8092/mommy?vin=" + rawListing.getVin(), Listing.class);
            rawListing.setMake(rawListingMommy.getMake());
            rawListing.setModel(rawListingMommy.getModel());
            rawListing.setYear(rawListingMommy.getYear());
        }
        PricingEngine pricingEngine = new PricingEngine();

        return pricingEngine.assess(assessedVehicle, rawListings);
    }


    @RequestMapping("/user")

    public @ResponseBody
    String user(@RequestParam(required = false) String user) {
        User fromDatabase = new UserClient().get(user);
        return "Hello world from " + fromDatabase.getName();
    }

}
