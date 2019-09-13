package listings

import listings.domain.History
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HistoryController {

    @RequestMapping("/history")
    public @ResponseBody
    History histories(@RequestParam (required = true) String vin) {
        return TestListings.dataset[vin]
    }
}
