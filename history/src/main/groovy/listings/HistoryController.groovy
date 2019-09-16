package listings

import listings.domain.History
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HistoryController {

    @RequestMapping("/history")
    public History histories(@RequestParam (required = true) String vin) {
        return TestListings.byVin(vin)
    }

    @RequestMapping("/control")
    public Map<String, Object> control(
            @RequestParam (required = false) Boolean shouldStepNumberOfOwners,
            @RequestParam (required = false) Boolean shouldStepNumberOfAccidents,
            @RequestParam (required = false) Integer secondsBetweenSteps,
            @RequestParam (required = false) Integer stepSize

            ) {

        if (shouldStepNumberOfOwners != null) {
            TestListings.shouldStepNumberOfOwners = shouldStepNumberOfOwners
        }
        if (shouldStepNumberOfAccidents != null) {
            TestListings.shouldStepNumberOfAccidents = shouldStepNumberOfAccidents
        }
        if (secondsBetweenSteps != null) {
            TestListings.secondsBetweenSteps = secondsBetweenSteps
        }
        if (stepSize != null) {
            TestListings.stepSize = stepSize
        }

        Map<String, Object> status = [:]
        status.shouldStepNumberOfOwners =       TestListings.shouldStepNumberOfOwners
        status.shouldStepNumberOfAccidents =   TestListings.shouldStepNumberOfAccidents
        status.secondsBetweenSteps =   TestListings.secondsBetweenSteps
        status.stepSize =  TestListings.stepSize
        status.currentStep =  TestListings.currentStep
        return status

    }
}
