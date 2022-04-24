package com.wx2022.perfectCodeStudy.flexible.process;

import com.wx2022.perfectCodeStudy.flexible.flex.FlexSettleService;
import lombok.Data;

/**
 * @Auther: wuxia
 * @Date: 2022/04/24 11:42
 * 保障自选结算任务quartz :是该任务的总入口
 * 会在代码里配置定时任务表达式：例如：flexSettleQuartzTrigger.cronExpression = 0 0/5 * * * ?
 *
 */
@Data
public class FlexSettleQuartz {
    private FlexSettleService flexSettleService;

    public void execute() {
        flexSettleService.execute();
    }
}

