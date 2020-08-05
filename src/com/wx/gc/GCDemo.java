package com.wx.gc;

import java.util.Random;

/**
 * @Auther: wuxia
 * @Date: 2020/06/02/16:21
 * 练习GCdemo
 * 在VM option 里面配置：
 * 1.串行GC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC
 * 打印的错误信息：
 * -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseSerialGC
 * GC Helllo....
 * [GC (Allocation Failure) [DefNew: 2652K->319K(3072K), 0.0037346 secs] 2652K->880K(9920K), 0.0045351 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [DefNew: 2738K->315K(3072K), 0.0027414 secs] 3298K->1590K(9920K), 0.0027898 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [DefNew: 2894K->0K(3072K), 0.0010485 secs] 4168K->1906K(9920K), 0.0010976 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [DefNew: 1947K->0K(3072K), 0.0012498 secs] 3853K->3169K(9920K), 0.0012977 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [DefNew: 2579K->0K(3072K), 0.0017618 secs] 5749K->5696K(9920K), 0.0018040 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [DefNew: 2579K->2579K(3072K), 0.0000268 secs][Tenured: 5696K->3327K(6848K), 0.0047934 secs] 8275K->3327K(9920K), [Metaspace: 3284K->3284K(1056768K)], 0.0048915 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
 * [GC (Allocation Failure) [DefNew: 2580K->0K(3072K), 0.0012253 secs][Tenured: 5854K->4590K(6848K), 0.0045146 secs] 5907K->4590K(9920K), [Metaspace: 3284K->3284K(1056768K)], 0.0058214 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
 * [Full GC (Allocation Failure) [Tenured: 4590K->4407K(6848K), 0.0050032 secs] 4590K->4407K(9920K), [Metaspace: 3284K->3284K(1056768K)], 0.0050426 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 	at java.util.Arrays.copyOf(Arrays.java:3332)
 * 	at java.lang.AbstractStringBuilder.expandCapacity(AbstractStringBuilder.java:137)
 * 	at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:121)
 * 	at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:647)
 * 	at java.lang.StringBuilder.append(StringBuilder.java:208)
 * 	at com.wx.gc.GCDemo.main(GCDemo.java:16)
 * Heap
 *  def new generation   total 3072K, used 136K [0x00000000ff600000, 0x00000000ff950000, 0x00000000ff950000)
 *   eden space 2752K,   4% used [0x00000000ff600000, 0x00000000ff622260, 0x00000000ff8b0000)
 *   from space 320K,   0% used [0x00000000ff8b0000, 0x00000000ff8b0000, 0x00000000ff900000)
 *   to   space 320K,   0% used [0x00000000ff900000, 0x00000000ff900000, 0x00000000ff950000)
 *  tenured generation   total 6848K, used 4407K [0x00000000ff950000, 0x0000000100000000, 0x0000000100000000)
 *    the space 6848K,  64% used [0x00000000ff950000, 0x00000000ffd9dce8, 0x00000000ffd9de00, 0x0000000100000000)
 *  Metaspace       used 3315K, capacity 4496K, committed 4864K, reserved 1056768K
 *   class space    used 361K, capacity 388K, committed 512K, reserved 1048576K
 *
 * 2.并行GC
 * Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release
 *
 * CMS收集器：
 *D:\development_tools\Java\jdk1.8.0_73\bin\java.exe -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC "-javaagent:D:\development_tools\ideaIU\IntelliJ IDEA 2018.3.5\lib\idea_rt.jar=55453:D:\development_tools\ideaIU\IntelliJ IDEA 2018.3.5\bin" -Dfile.encoding=UTF-8 -classpath D:\development_tools\Java\jdk1.8.0_73\jre\lib\charsets.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\deploy.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\ext\access-bridge-64.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\ext\cldrdata.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\ext\dnsns.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\ext\jaccess.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\ext\jfxrt.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\ext\localedata.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\ext\nashorn.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\ext\sunec.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\ext\sunjce_provider.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\ext\sunmscapi.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\ext\sunpkcs11.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\ext\zipfs.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\javaws.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\jce.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\jfr.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\jfxswt.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\jsse.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\management-agent.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\plugin.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\resources.jar;D:\development_tools\Java\jdk1.8.0_73\jre\lib\rt.jar;E:\codes\java_codes\2020interviewPractice\out\production\2020interviewPractice;G:\google_download\cglib-nodep-3.3.0.jar com.wx.gc.GCDemo
 * -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:MaxNewSize=3497984 -XX:MaxTenuringThreshold=6 -XX:NewSize=3497984 -XX:OldPLABSize=16 -XX:OldSize=6987776 -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC
 * GC Helllo....
 * [GC (Allocation Failure) [ParNew: 2654K->320K(3072K), 0.0037551 secs] 2654K->872K(9920K), 0.0038407 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [ParNew: 2814K->319K(3072K), 0.0032756 secs] 3366K->1749K(9920K), 0.0033338 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [ParNew: 2928K->14K(3072K), 0.0012475 secs] 4358K->2270K(9920K), 0.0012983 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [ParNew: 1985K->7K(3072K), 0.0027305 secs] 4240K->3541K(9920K), 0.0027841 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (CMS Initial Mark) [1 CMS-initial-mark: 3534K(6848K)] 6099K(9920K), 0.0001973 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-mark-start]
 * [GC (Allocation Failure) [ParNew: 2617K->4K(3072K), 0.0017430 secs] 6152K->6096K(9920K), 0.0017977 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-mark: 0.002/0.004 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-preclean-start]
 * [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (CMS Final Remark) [YG occupancy: 1282 K (3072 K)][Rescan (parallel) , 0.0029347 secs][weak refs processing, 0.0000296 secs][class unloading, 0.0004470 secs][scrub symbol table, 0.0010349 secs][scrub string table, 0.0003010 secs][1 CMS-remark: 6092K(6848K)] 7375K(9920K), 0.0049188 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-sweep-start]
 * [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-reset-start]
 * [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [ParNew: 2615K->2615K(3072K), 0.0000331 secs][CMS: 5131K->3200K(6848K), 0.0069384 secs] 7746K->3200K(9920K), [Metaspace: 3280K->3280K(1056768K)], 0.0070439 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
 * [GC (CMS Initial Mark) [1 CMS-initial-mark: 3200K(6848K)] 5757K(9920K), 0.0002315 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [CMS-concurrent-mark-start]
 * [GC (Allocation Failure) [ParNew: 2638K->0K(3072K), 0.0026855 secs][CMS[CMS-concurrent-mark: 0.002/0.005 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 *  (concurrent mode failure): 5759K->4478K(6848K), 0.0072999 secs] 5838K->4478K(9920K), [Metaspace: 3280K->3280K(1056768K)], 0.0100846 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
 * [Full GC (Allocation Failure) [CMS: 4478K->4458K(6848K), 0.0061909 secs] 4478K->4458K(9920K), [Metaspace: 3280K->3280K(1056768K)], 0.0062548 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 	at java.util.Arrays.copyOf(Arrays.java:3332)
 * 	at java.lang.AbstractStringBuilder.expandCapacity(AbstractStringBuilder.java:137)
 * 	at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:121)
 * 	at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:647)
 * 	at java.lang.StringBuilder.append(StringBuilder.java:208)
 * 	at com.wx.gc.GCDemo.main(GCDemo.java:52)
 * Heap
 *  par new generation   total 3072K, used 81K [0x00000000ff600000, 0x00000000ff950000, 0x00000000ff950000)
 *   eden space 2752K,   2% used [0x00000000ff600000, 0x00000000ff614540, 0x00000000ff8b0000)
 *   from space 320K,   0% used [0x00000000ff8b0000, 0x00000000ff8b0000, 0x00000000ff900000)
 *   to   space 320K,   0% used [0x00000000ff900000, 0x00000000ff900000, 0x00000000ff950000)
 *  concurrent mark-sweep generation total 6848K, used 4458K [0x00000000ff950000, 0x0000000100000000, 0x0000000100000000)
 *  Metaspace       used 3311K, capacity 4496K, committed 4864K, reserved 1056768K
 *   class space    used 361K, capacity 388K, committed 512K, reserved 1048576K
 *
 *   G1收集器：
 *   Heap
 *  garbage-first heap   total 10240K, used 4407K [0x00000000ff600000, 0x00000000ff700050, 0x0000000100000000)
 *   region size 1024K, 1 young (1024K), 0 survivors (0K)
 *  Metaspace       used 3312K, capacity 4496K, committed 4864K, reserved 1056768K
 *   class space    used 361K, capacity 388K, committed 512K, reserved 1048576K
 */
public class GCDemo {
    public static void main(String[] args) {
        System.out.println("GC Helllo....");
        String str ="几种垃圾回收器测试";
        try {
            while (true){
                str += str + new Random().nextInt(44444)+new Random().nextInt(888888);
                str.intern();
            }
            // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
