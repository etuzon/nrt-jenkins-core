package nrt.groovy.io


/***
 * Change directory.
 *
 * @param dirPath
 * @return file of the new directory.
 */
def cd(String dirPath) {
    println("cd ${dirPath} && cd")

    def dir = new File(dirPath)

    if (dir.exists() && dir.isDirectory()) {
        println("Changing directory to: ${dirPath}")
        return dir
    }

    throw new Exception("Failed to change directory to: ${dirPath}")
}

return this
