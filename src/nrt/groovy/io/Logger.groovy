package nrt.groovy.io

class Logger {
    def static steps

    def static println(String message) {
        Logger.steps.echo(message)
    }

    def static init(steps) {
        Logger.steps = steps
    }
}
