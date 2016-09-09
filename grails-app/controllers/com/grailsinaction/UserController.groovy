package com.grailsinaction

class UserController {
    def scaffold = true

    def search = {}

    def results = {
        def users = User.findAllByUserIdLike("%${params.userId}%")
        return [users: users, term: params.userId]
    }

    def advSearch = {}

    def advResults = {
        def profileProps = Profile.metaClass.properties*.name
        def profiles = Profile.withCriteria {
            "${params.queryType}" {
                params.each { field, value ->
                    if (profileProps.grep(field) && value) {
                        ilike(field, value)
                    }
                }
            }
        }
        [ profiles: profiles ]
    }

    def register2 = { UserRegistrationCommand urc ->
        if(urc.hasErrors()){
            return [ user: urc ]
        } else {
            def user = new User(urc.properties)
            user.profile = new Profile(urc.properties)
            if(user.save()){
                flash.message = "Welcome aboard, ${urc.fullName ?: urc.userId}"
                redirect(uri: '/')
            } else {
                //Not unique userId?
                return [ user: urc]
            }
        }
    }

}

class UserRegistrationCommand {
    String userId
    String password
    String passwordRepeat

    byte[] photo
    String fullName
    String bio
    String homepage
    String email
    String timezone
    String country
    String jabberAddress

    static constraints = {
        userId(size: 3..20)
        password(size: 6..8, blank: false,
                validator: { passwd, urc ->
                    return passwd != urc.userId
                })
        passwordRepeat(nullable: false,
                validator: { passwd2, urc ->
                    return passwd2 == urc.password
                })
        fullName(nullable: true)
        bio(nullable: true, maxSize: 1000)
        homepage(url: true, nullable: true)
        email(email: true, nullable: true)
        photo(nullable: true)
        country(nullable: true)
        timezone(nullable: true)
        jabberAddress(email: true, nullable: true)
    }

}
