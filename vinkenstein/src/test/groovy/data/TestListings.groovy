package data

import domain.Listing

class TestListings {
    static List<Listing> dataset = [
            new Listing(vin: "1FM5K8F82EGA64580", make: "FORD", model: "F150", year: 2014, numberOfAccidents: 1, numberOfOwners: 3, price: 23000),
            new Listing(vin: "2HHES36844H003626", make:	"ACURA" ,model: "MDX" , year: 2004, numberOfAccidents:	1, numberOfOwners:	4, price: 24000),
            new Listing(vin: "2HHES36844H003626", make:	"ACURA" ,model: "MDX" , year: 2004, numberOfAccidents:	1, numberOfOwners:	4, price: 23500),
            new Listing(vin: "19UYA125XVL010413", make:	"ACURA" ,model: "MDX" , year: 1997, numberOfAccidents:	1, numberOfOwners:	7, price: 12000),
            new Listing(vin: "2HHES36844H003626", make:	"ACURA" ,model: "MDX" , year: 2004, numberOfAccidents:	1, numberOfOwners:	4, price: 22000),
            new Listing(vin: "19UYA1254VL011797", make:	"ACURA" ,model: "MDX" , year: 1997, numberOfAccidents:	1, numberOfOwners:	1, price: 14000),
            new Listing(vin: "19UYA1152VL019947", make:	"ACURA" ,model: "MDX" , year: 1997, numberOfAccidents:	0, numberOfOwners:	6, price: 15000)
    ]

    static def List<Listing> getByMake(String make) {
        dataset.findAll {it.getMake().equalsIgnoreCase(make)};
    }
}
