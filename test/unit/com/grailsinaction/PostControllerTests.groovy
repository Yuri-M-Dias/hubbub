package com.grailsinaction

import grails.test.MockUtils
import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PostController)
class PostControllerTests {

    void testShow() {
        MockUtils.mockDomain(User, [
                new User(userId: 'glen'),
                new User(userId: 'peter')
        ])
        this.controller.params.id = "peter"
        def model = this.controller.show()
        assertEquals "peter", model["viewUser"]?.userId
    }

}
