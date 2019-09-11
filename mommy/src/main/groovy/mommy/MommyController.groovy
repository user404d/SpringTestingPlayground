package mommy

import mommy.domain.Mommy
import mommy.domain.Year
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MommyController {

    static Map<String, Mommy> mommies = [
            '1FM5K8F82EGA64580': new Mommy(vin: '1FM5K8F82EGA64580', make: "FORD", model: "F150", year: 2014)  ,
            'WBSBG9321VEY74382': new Mommy(vin: 'WBSBG9321VEY74382', make: "HONDA", model: "ODYSSEY", year: 2015)
    ].withDefault {String vin -> new Mommy(vin: vin, make: "UNKNOWN", model: "UNKNOWN", year: Year.get(vin))}

    @RequestMapping("/mommy")
    public @ResponseBody
    Mommy mommy(@RequestParam (required = true) String vin) {
        return mommies[vin]
    }
}
