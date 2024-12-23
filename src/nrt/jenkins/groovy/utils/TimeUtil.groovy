package nrt.jenkins.groovy.utils

class TimeUtil {

    static boolean isTimeout(startTime, timeout) {
        return startTime + timeout < System.currentTimeMillis()
    }
}
