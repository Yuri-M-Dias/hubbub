package pages

import org.codehaus.groovy.grails.plugins.webdriver.ButtonElement
import org.codehaus.groovy.grails.plugins.webdriver.WebDriverPage

class TimeLinePage extends WebDriverPage {
    static expectedTitle = ~".*Hubbub.*"
    //static expectedURL = ~/\/post\/timeline\/.*/

    String postContent
    String userFullname

    ButtonElement<TimeLinePage> submitNewPost

    static elements = {
    }

}
