package com.grailsinaction



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserTests {

    void testConstraints() {
        def will = new User(userId: "william")
        mockForConstraintsTests(User)
        def testUser = new User()
        assertFalse testUser.validate()
        assertEquals "nullable",
                testUser.errors["userId"]
        assertEquals "nullable",
                testUser.errors["password"]
        testUser = new User(userId: "william", password: "william")
        assertFalse testUser.validate()
        assertEquals "unique", testUser.errors["userId"]
        assertEquals "validator", testUser.errors["password"]
        testUser = new User(userId: "glen", password: "passwd")
        assertTrue testUser.validate()
    }

}
