package hubbub

class DateTagLib {

    static namespace = "h"

    static final long second = 1000
    static final long minute = second * 60
    static final long hour = minute * 60
    static final long day = hour * 24

    def dateFromNow = { attrs ->
        def date = attrs.date
        def niceDate = getNiceDate(date)
        out << niceDate
    }

    static String getNiceDate(Date date){
        def now = new Date()
        def diff = Math.abs(now.time - date.time)
        def niceTime = ""
        long calc = 0;
        calc = Math.floor(diff / day)
        if (calc) {
            niceTime += calc + " day" + (calc > 1 ? "s " : " ")
            diff %= day
        }
        calc = Math.floor(diff / hour)
        if (calc) {
            niceTime += calc + " hour" + (calc > 1 ? "s " : " ")
            diff %= hour
        }
        calc = Math.floor(diff / minute)
        if (calc) {
            niceTime += calc + " minute" + (calc > 1 ? "s " : " ")
            diff %= minute
        }
        if (!niceTime) {
            niceTime = "Right now"
        } else {
            niceTime += (date.time > now.time) ? "from now" : "ago"
        }
        return niceTime
    }

}
