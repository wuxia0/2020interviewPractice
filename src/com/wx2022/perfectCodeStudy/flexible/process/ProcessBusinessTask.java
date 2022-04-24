package com.wx2022.perfectCodeStudy.flexible.process;

import com.wx2022.perfectCodeStudy.flexible.flex.FlexibleSettlePersonDTO;
import com.wx2022.perfectCodeStudy.flexible.flex.SmtsFlexExceptionService;
import com.wx2022.perfectCodeStudy.flexible.flex.SmtsFlexSettleService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: wuxia
 * @Date: 2022/04/20/21:05
 */
@Slf4j
public class ProcessBusinessTask extends BaseTask<List<FlexibleSettlePersonDTO>, Object> {
    private SmtsFlexSettleService smtsFlexSettleService;

    public ProcessBusinessTask(final SmtsFlexSettleService smtsFlexSettleService,
                               final SmtsFlexExceptionService smtsFlexExceptionService,
                               final BlockingQueue<List<FlexibleSettlePersonDTO>> processBusinessTaskQueue,
                               final CountDownLatch processBusinessTaskCount,
                               final List<Runnable> taskList,
                               final List<Exception> exceptionList,
                               final ProcessMainTask processMainTask) {
        this.inQueue = processBusinessTaskQueue;
        this.currentTaskCount = processBusinessTaskCount;
        this.smtsFlexSettleService = smtsFlexSettleService;
        this.smtsFlexExceptionService = smtsFlexExceptionService;
        this.exceptionList = exceptionList;
        this.taskList = taskList;
        this.TASK_DESCRIPTION = "计算保费任务";
        this.mainTask = processMainTask;
    }

    /**
     * 业务逻辑处理
     * @param inObject
     * @return
     */
    @Override
    protected List<FlexibleSettlePersonDTO> processBusiness(List<FlexibleSettlePersonDTO> inObject) {
        return null;
    }

    @Override
    public void run() {
        try {
            taskList.add(this);
            System.out.println(Thread.currentThread() + "添加任务：" + TASK_DESCRIPTION);
            while (!Thread.currentThread().isInterrupted()){
                List<FlexibleSettlePersonDTO> inObject = inQueue.poll();
                if (null == inObject) {
                    if (preTaskCount.getCount() != 0) {
                        TimeUnit.MILLISECONDS.sleep(100);
                    }else {
                        if (null == inQueue.peek()){
                            break;
                        }
                    }
                }else {
                    //实现业务逻辑
                    System.out.println("processBusiness" + TASK_DESCRIPTION);
                    processBusiness(inObject);
                }
            }
        } catch (Exception e) {
            recordException("processBusinessTask", "", "run", e);
        } finally {
            try {
                //任务结束，先移除Task列表 同时任务计数器相应减一
                taskList.remove(this);
                currentTaskCount.countDown();
                System.out.println("移除任务：" + TASK_DESCRIPTION);
            } catch (Exception e) {
                exceptionList.add(e);
                System.out.println("移除任务时异常：" + TASK_DESCRIPTION);
            }
        }
    }

}
