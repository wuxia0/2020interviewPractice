package com.wx2022.perfectCodeStudy.flexible.process;

import com.wx2022.perfectCodeStudy.flexible.flex.SmtsFlexExceptionService;
import lombok.Data;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: wuxia
 * @Date: 2022/04/20/19:26
 */
@Data
public abstract class BaseTask<I,O> implements Runnable {
    /**
     * 异常处理service
     */
    protected SmtsFlexExceptionService smtsFlexExceptionService;

    /**
     * 主线程
     */
    protected com.wx2022.perfectCodeStudy.flexible.process.ProcessMainTask mainTask;

    /**
     * 执行中的任务列表
     */
    protected List<Runnable> taskList;

    /**
     * 异常列表
     */
    protected List<Exception> exceptionList;

    /**
     * 入参队列
     */
    protected BlockingQueue<I> inQueue;

    /**
     * 出参队列
     */
    protected BlockingQueue<O> outQueue;

    /**
     * 当前任务计数器
     */
    protected CountDownLatch currentTaskCount;

    /**
     * 上个任务计数器
     */
    protected  CountDownLatch preTaskCount;

    /**
     * 任务描述
     */
    protected String TASK_DESCRIPTION;

    /**
     * 类名称
     */
    protected String CLASS_NAME = this.getClass().getSimpleName();

    @Override
    public void run() {
        try {
            taskList.add(this);
            System.out.println(CLASS_NAME + "-添加 任务");
            while (!Thread.currentThread().isInterrupted()) {
                I inObject = inQueue.poll();
                if (null == inObject) {
                    //前一个任务没有结束 先休眠再取数据
                    if (preTaskCount.getCount() != 0) {
                        TimeUnit.MILLISECONDS.sleep(100);
                    }else {
                        /**
                         * 如果前一个任务结束，同时队列为空，则证明任务已处理完毕，结束当前任务
                         * 计数器为0后，需要再次判断queue是否为空
                         *
                         */
                        if (null == inQueue.peek()) {
                            break;
                        }
                    }
                }else {
                    //实现业务逻辑
                    O outObject = processBusiness(inObject);
                    if (null != outObject) {
                        outQueue.put(outObject);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            recordException(CLASS_NAME,"" ,"run", e);
        } finally {
            try {
                //任务结束 先移除task列表，同时任务计数器相应减一
                if (taskList.remove(this)) {
                    currentTaskCount.countDown();
                }else {
                    System.out.println("任务移除失败");
                }
                System.out.println("移除任务时成功");
            } catch (Exception e) {
                exceptionList.add(e);
                System.out.println("移除任务时异常");
            }
        }
    }

    /**
     * 处理具体的业务逻辑
     * @param inObject
     * @return
     */
    protected abstract O processBusiness(I inObject);

    /**
     * 记录异常信息
     * @param class_name
     * @param s
     * @param run
     * @param e
     */
    public void recordException(String class_name, String s, String run, Exception e) { }

}
