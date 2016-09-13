package tests

import junit.framework.Assert
import org.codehaus.groovy.grails.plugins.webdriver.WebDriverHelper
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import static org.junit.Assert.*
import pages.TimeLinePage

class TimeLineTests {

    @Rule
    public WebDriverHelper webdriver = new WebDriverHelper()

    private TimeLinePage timeLinePage

    @Before
    public void openTimeLinePageThroughUser() {
        timeLinePage = webdriver.open('/users/glen', TimeLinePage.class)
    }

    @Test
    public void testEmptyPost() {
        assertNotNull timeLinePage.postContent
        assertNotNull timeLinePage.submitNewPost
        timeLinePage.submitNewPost.clickStay()
        assertTrue webdriver.currentPage.currentURL.contains("login")
    }

    @Test
    public void testSamplePost() {
        assertNotNull timeLinePage.postContent
        assertNotNull timeLinePage.submitNewPost
        timeLinePage.postContent = 'Glen posted this through the test.'
        timeLinePage.submitNewPost.clickStay()
        assertTrue webdriver.currentPage.currentURL.contains("users")
    }

    @Test
    public void testSQLInjection() {
        assertNotNull timeLinePage.postContent
        assertNotNull timeLinePage.submitNewPost
        timeLinePage.postContent = "; DROP TABLE User;"
        timeLinePage.submitNewPost.clickStay()
        assertTrue webdriver.currentPage.currentURL.contains("users")
    }

}
