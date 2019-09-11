package listings

import listings.domain.RawListing
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import java.util.concurrent.CopyOnWriteArrayList

@RestController
class ListingsController {
    static CopyOnWriteArrayList<RawListing> rawListings;
    public ListingsController() {
        rawListings = new CopyOnWriteArrayList<>();
        rawListings << new RawListing(vin: '1FM5K8F82EGA64580', price: 24000)
        rawListings << new RawListing(vin: 'WBSBG9321VEY74382', price: 3500)

    }

    @RequestMapping("/listings")
    public List<RawListing> listings() {
        return rawListings
    }

    @RequestMapping("/clear")
    public int clear() {
        rawListings.clear()
        return rawListings.size()
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public ResponseEntity<Integer> add(@RequestBody RawListing rawListing) {
        synchronized (rawListings) {
            RawListing existing = rawListings.find {it.vin == rawListing.vin};
            println "asked to add: ${rawListing.vin}"
            if (!existing) {
                println "did not find, so adding"
                rawListings << rawListing
            } else {
                println "exists, so ignoring"
            }
        }
        return new ResponseEntity<>(rawListings.size(),HttpStatus.OK);
    }

    @RequestMapping(value="/delete", method= RequestMethod.DELETE)
    public ResponseEntity<Integer> delete(@RequestParam (required = true) String vin) {
        synchronized (rawListings) {
            RawListing rawListing = rawListings.find {it.vin == vin};
            if (rawListing != null) {
                rawListings.remove(rawListing);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
