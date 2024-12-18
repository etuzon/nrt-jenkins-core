package nrt.jenkins.groovy.process

import nrt.jenkins.groovy.logger.Logger
import nrt.jenkins.groovy.utils.TimeUtil

class NrtProcess {
    Logger logger

    NrtProcess() {
        logger = new Logger()
    }

    def executeCommand(String command, File dir, timeout, isKillOnTimeout = true, commandToPrint = null) {

        commandToPrint = commandToPrint ?: command

        def process = command.execute(null, dir)

        def output = new StringBuffer()
        def error = new StringBuffer()

        process.consumeProcessOutput(output, error)

        boolean isKilled = waitForProcess(process, timeout)

        if (!isKilled) {

            if (output.toString()) {
                logger.debug("Command [${commandToPrint}] output: ${output.toString()}")
            }

            if (error.toString()) {
                logger.error("Command [${commandToPrint}] error: ${error.toString()}")
            }

            if (isKillOnTimeout) {
                logger.debug("Killing process [${commandToPrint}]")
                process.destroy()
            }

            throw new Exception("Command [${commandToPrint}] timed out in [${dir.path}]")
        }

        return [process, output, error]
    }

    static boolean waitForProcess(process, timeout) {
        def startTime = System.currentTimeMillis()

        while (process.isAlive() && !TimeUtil.isTimeout(startTime, timeout)) {
            sleep(200)
        }

        return !process.isAlive()
    }
}
