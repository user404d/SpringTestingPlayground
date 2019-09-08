package listings

import listings.domain.RawListing
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ListingsController {
    @RequestMapping("/listings")
    public @ResponseBody
    List<RawListing> listings() {
        return [
                new RawListing(vin: '1FM5K8F82EGA64580', price: 24000),
                new RawListing(vin: 'WBSBG9321VEY74382', price: 3500)
        ]
    }

}
