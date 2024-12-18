package nrt.groovy.scm
import nrt.groovy.io.Logger

class Git {

    def static pull(String branchName, File dir) {
        println("EYALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL")
        Logger.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")
        Logger.println("git pull origin ${branchName}")

        def process = "Git pull origin ${branchName}".execute(null, dir)
        process.waitFor()

        Logger.println(process.text)

        if (process.exitValue() != 0) {
            throw new Exception("Git pull failed in directory: ${dir.path}.")
        }

        Logger.println("Git pull successful in directory: ${dir.path}")
    }
}
