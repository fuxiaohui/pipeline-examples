// This shows a simple example of how to archive the build output artifacts.
// 归档文件的例子
node {
    // 编译阶段
    stage("Create build output") {
        // Make the output directory.
        // 创建输出的文件夹
        sh "mkdir -p output"

        // Write an useful file, which is needed to be archived.
        // 编写需要归档文件
        writeFile file: "output/usefulfile.txt", text: "This file is useful, need to archive it."

        // Write an useless file, which is not needed to be archived.
        // 编写不需要归档的文件
        writeFile file: "output/uselessfile.md", text: "This file is useless, no need to archive it."
    }

    // 归档阶段
    stage("Archive build output") {
        // Archive the build output artifacts.
        // 归档输出文件
        archiveArtifacts artifacts: 'output/*.txt', excludes: 'output/*.md'
    }

}
