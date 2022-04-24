package com.wx2022.perfectCodeStudy.flexible.process;

import com.wx2022.perfectCodeStudy.flexible.flex.FlexibleSettlePersonDTO;
import com.wx2022.perfectCodeStudy.flexible.flex.SmtsFlexExceptionService;
import com.wx2022.perfectCodeStudy.flexible.flex.SmtsFlexSettleService;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: wuxia
 * @Date: 2022/04/20/17:59
 * 结算主Task
 */
@Data
public class ProcessMainTask {
    /**
     * 业务处理service
     */
    private SmtsFlexSettleService smtsFlexSettleService;

    /**
     * 异常处理service
     */
    private SmtsFlexExceptionService smtsFlexExceptionService;

    /**
     * 查询结算人员列表Task计数器
     */
    private CountDownLatch queryTaskCount = new CountDownLatch(1);

    /**
     * 计算保费Task计数器
     */
    private CountDownLatch processBusinessTaskCount = new CountDownLatch(2);

    /**
     * 任务列表
     */
    private List<Runnable> taskList = Collections.synchronizedList(new ArrayList<Runnable>());

    /**
     * 异常列表
     */
    private List<Exception> exceptionList = Collections.synchronizedList(new ArrayList<Exception>());

    /**
     * 查询结算人员出参对列
     */
    private BlockingQueue<List<FlexibleSettlePersonDTO>> querySettlePersonTaskOutQueue = new ArrayBlockingQueue<List<FlexibleSettlePersonDTO>>(1000);

    /**
     * 业务处理 入参对列
     */
    private BlockingQueue<List<FlexibleSettlePersonDTO>> processBusinessTaskQueue = querySettlePersonTaskOutQueue;

    /**
     * 心跳计时器 用于检测整个流程活动情况
     */
    private volatile long heartCount = System.currentTimeMillis();

    /**
     * 同步锁用于心跳计时器
     */
    private ReentrantLock heartLock = new ReentrantLock();

    public ProcessMainTask(
            final SmtsFlexSettleService smtsFlexSettleService,
            final SmtsFlexExceptionService smtsFlexExceptionService) {
        this.smtsFlexSettleService = smtsFlexSettleService;
        this.smtsFlexExceptionService = smtsFlexExceptionService;
    }

    /**
     * 结算主线程
     */
    public void process() {
        System.out.println("ProcessMainTask 开始运行");
        long mainStartTime = System.currentTimeMillis();
        //ExecutorService threaPools = Executors.newFixedThreadPool(8);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 8,
                0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        try {
            //必须先获取总数，否则可能在遍历时，线程已开始修改这个变量
            long queryTaskNum = queryTaskCount.getCount();
            for (int i = 0; i < queryTaskNum; i++) {
                threadPoolExecutor.execute(taskFactory('Q'));
            }
            //查询task仍在运行，但结算列表为空，则等待
            waitTask(querySettlePersonTaskOutQueue, queryTaskCount);

            long  processBusinessTaskNum = processBusinessTaskCount.getCount();
            //计算保费task
            for (int i = 0; i < processBusinessTaskNum; i++) {
                threadPoolExecutor.execute(taskFactory('P'));
            }
            //终止Task 暂停主线程 等待其他任务全部结束后，允许主线程
            Thread thread = new Thread(taskFactory('T'));
            thread.start();
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("processMainTask 报错");
        } finally {
            long mainEndTime = System.currentTimeMillis();
            threadPoolExecutor.shutdown();
            System.out.println("所有子线程停止，主线程结束，耗时：" + (mainEndTime - mainStartTime));
            for (Exception e : exceptionList) {
                System.out.println("throw exception");
            }
        }

    }

    private void waitTask(BlockingQueue blockingQueue,
                          CountDownLatch count) {
        try {
            while (count.getCount() >0 && blockingQueue.isEmpty()) {
                if (this.exceptionList.size() > 0) {
                    break;
                }else {
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("waitTask throw Exception");
        }
    }

    /**
     * 任务工厂，根据参数返回具体的TASK对象
     * Q:查询结算人员task
     * P:计算保费task
     * T:终结task
     * @param taskType
     * @return
     */
    private BaseTask taskFactory(char taskType) {
        BaseTask task = null;
        switch (taskType) {
            // 查询结算人员task
            case 'Q':
                task = new QuerySettlePersonTask(smtsFlexSettleService, smtsFlexExceptionService, querySettlePersonTaskOutQueue, queryTaskCount, taskList, exceptionList, this);
                break;
             // 计算保费task
            case 'P':
                task = new ProcessBusinessTask(smtsFlexSettleService, smtsFlexExceptionService,processBusinessTaskQueue, processBusinessTaskCount, taskList, exceptionList, this);
                break;
             // 终结task
            case 'T':
                task = new TerminateTask(smtsFlexSettleService, processBusinessTaskCount, taskList, exceptionList, this);
                break;
        }
        return task;
    }

    /**
     * 添加心跳检测
     */
    public void setHeartCount(long heartCount) {
        //尝试获取锁，如果失败则说明有其他线程正在更新，故可以不更新这个值
        boolean capyured = heartLock.tryLock();
        try {
            if (capyured) {
                this.heartCount =heartCount;
            }
        } finally {
            if (capyured) {
                heartLock.unlock();
            }
        }

    }
}
