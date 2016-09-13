package hubbub

import com.grailsinaction.User

class LameSecurityFilters {

    def filters = {
        all(controller:'post', action:'(addPost|deletePost)') {
            before = {
                if(params.logonId) {
                    session.user = User.findByUserId(params.userId)
                }
                if(!session.user){
                    //redirect(controller: 'login', action: 'form')
                    return false
                }
            }
            after = { Map model ->
            }
            afterView = { Exception e ->
                log.debug("Finished running ${controllerName} - ${actionName}")
            }
        }
    }

}
