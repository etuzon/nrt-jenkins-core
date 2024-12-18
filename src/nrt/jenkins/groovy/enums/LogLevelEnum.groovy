package nrt.jenkins.groovy.enums

enum LogLevelEnum {
    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4),
    FATAL(5)

    private final int level

    LogLevelEnum(int level) {
        this.level = level
    }

    int getLevel() {
        return level
    }
}
