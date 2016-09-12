package com.grailsinaction

import com.grailsrocks.functionaltest.*

class PostListFunctionalTests extends BrowserTestCase {

    void testTimelineNotLoggedIn() {
        get("/post/timeline")
        assertStatus 500
    }

    void testTimeline() {
        post("/login/index") {
            userId = "peter"
            password = "password"
        }
        assertTitle "Hubbub » New Post"
        assertContentContains(
                "What are you hacking on right now?")
    }

    void testSomeWebsiteFeature() {
        // Here call get(uri) or post(uri) to start the session
        // and then use the custom assertXXXX calls etc to check the response
        //
        // get('/something')
        // assertStatus 200
        // assertContentContains 'the expected text'
    }
}
