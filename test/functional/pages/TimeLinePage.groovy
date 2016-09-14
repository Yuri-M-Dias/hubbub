package pages

import geb.Page

class TimeLinePage extends Page {
    static url = "post/timeline/glen"

    static at = {
        title.contains("Hubbub")
    }

    static content = {
        postContent { $("#postContent") }
        userFullname { $("#userFullname") }
        submitNewPost { $("#submitNewPost")}
    }

}
