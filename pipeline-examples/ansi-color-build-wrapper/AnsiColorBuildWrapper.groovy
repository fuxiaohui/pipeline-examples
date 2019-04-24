// This shows a simple build wrapper example, using the AnsiColor plugin.
// AnsiColor插件的使用
// 可参照: https://wiki.jenkins.io/display/JENKINS/AnsiColor+Plugin
node {
    // This displays colors using the 'xterm' ansi color map.
    // 使用xterm的配色方式
    ansiColor('xterm') {
        // Just some echoes to show the ANSI color.
        // 展示所有的ANSI颜色
        // 可参照: http://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html#256-colors
        stage("8 Colors") {
            for (int i = 31; i <= 37; i++) {
                echo("\u001B[${i}mI'm Good\u001B[0m")
            }
        }

        stage("16 Colors") {
            for (int i = 31; i <= 37; i++) {
                echo("\u001B[${i};1mI'm Good\u001B[0m")
            }
        }

        stage("256 Colors") {
            for (int i = 0; i <= 255; i++) {
                echo("\u001b[38;5;${i}mI'm Good\u001B[0m")
            }
        }
    }
}
