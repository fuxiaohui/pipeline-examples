// 演示在不同的jenkins节点上执行

// 在jenkins的master节点上执行
node('master') {
    stage("first step on first node") {
        // Make the output directory.
        // 创建output文件夹
        sh "mkdir -p output"

        // Write a text file there.
        // 创建文件, 并向文件中添加内容
        writeFile file: "output/somefile", text: "Hey look, some text."

        // Stash that directory and file.
        // Note that the includes could be "output/", "output/*" as below, or even
        // "output/**/*" - it all works out basically the same.
        stash name: "first-stash", includes: "output/*"
    }
}

// Next, we'll make a new directory on a second node, and unstash the original
// into that new directory, rather than into the root of the build.


// 在jenkins的second-node节点上执行
node('master') {
    stage("second step on second node") {
        // Run the unstash from within that directory!
        dir("first-stash") {
            unstash "first-stash"
        }

        // Look, no output directory under the root!
        // pwd() outputs the current directory Pipeline is running in.
        sh "ls -la ${pwd()}"

        // And look, output directory is there under first-stash!
        sh "ls -la ${pwd()}/first-stash"
    }
}
