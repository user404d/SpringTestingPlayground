package domain

import spock.lang.Specification

class VehicleComparatorSpec extends Specification {
    def "all matching except for vin"() {
        given:
            AssessedVehicle assessedVehicle = new AssessedVehicle(vin: "5NPDH4AE0EH476884", make: "Buick", model: "LeSabre")
            Listing comparable = new Listing(vin: "XNPDH4AE0EH476884", make: "Buick", model: "LESABRE")
        when:
            Comparison comparison = new VehicleComparator().compare(assessedVehicle, comparable)
        then:
            comparison.similarityScore == 100
            comparison.comparable == comparable
            comparison.priceDifferenceFromAssessed == 0
    }

    def "all matching except for vin fully populated"() {
        given:
            AssessedVehicle assessedVehicle = new AssessedVehicle(vin: "FNPDH4AE0EH476884", make: "FORD", model: "F150", year: 1999, numberOfAccidents: 1, numberOfOwners: 2)
            Listing comparable = new Listing(vin: "FNPDH4AE0EH476884", make: "Ford", model: "f150", year: 1999, numberOfAccidents: 1, numberOfOwners: 2, price: 9922)
        when:
            Comparison comparison = new VehicleComparator().compare(assessedVehicle, comparable)
        then:
            comparison.similarityScore == 100
            comparison.comparable == comparable
            comparison.priceDifferenceFromAssessed == 0
    }

    def "ignore different make and model"() {
        given:
            AssessedVehicle assessedVehicle = new AssessedVehicle(vin: "5NPDH4AE0EH476884", make: "Buick", model: "LeSabre")
            Listing comparable = new Listing(vin: "XNPDH4AE0EH476884", make: "Buick", model: "Lacross")
        when:
            Comparison comparison = new VehicleComparator().compare(assessedVehicle, comparable)
        then:
            comparison.similarityScore == 0
            comparison.comparable == comparable
    }
    AssessedVehicle assessedVehicle = new AssessedVehicle(vin: "5NPDH4AE0EH476884", make: "Buick", model: "LeSabre", numberOfAccidents: 0)
    Listing comparable = new Listing(vin: "XNPDH4AE0EH476884", make: "Buick", model: "LESABRE", numberOfAccidents: 1)

    def "differ by one accident"() {
        given:
            assessedVehicle.numberOfAccidents = 0
            comparable.numberOfAccidents = 1
            comparable.price = 10000
        when:
            Comparison comparison = new VehicleComparator().compare(assessedVehicle, comparable)
        then:
            comparison.similarityScore < 100
            comparison.similarityScore > 0
            comparison.comparable == comparable
            comparison.priceDifferenceFromAssessed == -1000

    }

    def "differ by one accident the other way"() {
        given:
            assessedVehicle.numberOfAccidents = 10
            comparable.numberOfAccidents = 9
            comparable.price = 10000
        when:
            Comparison comparison = new VehicleComparator().compare(assessedVehicle, comparable)
        then:
            comparison.similarityScore < 100
            comparison.similarityScore > 0
            comparison.comparable == comparable
            comparison.priceDifferenceFromAssessed == 1000

    }

    def "cap accident penalty at 40 percent"() {
        given:
            assessedVehicle.numberOfAccidents = 1
            comparable.numberOfAccidents = 99
            comparable.price = 100000
        when:
            Comparison comparison = new VehicleComparator().compare(assessedVehicle, comparable)
        then:
            comparison.priceDifferenceFromAssessed == -40000
    }
}
