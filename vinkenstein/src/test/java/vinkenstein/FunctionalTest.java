package vinkenstein;

import clients.ListingsClient;
import data.TestListings;
import domain.Assessment;
import domain.Listing;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//, properties = "spring.main.allow-bean-definition-overriding=true")
public class FunctionalTest {

    @Autowired String remoteServerHostname;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ListingsClient listingsClient;

    List<Listing> listingsToCleanup;

    @Before
    public void setup() {
        listingsToCleanup = new ArrayList<>();
    }

    @After
    public void tearDown() {
       for (Listing listing: listingsToCleanup) {
           listingsClient.delete(listing.getVin());
       }
    }

    @Test
    public void asessedVinSameAsListed() {
        Listing listing = TestListings.getDataset().get(0);
        givenVinHasMakeModelYear(listing.getVin(), listing.getMake(), listing.getModel(), listing.getYear());
        givenNoMakeModelForSale(listing.getMake(), listing.getModel());
        givenVinListedForSale(listing.getVin(), listing.getPrice());
        whenCustomerPrices(listing.getVin());
        thenAssessedPrice(listing.getPrice());
        thenSuggestedComparablePriceAdjustment(listing.getVin(), 0);
    }

    private void givenVinHasMakeModelYear(String vin, String make, String model, int year) {
        Listing accordingToMommy = restTemplate.getForObject("http://"+remoteServerHostname+":8092/mommy?vin=" + vin, Listing.class);
        assertEquals(make, accordingToMommy.getMake());
        assertEquals(model, accordingToMommy.getModel());
        assertEquals(year, accordingToMommy.getYear());
    }

    Assessment assessment;

    public void whenCustomerPrices(String vin) {
        assessment = restTemplate.getForObject("/assessment?vin=" + vin, Assessment.class);
        assertEquals(assessment.getAssessedVehicle().getVin(), vin);
    }

    public void givenNoMakeModelForSale(String make, String model) {
        //listingsClient.removeAllListings();
        List<Listing> listings = listingsClient.getListings();
        for (Listing listing : listings) {
            Listing accordingToMommy = restTemplate.getForObject("http://"+remoteServerHostname+":8092/mommy?vin=" + listing.getVin(), Listing.class);
            if (make.equals(accordingToMommy.getMake()) && model.equals(accordingToMommy.getModel())) {
                listingsClient.delete(listing.getVin());
            }
        }
    }

    public void givenVinListedForSale(String vin, int price) {
        Listing listing = new Listing();
        listing.setVin(vin);
        listing.setPrice(price);
        listingsClient.add(listing);
        listingsToCleanup.add(listing);
    }

    public void thenAssessedPrice(int expectedPrice) {
        assertEquals(expectedPrice, assessment.getSuggestedPrice());
    }

    public void thenSuggestedComparablePriceAdjustment(String comparableVin, int expectedPriceAdjustment) {
        assertEquals(1, assessment.getComparables().size());
        assertEquals(comparableVin, assessment.getComparables().get(0).getComparable().getVin());
        assertEquals(expectedPriceAdjustment, assessment.getComparables().get(0).getPriceDifferenceFromAssessed());
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
        assertTrue("Precondition: there should be at least one listing with different model", mercuryListingsForAllModels.size() > mercuryListingsForMountaineer.size());

        Listing assessedMountaineer = mercuryListingsForMountaineer.remove(0);
        assertTrue("Sanity check - all models should include listing being assessed", mercuryListingsForAllModels.remove(assessedMountaineer));

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
        Listing assessedOlder = TestListings.getByVin("5J8TB4H56GL047140");
        Listing comparableYounger = TestListings.getByVin("5J8TB4H59HL026972");
        assertTrue("Precondition: year", assessedOlder.getYear() < comparableYounger.getYear());
        listingsClient.add(comparableYounger);
        int priceAssessed = restTemplate.getForObject("/assessment?vin=" + assessedOlder.getVin(), Assessment.class).getSuggestedPrice();
        assertTrue(priceAssessed < comparableYounger.getPrice());
    }

    @Test
    public void moreOwnersIsCheaper() {
        listingsClient.removeAllListings();
        Listing assessedMoreOwners = TestListings.getByVin("KNALW4D42F6086777");
        Listing comparableFewerOwners = TestListings.getByVin("KNALW4D44F6063577");
        assertTrue("Precondition: numberOfOwners", assessedMoreOwners.getNumberOfOwners() > comparableFewerOwners.getNumberOfOwners());
        assertTrue("Precondition: numberOfAccidents", assessedMoreOwners.getNumberOfAccidents() == comparableFewerOwners.getNumberOfAccidents());

        listingsClient.add(comparableFewerOwners);
        int priceAssessed = restTemplate.getForObject("/assessment?vin=" + assessedMoreOwners.getVin(), Assessment.class).getSuggestedPrice();
        assertTrue(priceAssessed < comparableFewerOwners.getPrice());
    }

    @Test
    public void moreAccidentsIsCheaper() {
        listingsClient.removeAllListings();
        Listing assessedMoreAccidents = TestListings.getByVin("6G2EP57W89L309931");
        Listing comparableFewerAccidents = TestListings.getByVin("6G2EP57W79L353452");
        assertTrue("Precondition: numberOfAccidents", assessedMoreAccidents.getNumberOfAccidents() > comparableFewerAccidents.getNumberOfAccidents());
        assertTrue("Precondition: numberOfOwners", assessedMoreAccidents.getNumberOfOwners() == comparableFewerAccidents.getNumberOfOwners());
        listingsClient.add(comparableFewerAccidents);
        int priceAssessed = restTemplate.getForObject("/assessment?vin=" + assessedMoreAccidents.getVin(), Assessment.class).getSuggestedPrice();
        assertTrue(priceAssessed < comparableFewerAccidents.getPrice());
    }

}
