// This shows a simple build wrapper example, using the Timestamper plugin.
// 展示如何使用Timestamper插件
node {
    // Adds timestamps to the output logged by steps inside the wrapper.
    // 添加timestamps{}, 指定对哪些日志添加日期
    timestamps {
        // Just some echoes to show the timestamps.
        // 输出日志, 展示时间
        stage("First echo") {
            echo "Hey, look, I'm echoing with a timestamp!"
        }

        // A sleep to make sure we actually get a real difference!
        // 休眠30秒, 感受一下不同的地方(连休眠都会有日志输入, 并且也有时间)
        stage("Sleeping") {
            sleep 30 as long
        }

        // And a final echo to show the time when we wrap up.
        // 最后输出一次
        stage("Second echo") {
            echo "Wonder what time it is now?"
        }
    }
}
