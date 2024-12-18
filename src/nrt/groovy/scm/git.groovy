package nrt.groovy.scm


def pull(String branchName, File dir) {
    println("git pull origin ${branchName}")

    def process = "git pull origin ${branchName}".execute(null, dir)
    process.waitFor()

    println(process.text)

    if (process.exitValue() != 0) {
        throw new Exception("git pull failed in directory: ${dir.path}.")
    }

    println("git pull successful in directory: ${dir.path}")
}

return this
