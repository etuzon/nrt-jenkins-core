package nrt.jenkins.groovy.scm
import nrt.jenkins.groovy.logger.Logger
import nrt.jenkins.groovy.process.NrtProcess


class Git {
    def TIMEOUT_60_SECONDS = 60000

    String username
    String password
    Logger logger

    Git(username, password) {
        logger = new Logger()
        this.username = username
        this.password = password
    }

    def clone(String remoteRepositoryUrl, String branchName, File dir) {

        def reportRepositoryFullUrl = "https://${username}:${password}@${remoteRepositoryUrl}"
        def reportRepositoryFullUrlToPrint = "https://${username}:****@${remoteRepositoryUrl}"

        def command = "git clone -b ${branchName} ${reportRepositoryFullUrl} ."
        def commandToPrint = "git clone -b ${branchName} ${reportRepositoryFullUrlToPrint} ."

        logger.debug("Execute [${commandToPrint}] in [${dir.path}]")

        NrtProcess nrtProcess = new NrtProcess()

        def processDataList =
                nrtProcess.executeCommand(command, dir, 60000, false, commandToPrint)

        def process = processDataList[0]

        def error = processDataList[2].toString()

        if (process.exitValue() != 0) {
            logger.fatal("git clone output: ${error}")
            throw new Exception("Git clone failed in [${dir.path}]")
        }

        if (error) {
            logger.fatal("git clone output: ${error}")
            throw new Exception("Git clone failed in [${dir.path}]")
        }

        def output = processDataList[1].toString()

        if (output.contains("fatal")) {
            logger.fatal("git clone output: ${output}")
            throw new Exception("Git clone failed in [${dir.path}]")
        }

        logger.debug("git clone successful in [${dir.path}]")
    }

    String fetch(File dir) {

        def command = "git fetch"

        logger.debug("Execute [${command}] in [${dir.path}]")

        def nrtProcess = new NrtProcess()

        def processDataList = nrtProcess.executeCommand(command, dir, TIMEOUT_60_SECONDS)

        def process = processDataList[0]

        if (process.exitValue() != 0) {
            def error = processDataList[2].toString()

            logger.fatal("git fetch output: ${error}")
            throw new Exception("Git fetch failed in [${dir.path}]")
        }

        return processDataList[1].toString()
    }

    def stash(File dir) {

        def command = "git stash"

        logger.debug("Execute [${command}] in [${dir.path}]")

        def nrtProcess = new NrtProcess()

        def processDataList = nrtProcess.executeCommand(command, dir, TIMEOUT_60_SECONDS)

        def process = processDataList[0]

        if (process.exitValue() != 0) {
            def error = processDataList[2].toString()

            logger.fatal("git stash output: ${error}")
            throw new Exception("git stash failed in [${dir.path}]")
        }

        return processDataList[1].toString()
    }

    String status(File dir) {

        def command = "git status"

        logger.debug("Execute [${command}] in [${dir.path}]")

        def nrtProcess = new NrtProcess()

        def processDataList = nrtProcess.executeCommand(command, dir, TIMEOUT_60_SECONDS)

        return processDataList[1].toString()
    }

    def pull(String remoteRepositoryUrl, String branchName, File dir) {

        def reportRepositoryFullUrl = "https://${username}:${password}@${remoteRepositoryUrl}"
        def reportRepositoryFullUrlToPrint = "https://${username}:****@${remoteRepositoryUrl}"

        def command = "git pull ${reportRepositoryFullUrl} ${branchName}"
        def commandToPrint = "git pull ${reportRepositoryFullUrlToPrint} ${branchName}"

        logger.debug("Execute [${commandToPrint}] in [${dir.path}]")

        NrtProcess nrtProcess = new NrtProcess()

        def processDataList =
                nrtProcess.executeCommand(command, dir, 60000, false, commandToPrint)

        def process = processDataList[0]

        def error = processDataList[2].toString()

        if (process.exitValue() != 0) {
            logger.fatal("git pull output: ${error}")
            throw new Exception("Git pull failed in [${dir.path}]")
        }

        if (error) {
            logger.fatal("git pull output: ${error}")
            throw new Exception("Git pull failed in [${dir.path}]")
        }

        def output = processDataList[1].toString()

        if (output.contains("Aborting")) {
            logger.fatal("git pull output: ${output}")
            throw new Exception("Git pull failed in [${dir.path}]")
        }

        logger.debug("git pull successful in [${dir.path}]")
    }
}
