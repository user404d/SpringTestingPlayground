package data

import domain.Listing
import org.junit.Test

import static junit.framework.Assert.assertEquals
import static junit.framework.TestCase.assertFalse
import static junit.framework.TestCase.assertTrue

class TestListingsTest {
    @Test
    public void testMakes() {
        List makes = TestListings.makes();
        for (String make : makes) {
            System.out.println(make)
        }
        assertEquals(49, makes.size());
        assertTrue(makes.contains("FORD"));
        assertTrue(makes.contains("ASTON MARTIN"));

    }

    @Test
    public void testMakeModels() {
        List makes = TestListings.makes();
        for (String make : makes) {
            List models = TestListings.modelsfForMake(make)
            System.out.println(make)
            for (String model : models) {
                System.out.println("   " + model)

            }
        }

        assertTrue(TestListings.modelsfForMake("FORD").contains("F150"))
        assertFalse(TestListings.modelsfForMake("FREIGHTLINER").contains("F150"))
        assertEquals(5, TestListings.modelsfForMake("MITSUBISHI").size())
        assertTrue(TestListings.modelsfForMake("MITSUBISHI").contains("LANCER"));
        assertTrue(TestListings.modelsfForMake("MITSUBISHI").contains("ECLIPSE"));
        assertTrue(TestListings.modelsfForMake("MITSUBISHI").contains("OUTLANDER"));
        assertTrue(TestListings.modelsfForMake("MITSUBISHI").contains("MIRAGE"));
        assertTrue(TestListings.modelsfForMake("MITSUBISHI").contains("GALANT"));

    }

    @Test
    public void getByMake() {
        List<Listing> listings = TestListings.getByMake("PONTIAC");
        List<String> models = TestListings.modelsfForMake("PONTIAC");
        assertEquals(600, listings.size());
        for (Listing listing: listings) {
            assertTrue(models.contains(listing.getModel()));
            assertEquals("PONTIAC", listing.getMake())
        }
    }

    @Test
    public void testGetByMakeModel() {
        List<Listing> listingsByMake = TestListings.getByMake("FORD");
        List<Listing> f150Listings = TestListings.getByMakeModel("FORD", "F150");
        assertTrue(listingsByMake.size() > f150Listings.size())
        assertTrue(f150Listings.size() > 0);
        for (Listing listing : f150Listings) {
            assertEquals("F150", listing.getModel())
            assertEquals("FORD", listing.getMake())
        }
    }
}
