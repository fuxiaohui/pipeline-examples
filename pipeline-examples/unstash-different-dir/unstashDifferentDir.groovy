// 演示在不同的jenkins节点上执行

// 在jenkins的master节点上执行
node('master') {
    stage("first step on master node") {
        // Make the output directory.
        // 创建output文件夹
        sh "mkdir -p output"

        // Write a text file there.
        // 创建文件, 并向文件中添加内容
        writeFile file: "output/somefile", text: "Hey look, some text."

        // Stash that directory and file.
        // Note that the includes could be "output/", "output/*" as below, or even
        // "output/**/*" - it all works out basically the same.
        // 把output文件夹及其内容存入first-stash文件夹
        stash name: "first-stash", includes: "output/*"
    }
}


// 在jenkins的slave节点上执行
node('slave') {
    stage("second step on slave node") {
        // Run the unstash from within that directory!
        // 把first-stash文件夹中内容提取出来
        dir("first-stash") {
            unstash "first-stash"
        }

        // Look, no output directory under the root!
        // pwd() outputs the current directory Pipeline is running in.
        // 展示当前工作区中的所有文件
        sh "ls -la ${pwd()}"

        // And look, output directory is there under first-stash!
        // 展示first-stash文件夹中的所有文件
        sh "ls -la ${pwd()}/first-stash"
    }
}
