package listings

import groovy.json.JsonSlurper
import listings.domain.History

class TestListings {
    static def histories = new JsonSlurper().parse(new File('history/src/main/resources/history.json'))


    static Map<String, History> dataset = [
            'WBSBG9321VEY74382': new History(vin: 'WBSBG9321VEY74382',  numberOfOwners: 2,   numberOfAccidents: 12),
            "1FM5K8F82EGA64580": new History(vin: "1FM5K8F82EGA64580", numberOfAccidents: 1, numberOfOwners: 3), //  make: "FORD", model: "F150", year: 2014,  price: 23000),
            "2HHES36844H003626": new History(vin: "2HHES36844H003626", numberOfAccidents:	1, numberOfOwners:	4), //, make:	"ACURA" ,model: "MDX" , year: 2004,  price: 24000),
            "2HHES36844H003626": new History(vin: "2HHES36844H003626", numberOfAccidents:	1, numberOfOwners:	4), //, make:	"ACURA" ,model: "MDX" , year: 2004,  price: 23500),
            "19UYA125XVL010413": new History(vin: "19UYA125XVL010413", numberOfAccidents:	1, numberOfOwners:	7), //, make:	"ACURA" ,model: "MDX" , year: 1997,  price: 12000),
            "2HHES36844H003626": new History(vin: "2HHES36844H003626", numberOfAccidents:	1, numberOfOwners:	4), //, make:	"ACURA" ,model: "MDX" , year: 2004,  price: 22000),
            "19UYA1254VL011797": new History(vin: "19UYA1254VL011797", numberOfAccidents:	1, numberOfOwners:	1), //, make:	"ACURA" ,model: "MDX" , year: 1997,  price: 14000),
            "19UYA1152VL019947": new History(vin: "19UYA1152VL019947", numberOfAccidents:	0, numberOfOwners:	6), //, make:	"ACURA" ,model: "MDX" , year: 1997,  price: 15000)
    ]

    static History byVin(String vin) {
        History result = dataset[vin]
        if (result == null) {
            def history = histories.find {it.vin == vin}
            if (history != null) {
                result = new History (vin: vin, numberOfAccidents: history.numberOfAccidents, numberOfOwners: history.numberOfOwners)
            }
        }
        if (result == null) {
            result = new History(vin: vin,numberOfOwners: 0,   numberOfAccidents: 0)
        }
        return result
    }

    static def increment(int accidentDelta, int ownersDelta) {
        Random random = new Random()
        histories.each {
            it.numberOfAccidents += random.nextInt(accidentDelta)
            it.numberOfOwners += random.nextInt(ownersDelta)
        }
        dataset.each {key, value ->
            value.numberOfAccidents += random.nextInt(accidentDelta)
            value.numberOfOwners += random.nextInt(ownersDelta)
        }
    }
}
