package hubbub

class UtilTagLib {
    static namespace = "h"

    def lameBrowser = { attrs, body ->
        if (request.getHeader('User-Agent') =~ attrs.userAgent) {
            out << body
        }
    }

}
