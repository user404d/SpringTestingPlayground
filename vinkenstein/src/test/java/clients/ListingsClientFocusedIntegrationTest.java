package clients;

import domain.Listing;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vinkenstein.AppConfig;
import vinkenstein.TestAppConfig;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ListingsClientFocusedIntegrationTest {
    @Autowired
    ListingsClient listingsClient;

    @After
    public void cleanup() {
        listingsClient.delete("1N4AL11E26N343129");
    }

    @Test
    public void crudAListing() {
        Listing newListing = new Listing();
        newListing.setVin("1N4AL11E26N343129");
        newListing.setPrice(44999);
        listingsClient.delete(newListing.getVin());
        List<Listing> listings = listingsClient.getListings();
        assertFalse(listings.stream().filter(listing -> "1N4AL11E26N343129".equals(listing.getVin())).findAny().isPresent());
        listingsClient.add(newListing);
        List<Listing> listingsAfter = listingsClient.getListings();
        assertEquals(44999, listingsAfter.stream().filter(listing -> "1N4AL11E26N343129".equals(listing.getVin())).findAny().get().getPrice());
    }

    @Test
    public void removeAllListings() {
        Listing newListing = new Listing();
        newListing.setVin("XN4AL11E26N343129");
        newListing.setPrice(1);
        listingsClient.add(newListing);
        Listing newListing2 = new Listing();
        newListing2.setVin("YN4AL11E26N343129");
        newListing2.setPrice(1);
        listingsClient.add(newListing2);
        listingsClient.removeAllListings();
        assertEquals(0, listingsClient.getListings().size());

    }
}
