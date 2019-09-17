package listings

import listings.domain.History
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HistoryController {

    @RequestMapping("/history")
    public History histories(@RequestParam (required = true) String vin) {
        return TestListings.byVin(vin)
    }

    @RequestMapping("/control")
    public ResponseEntity control(
            @RequestParam (required = true) Integer accidentDelta,
            @RequestParam (required = true) Integer ownersDelta

            ) {

        TestListings.increment(accidentDelta, ownersDelta)

        return new ResponseEntity<> (HttpStatus.ACCEPTED)

    }
}
