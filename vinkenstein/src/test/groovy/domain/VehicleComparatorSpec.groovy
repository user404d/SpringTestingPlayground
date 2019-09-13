package domain

import spock.lang.Specification

class VehicleComparatorSpec extends Specification {
    AssessedVehicle assessedVehicle ;
    Listing comparable ;

    def "setup"() {
        assessedVehicle = new AssessedVehicle(vin: "5NPDH4AE0EH476884", make: "Buick", model: "LeSabre")
        comparable = new Listing(vin: "XNPDH4AE0EH476884", make: "Buick", model: "LESABRE")
    }

    def "all matching except for vin"() {
        given:
            AssessedVehicle assessedVehicle = new AssessedVehicle(vin: "5NPDH4AE0EH476884", make: "Buick", model: "LeSabre")
            Listing comparable = new Listing(vin: "XNPDH4AE0EH476884", make: "Buick", model: "LESABRE")
        when:
            Comparison comparison = new VehicleComparator().compare(assessedVehicle, comparable)
        then:
            comparison.valid == true
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
            comparison.valid == true
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
            comparison.valid == false
            comparison.comparable == comparable
    }

    def "differ by one accident"() {
        given:
            assessedVehicle.numberOfAccidents = 0
            comparable.numberOfAccidents = 1
            comparable.price = 10000
        when:
            Comparison comparison = new VehicleComparator().compare(assessedVehicle, comparable)
        then:
            comparison.valid == true
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
            comparison.valid == true
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
            comparison.valid == true
            comparison.priceDifferenceFromAssessed == -40000
    }

    def "one year"() {
        given:
            assessedVehicle.year = 2018
            comparable.year = 2017
            comparable.price = 100
        when:
            Comparison comparison = new VehicleComparator().compare(assessedVehicle, comparable)
        then:
            comparison.valid == true
            comparison.priceDifferenceFromAssessed == -10
    }

    def "number of owners"() {
        given:
            assessedVehicle.numberOfOwners = 1
            comparable.numberOfOwners = 2
            comparable.price = 100
        when:
            Comparison comparison = new VehicleComparator().compare(assessedVehicle, comparable)
        then:
            comparison.valid == true
            comparison.priceDifferenceFromAssessed == -5
    }
}
