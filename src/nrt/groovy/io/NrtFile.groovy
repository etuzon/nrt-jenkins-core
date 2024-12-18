package nrt.groovy.io


class NrtFile {
    /***
     * Change directory.
     *
     * @param dirPath
     * @return NrtFile of the new directory.
     */
    def static cd(String dirPath) {
        println("cd ${dirPath} && cd")

        def dir = new File(dirPath)

        if (dir.exists() && dir.isDirectory()) {
            println("Changing directory to: ${dirPath}")
            return dir
        }

        throw new Exception("Failed to change directory to: ${dirPath}")
    }
}
