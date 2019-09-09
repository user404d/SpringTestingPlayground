package listings

import listings.domain.History
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HistoryController {

    static Map<String, History> histories = [
            '1FM5K8F82EGA64580': new History(vin: '1FM5K8F82EGA64580',  numberOfOwners: 1,   numberOfAccidents: 3)  ,
            'WBSBG9321VEY74382': new History(vin: 'WBSBG9321VEY74382',  numberOfOwners: 2,   numberOfAccidents: 12)
    ].withDefault {String vin -> new History(vin: vin,                  numberOfOwners: 0,   numberOfAccidents: 0)}

    @RequestMapping("/history")
    public @ResponseBody
    History histories(@RequestParam (required = true) String vin) {
        return histories[vin]
    }
}
