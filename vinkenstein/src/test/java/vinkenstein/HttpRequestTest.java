package vinkenstein;

import clients.ListingsClient;
import data.TestListings;
import domain.Assessment;
import domain.Listing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//, properties = "spring.main.allow-bean-definition-overriding=true")
public class HttpRequestTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ListingsClient listingsClient;

    @Test
    public void asessedVinSameAsListed() {
        Listing listing = TestListings.getDataset().get(0);
        listingsClient.removeAllListings();
        listingsClient.add(listing);
        Assessment assessment = restTemplate.getForObject("/assessment?vin=" + listing.getVin(), Assessment.class);
        System.out.println(assessment);
        assertEquals(assessment.getAssessedVehicle().getVin(), listing.getVin());
        assertEquals(listing.getPrice(), assessment.getSuggestedPrice());
        assertEquals(1, assessment.getComparables().size());
        assertEquals(listing.getVin(), assessment.getComparables().get(0).getComparable().getVin());
    }

    @Test
    public void severalAcuraMDX() {
        List<Listing> listings = TestListings.getByMake("ACURA");
        Listing assessed = listings.get(0);
        listings.remove(assessed);
        listingsClient.removeAllListings();
        for (Listing listing : listings) {
            listingsClient.add(listing);
        }
        Assessment assessment = restTemplate.getForObject("/assessment?vin=" + assessed.getVin(), Assessment.class);
        assertEquals(assessment.getAssessedVehicle().getVin(), assessed.getVin());
        assertEquals(23500, assessment.getSuggestedPrice());
        assertTrue(assessment.getComparables().size() > 0);

    }


    @Test
    public void dynamicYearMakeLookupOnAssessedVehicle() {
        Assessment assessment = restTemplate.getForObject("/assessment?vin=5FNRL5H40GB184780", Assessment.class);
        System.out.println(assessment);
        assertEquals(assessment.getAssessedVehicle().getVin(), "5FNRL5H40GB184780");
        assertEquals(assessment.getAssessedVehicle().getYear(), 2016);
        assertEquals(assessment.getAssessedVehicle().getMake(), "HONDA");
        assertEquals(assessment.getAssessedVehicle().getModel(), "ODYSSEY");
    }


    @Test
    public void ignoreOtherModelsForPricing() {
        List<Listing> mercuryListingsForAllModels = TestListings.getByMake("MERCURY");
        List<Listing> mercuryListingsForMountaineer = TestListings.getByMakeModel("MERCURY", "MOUNTAINEER");
        assertTrue(mercuryListingsForAllModels.size() > mercuryListingsForMountaineer.size());

        Listing assessedMountaineer = mercuryListingsForMountaineer.remove(0);
        assertTrue(mercuryListingsForAllModels.remove(assessedMountaineer));

        listingsClient.removeAllListings();
        for (Listing listing : mercuryListingsForAllModels) {
            listingsClient.add(listing);
        }

        int priceAssessedForAll = restTemplate.getForObject("/assessment?vin=" + assessedMountaineer.getVin(), Assessment.class).getSuggestedPrice();

        listingsClient.removeAllListings();
        for (Listing listing : mercuryListingsForMountaineer) {
            listingsClient.add(listing);
        }

        int priceAssessedForMountaineers = restTemplate.getForObject("/assessment?vin=" + assessedMountaineer.getVin(), Assessment.class).getSuggestedPrice();

        assertEquals(priceAssessedForAll, priceAssessedForMountaineers);

    }

    @Test
    public void olderIsCheaper() {
        listingsClient.removeAllListings();
        //5J8TB4H59HL026972|ACURA|RDX|2017|0|1|31728
        //5J8TB4H56GL047140|ACURA|RDX|2016|0|1|27067
        Listing assessedOlder = TestListings.getByVin("5J8TB4H56GL047140");
        Listing comparableYounger = TestListings.getByVin("5J8TB4H59HL026972");
        assertTrue(assessedOlder.getYear() < comparableYounger.getYear());
        listingsClient.add(comparableYounger);
        int priceAssessed = restTemplate.getForObject("/assessment?vin=" + assessedOlder.getVin(), Assessment.class).getSuggestedPrice();
        assertTrue(priceAssessed < comparableYounger.getPrice());
    }

}
