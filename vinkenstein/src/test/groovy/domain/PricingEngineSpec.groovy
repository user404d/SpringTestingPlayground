package domain

import spock.lang.Specification
import spock.lang.Unroll

class PricingEngineSpec extends Specification {
    PricingEngine pricingEngine

    def setup() {
        pricingEngine = new PricingEngine()
    }

    @Unroll
    def "identical single comparable priced #listPrice"() {
        given:
        AssessedVehicle assessedVehicle = new AssessedVehicle(vin: "1FM5K8F82EGA64580", make: "FORD", model: "F150", year: 2014, numberOfAccidents: 1, numberOfOwners: 3)
        List<Listing> listings = [new Listing(price: listPrice, vin: "1FM5K8F82EGA64580", make: "FORD", model: "F150", year: 2014, numberOfAccidents: 1, numberOfOwners: 3)]
        when:
        Assessment assessment = pricingEngine.assess(assessedVehicle, listings)
        then:
        assessment.suggestedPrice == listPrice
        assessment.assessedVehicle == assessedVehicle
        assessment.comparables.size() == 1
        assessment.comparables[0].comparable == listings[0]
        assessment.comparables[0].similarityScore == 100
        where:
        listPrice | _
        0         | _
        123       | _
        23000     | _
    }
}
