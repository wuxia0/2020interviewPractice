package com.wx2022.perfectCodeStudy.flexible.process;

import com.wx2022.perfectCodeStudy.flexible.flex.FlexibleSettlePersonDTO;
import com.wx2022.perfectCodeStudy.flexible.flex.SmtsFlexSettleService;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: wuxia
 * @Date: 2022/04/20/21:11
 */
public class TerminateTask extends BaseTask {
    private SmtsFlexSettleService smtsFlexSettleService;

    public TerminateTask(final SmtsFlexSettleService smtsFlexSettleService,
                         final CountDownLatch processBusinessTaskCount,
                         final List<Runnable> taskList,
                         final List<Exception> exceptionList,
                         final ProcessMainTask processMainTask) {
        this.preTaskCount = processBusinessTaskCount;
        this.smtsFlexSettleService = smtsFlexSettleService;
        this.exceptionList = exceptionList;
        this.taskList = taskList;
        this.TASK_DESCRIPTION = "终结task";
        this.mainTask = processMainTask;
    }

    @Override
    protected List<FlexibleSettlePersonDTO> processBusiness(Object inObject) {
        return null;
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("主线程等待子线程完成任务");
                preTaskCount.await(30, TimeUnit.SECONDS);
                System.out.println("任务完成，preCountTask：" + preTaskCount.getCount());
                //如果所有线程 半小时内没有任何反应，则停止当前任务
                if (System.currentTimeMillis() - mainTask.getHeartCount() >= (1000*60*10)) {
                    System.out.println("线程空等待超时");
                    break;
                }
                if (this.taskList.size() == 0 || this.exceptionList.size() >0) {
                    break;
                }else {
                    TimeUnit.MINUTES.sleep(1);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("主线程异常");
        }
        System.out.println("任务已经完成，允许主线程继续执行");
    }
}
