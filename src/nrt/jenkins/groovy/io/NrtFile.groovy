package nrt.jenkins.groovy.io

import nrt.jenkins.groovy.logger.Logger

class NrtFile {
    Logger logger

    NrtFile() {
        logger = new Logger()
    }

    File convertToDirectory(String dirPath) {

        def dir = new File(dirPath)

        if (dir.exists() && dir.isDirectory()) {
            logger.debug("Verified that directory [${dirPath}] exists")
            return dir
        }

        throw new Exception("Failed to change directory to [${dirPath}]")
    }
}
