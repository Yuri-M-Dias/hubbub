package com.grailsinaction

import javax.jws.soap.SOAPBinding

class PhotoUploadCommand {
    byte[] photo
    String userId
}

class ImageController {

    def upload = { PhotoUploadCommand puc ->
        def user = User.findByUserId(puc.userId)
        user.profile.photo = puc.photo
        redirect(action: 'view', id: puc.userId)
    }

    def form = {
        // Upload form view
        [userList: User.list()]
    }

    def view = {
        //"View photo" page
    }

    def rawUpload = {
        def mhsr = request.getFile('photo')
        if (!mhsr?.empty && mhrs.size < 1024 * 200) {
            mhsr.transferTo(new File("/hubbub/images/${params.userId}/mugshot.gif"))
        }
    }

    def renderImage = {
        def user = User.findByUserId(params.id)
        if (user?.profile?.photo) {
            response.setContentLength(user.profile.photo.length)
            response.outputStream.write(user.profile.photo)
        } else {
            response.sendError(404)
        }
    }

}
