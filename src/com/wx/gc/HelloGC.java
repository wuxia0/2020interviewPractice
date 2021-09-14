package com.wx.gc;

/**
 * @Auther: wuxia
 * @Date: 2020/06/02/15:16
 *java -XX:+PrintCommandLineFlags -version
 * 这个命令是看jvm用的是哪种回收器
 * E:\codes\java_codes\2020interviewPractice\src\com\wx\thread\threads>java -XX:+PrintCommandLineFlags -version
 * -XX:InitialHeapSize=65233472 -XX:MaxHeapSize=1043735552 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndivid
 * ualAllocation -XX:+UseParallelGC
 * java version "1.8.0_73"
 * Java(TM) SE Runtime Environment (build 1.8.0_73-b02)jps
 * Java HotSpot(TM) 64-Bit Server VM (build 25.73-b02, mixed mode)
 *
 *
 * E:\codes\java_codes\2020interviewPractice\src\com\wx\thread\threads>jinfo -flag UseSerialGC 4968
 * -XX:-UseSerialGC
 * -XX:MetaspaceSize
 * -XX:MaxTenuringThreshold=15
 * -XX:+PrintFlagsInitial
 *java -XX:+PrintFlagsFinal -version  --最终修改的值
 *java -XX:+printFlagsInitial         --初始值
 *-XX:+PrintCommand
 * jinfo -flags 5040 -- 5040表示进程号，查出所有的参数
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("***************HelloGC");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
