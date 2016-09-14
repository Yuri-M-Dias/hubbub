package com.grailsinaction

class PostController {

    def postService
    def scaffold = true

    def index = {
        if (!params.id)
            params.id = "chuck_norris"
        redirect(action: 'timeline', params: params)
    }

    def timeline = {
        def user = User.findByUserId(params.id)
        [ user : user ]
    }

    def addPost = {
        try {
            def newPost = postService.createPost(params.id, params.content)
            flash.message = "Added new post: ${newPost.content}"
        } catch (PostException pe) {
            flash.message = pe.message
            log.info(pe)
        }
        log.info(params)
        redirect(action: 'timeline', id: params.id)
    }

    def register = {
        def user = new User(params)
        if (user.validate()) {
            user.save()
            flash.message = "Successfully Created User"
            redirect(uri: '/')
        } else {
            flash.message = "Error Registering User"
            return [ user: user ]
        }
    }

}
