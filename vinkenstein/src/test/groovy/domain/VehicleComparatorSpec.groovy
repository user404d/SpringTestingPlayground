package domain

import spock.lang.Specification

class VehicleComparatorSpec extends Specification {
    def "same make and model all matching"() {
        given:
            Listing assessedVehicle = new Listing(vin: "5NPDH4AE0EH476884", make: "Buick", model: "LeSabre")
            Listing comparable = new Listing(vin: "XNPDH4AE0EH476884", make: "Buick", model: "LESABRE")
        when:
            Comparison comparison = new VehicleComparator().compare(assessedVehicle, comparable)
        then:
            comparison.similarityScore == 100
            comparison.comparable == comparable
            comparison.priceDifferenceFromAssessed == 0
    }

    def "ignore different make and model"() {
        given:
            Listing assessedVehicle = new Listing(vin: "5NPDH4AE0EH476884", make: "Buick", model: "LeSabre")
            Listing comparable = new Listing(vin: "XNPDH4AE0EH476884", make: "Buick", model: "Lacross")
        when:
            Comparison comparison = new VehicleComparator().compare(assessedVehicle, comparable)
        then:
            comparison.similarityScore == 0
            comparison.comparable == comparable
    }
}
