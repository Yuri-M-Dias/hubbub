package com.grailsinaction

import java.text.SimpleDateFormat

class UserController {
    def scaffold = true

    def profile = {}

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

    def stats = {
        User user = User.findByUserId(params.userId)
        if (user) {
            def sdf = new SimpleDateFormat('E')
            def postsOnDay = [:]
            user.posts.each { post ->
                def dayOfWeek = sdf.format(post.dateCreated)
                if (postsOnDay[dayOfWeek]) {
                    postsOnDay[dayOfWeek]++
                } else {
                    postsOnDay[dayOfWeek] = 1
                }
            }
            return [ userId: params.userId, postsOnDay: postsOnDay ]
        } else {
            flash.message = "No stats available for that user"
            redirect(uri: "/")
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
        photo(nullable: true, maxSize: 100000)
        country(nullable: true)
        timezone(nullable: true)
        jabberAddress(email: true, nullable: true)
    }

}
