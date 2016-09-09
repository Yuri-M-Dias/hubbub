package hubbub

import com.grailsinaction.Post
import com.grailsinaction.Profile
import com.grailsinaction.Tag
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

    @Test
    void testQueryByExample(){
        new User(userId: 'glen', password: 'password').save()
        new User(userId: 'peter', password: 'password').save()
        new User(userId: 'cynthia', password: 'sesame').save()
        def userToFind = new User(userId: 'glen')
        def u1 = User.find(userToFind)
        assertEquals('password', u1.password)
        userToFind = new User(userId: 'cynthia')
        def u2 = User.find(userToFind)
        assertEquals('cynthia', u2.userId)
        userToFind = new User(password: 'password')
        def u3 = User.findAll(userToFind)
        assertEquals(['glen', 'peter'], u3*.userId)
    }

    @Test
    void testQueryProjections(){
        def glen = new User(userId: 'glen', password: 'password').save()
        def groovyTag = new Tag(name: 'groovy')
        glen.addToTags(groovyTag)
        def grailsTag = new Tag(name: 'grails')
        glen.addToTags(grailsTag)
        new Post(content: 'Glen posted this 1.', user: glen, tags: [grailsTag]).save()
        new Post(content: 'Glen posted this 2.', user: glen, tags: [groovyTag]).save()
        new Post(content: 'Glen posted this 3.', user: glen, tags: [groovyTag]).save()
        assertEquals(['grails', 'groovy'], glen.tags*.name.sort())
        def tagList = Post.withCriteria {
            createAlias("user", "u")
            createAlias("tags", "t")
            eq("u.userId", "glen")
            projections {
                groupProperty("t.name")
                count("t.id")
            }
        }
        def tagCloudMap = tagList.inject([ : ]) { map, tag ->
            map[ tag[0] ] = tag[1]
            map
        }
        def tagCloudMapExpected = [grails: 1L, groovy: 2L]
        assertEquals(tagCloudMapExpected, tagCloudMap)
    }

    @Test
    void testBasicHQL(){
        def glen = new User(userId: 'glen', password: 'password').save()
        def foundGlen = User.find("FROM User u WHERE u.userId = :uid", [uid: 'glen'])
        assertEquals(glen, foundGlen)
    }

}
