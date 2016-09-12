package hubbub

import grails.test.mixin.TestFor
import grails.util.GrailsWebUtil

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(SecurityTagLib)
class SecurityTagLibTests {

    void testIsLoggedIn() {
        String testContent = "You are logged in!"
        request.session["user"] = "me"
        tagLib.isLoggedIn([:]) {-> testContent}
        //assertEquals testContent, tagLib.out.toString()
        assertOutputEquals(testContent, "<g:isLoggedIn>$testContent</g:isLoggedIn>")
    }

    void testIsLoggedInNonUser() {
        String testContent = "You are not logged in!"
        tagLib.isLoggedIn([:]) {-> testContent}
        //assertEquals "", tagLib.out.toString()
        assertOutputEquals("", "<g:isLoggedIn>$testContent</g:isLoggedIn>")
    }

}
