package com.wx2022.perfectCodeStudy.flexible.process;

import com.wx2022.perfectCodeStudy.flexible.flex.FlexibleSettlePersonDTO;
import com.wx2022.perfectCodeStudy.flexible.flex.SmtsFlexExceptionService;
import com.wx2022.perfectCodeStudy.flexible.flex.SmtsFlexSettleService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: wuxia
 * @Date: 2022/04/20/20:43
 */
public class QuerySettlePersonTask extends BaseTask<Object, List<FlexibleSettlePersonDTO>> {

    /**
     *业务service
     */
    protected SmtsFlexSettleService smtsFlexSettleService;

    public QuerySettlePersonTask(final SmtsFlexSettleService smtsFlexSettleService,
                                 final SmtsFlexExceptionService smtsFlexExceptionService,
                                 final BlockingQueue<List<FlexibleSettlePersonDTO>> querySettlePersonTaskOutQueue,
                                 final CountDownLatch queryTaskCount,
                                 final List<Runnable> taskList,
                                 final List<Exception> exceptionList,
                                 final ProcessMainTask processMainTask) {
        this.outQueue = querySettlePersonTaskOutQueue;
        this.currentTaskCount = queryTaskCount;
        this.smtsFlexSettleService = smtsFlexSettleService;
        this.smtsFlexExceptionService = smtsFlexExceptionService;
        this.exceptionList = exceptionList;
        this.taskList = taskList;
        this.TASK_DESCRIPTION = "查询结算人员列表任务";
        this.mainTask = processMainTask;
    }

    @Override
    protected List<FlexibleSettlePersonDTO> processBusiness(Object inObject) {
        return null;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        try {
            this.taskList.add(this);
            System.out.println(Thread.currentThread() + "添加任务" + TASK_DESCRIPTION);
            long startQueryTime = System.currentTimeMillis();
            List<FlexibleSettlePersonDTO> settlePersonDTOS = smtsFlexSettleService.queryFlexSettlePersonList();
            long endQueryTime = System.currentTimeMillis();
            System.out.println("queryPerson ，总数:" +(endQueryTime - startQueryTime));
            List<FlexibleSettlePersonDTO> list = new ArrayList<>();
            int i = 0;
            for (FlexibleSettlePersonDTO dto : settlePersonDTOS) {
                if (i < 100) {
                    list.add(dto);
                    i++;
                }else {
                    outQueue.put(list);
                    list = new ArrayList<FlexibleSettlePersonDTO>();
                    list.add(dto);
                    i = 0;
                }
            }
            // 如果不满100
            if (i != 0 && null != list) {
                outQueue.put(list);
                mainTask.setHeartCount(System.currentTimeMillis());
            }
        } catch (Exception e) {
            recordException("QuerySettlePersonTask", "", "run",e);
        } finally {
            try {
                //任务结束，先移除Task列表 同时任务计数器相应减一
                taskList.remove(this);
                currentTaskCount.countDown();
            } catch (Exception e) {
                exceptionList.add(e);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("QuerySettlePersonTask耗时:" + (endTime - startTime));
    }
}
