package hubbub

import com.grailsinaction.Profile
import com.grailsinaction.User

import static org.junit.Assert.*
import org.junit.*

class QueryIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testBasicDynamicFilters() {
        new User(userId: 'glen', password: 'secret',
                profile: new Profile(email: 'glen@glensmith.com')).save()
        new User(userId: 'peter', password: 'sesame',
                profile: new Profile(homepage: 'http://www.peter.com/')).save()
        def user = User.findByPassword('sesame')
        assertEquals 'peter', user.userId
        user = User.findByUserIdAndPassword('glen', 'secret')
        assertEquals 'glen', user.userId
        def now = new Date()
        def users = User.findAllByDateCreatedBetween(now - 1, now)
        assertEquals 2, users.size()
        def profiles = Profile.findAllByEmailIsNotNull()
        assertEquals 1, profiles.size()
    }

}
