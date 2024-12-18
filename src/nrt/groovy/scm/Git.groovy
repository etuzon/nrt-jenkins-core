package nrt.groovy.scm


class Git {

    def static pull(String branchName, File dir) {
        println("Git pull origin ${branchName}")

        def process = "Git pull origin ${branchName}".execute(null, dir)
        process.waitFor()

        println(process.text)

        if (process.exitValue() != 0) {
            throw new Exception("Git pull failed in directory: ${dir.path}.")
        }

        println("Git pull successful in directory: ${dir.path}")
    }
}
