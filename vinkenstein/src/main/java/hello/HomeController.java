package hello;

import domain.Listing;
import clients.UserClient;
import domain.User;
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
    List<Listing> listings(@RequestParam(required = false) String vin) {
        RestTemplate restTemplate = new RestTemplate();
        List<Listing> result = new ArrayList<>();
        if ((vin == null) || (vin == ""))
        {
            ResponseEntity<List<Listing>> response = restTemplate.exchange(
                    "http://localhost:8091/listings/",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Listing>>() {
                    });
            List<Listing> rawListings = response.getBody();


            for (Listing rawListing : rawListings) {
                Listing mommy = restTemplate.getForObject("http://localhost:8092/mommy?vin="+rawListing.getVin(), Listing.class);
                Listing listing = new Listing(rawListing.getVin(), mommy.getMake(), mommy.getModel(), mommy.getYear(), 1, rawListing.getPrice(), 77);
                result.add(listing);
            }
        } else {
            Listing mommy = restTemplate.getForObject("http://localhost:8092/mommy?vin="+vin, Listing.class);
            Listing listing = new Listing(vin, mommy.getMake(), mommy.getModel(), mommy.getYear(), 2, 0, 77);
            result.add(listing);
        }
        return result;
    }


    @RequestMapping("/user")

    public @ResponseBody
    String user(@RequestParam(required = false) String user) {
        User fromDatabase = new UserClient().get(user);
        return "Hello world from " + fromDatabase.getName();
    }

}
