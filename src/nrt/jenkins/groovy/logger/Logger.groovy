package nrt.jenkins.groovy.logger

import nrt.jenkins.groovy.enums.LogLevelEnum

class Logger {
    private static logLevel = LogLevelEnum.INFO
    private static steps = null

    Logger() {
        if (steps == null) {
            throw new Exception("Logger is not initialized. Use Logger.init(steps) to initialize it.")
        }
    }

    def debug(String message) {
        if (LogLevelEnum.DEBUG.getLevel() >= logLevel.getLevel()) {
            steps.echo("[DEBUG] ${message}")
        }
    }

    def info(String message) {
        if (LogLevelEnum.INFO.getLevel() >= logLevel.getLevel()) {
            steps.echo("[INFO] ${message}")
        }
    }

    def warn(String message) {
        if (LogLevelEnum.WARN.getLevel() >= logLevel.getLevel()) {
            steps.echo("[WARN] ${message}")
        }
    }

    def error(String message) {
        if (LogLevelEnum.ERROR.getLevel() >= logLevel.getLevel()) {
            steps.echo("[ERROR] ${message}")
        }
    }

    def fatal(String message) {
        if (LogLevelEnum.FATAL.getLevel() >= logLevel.getLevel()) {
            steps.echo("[FATAL] ${message}")
        }
    }

    static init(steps, logLevel = LogLevelEnum.INFO) {
        Logger.steps = steps
        Logger.logLevel = logLevel
    }
}
