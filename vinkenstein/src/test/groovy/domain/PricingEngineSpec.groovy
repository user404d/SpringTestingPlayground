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
        assessment.comparables[0].priceDifferenceFromAssessed == 0
        where:
        listPrice | _
        0         | _
        123       | _
        23000     | _
    }

    def "ignore non-matching make and model"() {
        given:
            AssessedVehicle assessedVehicle = new AssessedVehicle(vin: "XFM5K8F82EGA64580", make: "FORD", model: "F150", year: 2014, numberOfAccidents: 1, numberOfOwners: 3)
            List<Listing> listings = [
                    new Listing(price: 99, vin: "BFM5K8F82EGA64580", make: "BMW", model: "M3", year: 2014, numberOfAccidents: 1, numberOfOwners: 3),
                    new Listing(price: 4000, vin: "FFM5K8F82EGA64580", make: "FORD", model: "F150", year: 2014, numberOfAccidents: 1, numberOfOwners: 3),
                    new Listing(price: 77, vin: "MFM5K8F82EGA64580", make: "MINI", model: "COOPER", year: 2014, numberOfAccidents: 1, numberOfOwners: 3),
                    new Listing(price: 6000, vin: "1FM5K8F82EGA64580", make: "FORD", model: "F150", year: 2014, numberOfAccidents: 1, numberOfOwners: 3)
            ]
        when:
            Assessment assessment = pricingEngine.assess(assessedVehicle, listings)
        then:
            assessment.suggestedPrice == 5000
            assessment.assessedVehicle == assessedVehicle
            assessment.comparables.size() == 2
            assessment.comparables[0].comparable == listings[1]
            assessment.comparables[0].priceDifferenceFromAssessed == 0
            assessment.comparables[1].comparable == listings[3]
            assessment.comparables[1].priceDifferenceFromAssessed == 0
    }


}
