package com.wx2022.perfectCodeStudy.flexible.flex;

import com.wx2022.perfectCodeStudy.flexible.process.ProcessMainTask;
import lombok.Data;

/**
 * @Auther: wuxia
 * @Date: 2022/04/24 11:43
 * 结算任务
 */
@Data
public class FlexSettleService {
    private SmtsFlexSettleService smtsFlexSettleService;
    private SmtsFlexExceptionService smtsFlexExceptionService;

    public void execute() {
        new ProcessMainTask(smtsFlexSettleService, smtsFlexExceptionService).process();
    }
}

