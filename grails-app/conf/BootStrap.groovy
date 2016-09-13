import com.grailsinaction.Post
import com.grailsinaction.Profile
import com.grailsinaction.Tag
import com.grailsinaction.User
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        switch (Environment.current) {
            case Environment.TEST:
                createAdminUserIfRequired();
                if (!User.findByUserId('glen')) {
                    createBootstrapUsers()
                }
                break;
            case Environment.DEVELOPMENT:
                createAdminUserIfRequired();
                if (!User.findByUserId('glen')) {
                    createBootstrapUsers()
                }
                break;
            case Environment.PRODUCTION:
                println "No special configuration required"
                if (!User.findByUserId('glen')) {
                    createBootstrapUsers()
                }
                break;
        }
    }

    def destroy = {
    }

    def createAdminUserIfRequired() {
        if (!User.findByUserId("admin")) {
            println "Fresh database. Creating ADMIN user."
            def profile = new Profile(email: "admin@zg.com")
            def user = new User(userId: "admin", password: "secret", profile: profile).save()
        } else {
            println "Existing admin user, skipping creation."
        }
    }

    def createBootstrapUsers() {
        User glen = new User(userId: 'glen', password: 'password',
                profile: new Profile(country: 'Brazil', email: 'glen@glen.com',
                        fullName: 'Glen, the Fourth of His Name'))
                .save()
        def groovyTag = new Tag(name: 'Groovy', user: glen)
        new Post(content: 'Glen posted this 1.', user: glen, tags: [groovyTag]).save()
        new Post(content: 'Glen posted this 2.', user: glen, tags: [groovyTag]).save()
        new Post(content: 'Glen posted this 3.', dateCreated: getTomorrow(),
                user: glen, tags: [groovyTag]).save()
        new Post(content: 'Glen posted this 4.', dateCreated: getTomorrow(),
                user: glen, tags: [groovyTag]).save()
        User chuckNorris = new User(userId: 'chuck_norris', password: 'roundhouse').save()
        new Post(content: 'Chuck posted this 1.', user: chuckNorris, tags: [groovyTag]).save()
        new Post(content: 'Chuck posted this 2.', user: chuckNorris, tags: [groovyTag]).save()
        new User(userId: 'someone', password: 'something').save()
    }

    Date getTomorrow(){
        //Warning for crude implementation
        Date today = new Date()
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24))
        tomorrow
    }

}
