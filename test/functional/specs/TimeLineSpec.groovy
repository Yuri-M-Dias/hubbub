package specs

import geb.spock.GebReportingSpec
import pages.TimeLinePage
import spock.lang.Stepwise

@Stepwise
class TimeLineSpec extends GebReportingSpec {

    def "empty post"() {
        given: "I am at the TimeLine page"
        to TimeLinePage

        when: "I am submiting an empty post"
        timeLinePage.submitNewPost.click()

        then: "I am redirected back to the TimeLine page"
        at TimeLinePage
    }

}
