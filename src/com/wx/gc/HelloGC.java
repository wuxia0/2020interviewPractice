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
 */
public class HelloGC {
    public static void main(String[] args) {

    }
}
