package data

import domain.Listing
import groovy.json.JsonOutput

class DataConverter {
    static def main(args) {
        File file = new File("vinkenstein/src/test/resources/sample-listings.dat")
        File output = new File("vinkenstein/src/test/resources/history.json")
        output.delete()

        def listings = []//[:].withDefault { [] }

        file.eachLine {

            //Random rand = new Random()

            //if (size < 100) {
            String[] chunks = it.split("\\|")
            Listing listing = new Listing(vin: chunks[0],
                    make: chunks[1],
                    model: chunks[2],
                    year: Integer.valueOf(chunks[3]),
                    numberOfAccidents: Integer.valueOf(chunks[4]),
                    numberOfOwners: Integer.valueOf(chunks[5]),
                    price: Integer.valueOf(chunks[6]),
            )

           listings << listing


        }

        output << JsonOutput.toJson(listings)

//println "def makes = ["
//        makes.entrySet().each { makesEntry ->
//           println "  '"+makesEntry.key+"': ["
//            println "    '"+makesEntry.value[0]+"', ["
//            println "        "+makesEntry.value[1].entrySet().collect { modelEntry ->
//                return "'"+modelEntry.key+"': '"+modelEntry.value+"'"
//            }.join(",")
//            println "    ]"
//            println "  ],"
//
//        }
//println "]"
        //println makes
        println "done, read ${listings.size()}"
    }

}

