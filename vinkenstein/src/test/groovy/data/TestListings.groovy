package data

import domain.Listing

class TestListings {
    static List<Listing> dataset = [
            new Listing(vin: "1FM5K8F82EGA64580", make: "FORD", model: "F150", year: 2014, numberOfAccidents: 1, numberOfOwners: 3, price: 23000),
            new Listing(vin: "2HHES36844H003626", make: "ACURA", model: "MDX", year: 2004, numberOfAccidents: 1, numberOfOwners: 4, price: 24000),
            new Listing(vin: "2HHES36844H003626", make: "ACURA", model: "MDX", year: 2004, numberOfAccidents: 1, numberOfOwners: 4, price: 23500),
            new Listing(vin: "19UYA125XVL010413", make: "ACURA", model: "MDX", year: 1997, numberOfAccidents: 1, numberOfOwners: 7, price: 12000),
            new Listing(vin: "2HHES36844H003626", make: "ACURA", model: "MDX", year: 2004, numberOfAccidents: 1, numberOfOwners: 4, price: 22000),
            new Listing(vin: "19UYA1254VL011797", make: "ACURA", model: "MDX", year: 1997, numberOfAccidents: 1, numberOfOwners: 1, price: 14000),
            new Listing(vin: "19UYA1152VL019947", make: "ACURA", model: "MDX", year: 1997, numberOfAccidents: 0, numberOfOwners: 6, price: 15000)
    ]

    static {
        File testData = new File("src/test/resources/sample-listings.dat")
        println testData.exists()
        testData.eachLine {
            String[] chunks = it.split("\\|")
            Listing listing = new Listing(vin: chunks[0],
                    make: chunks[1],
                    model: chunks[2],
                    year: Integer.valueOf(chunks[3]),
                    numberOfAccidents: Integer.valueOf(chunks[4]),
                    numberOfOwners: Integer.valueOf(chunks[5]),
                    price: Integer.valueOf(chunks[6]),
            )
            dataset << listing
        }
    }

    static List<Listing> getByMake(String make) {
        dataset.findAll { it.getMake().equalsIgnoreCase(make) };
    }

    static List<String> makes() {
        return dataset.groupBy {it.make}.keySet().toList()
    }

    static List<String> modelsfForMake(String make) {
        return dataset.findAll{it.make == make}.groupBy {it.model}.keySet().toList()
    }

    static List<Listing> getByMakeModel(String make, String model) {
        return dataset.findAll {it.make == make && it.model == model}
    }

    static Listing getByVin(String vin) {
        return dataset.find {it.vin == vin}
    }
}
