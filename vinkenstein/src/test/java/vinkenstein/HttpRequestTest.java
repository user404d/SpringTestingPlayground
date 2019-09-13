package vinkenstein;

import clients.ListingsClient;
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

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=TestAppConfig.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) //, properties = "spring.main.allow-bean-definition-overriding=true")
public class HttpRequestTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ListingsClient listingsClient;

    @Test
    public void assessingFord() {
        Listing listing = new Listing();
        listing.setVin("1FM5K8F82EGA64580");
        listing.setPrice(24000);
        listingsClient.add(listing);
        Assessment assessment = restTemplate.getForObject("/assessment?vin=1FM5K8F82EGA64580", Assessment.class);
        System.out.println(assessment);
        assertEquals(assessment.getAssessedVehicle().getVin(), "1FM5K8F82EGA64580");
        assertEquals(24000, assessment.getSuggestedPrice());
        assertTrue(assessment.getComparables().size()> 0);
        assertTrue(assessment.getComparables().get(0).getComparable().getVin() != null);
    }


    @Test
    public void dynamicYearMakeLookupOnAssessedVehicle() {
        Assessment assessment = restTemplate.getForObject("/assessment?vin=JHM5K8F82FFA64580", Assessment.class);
        System.out.println(assessment);
        assertEquals(assessment.getAssessedVehicle().getVin(), "JHM5K8F82FFA64580");
        assertEquals(assessment.getAssessedVehicle().getYear(), 2015);
        assertEquals(assessment.getAssessedVehicle().getMake(), "Honda");
    }

}
