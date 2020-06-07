package com.zhenma.scheduled;

import com.zhenma.mapper.CronMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

/**
 * @Description
 * @ClassName MyTask
 * @Author User
 * @date 2020.06.07 15:23
 */
@Component
@EnableScheduling
public class MyTask implements SchedulingConfigurer {
    // 创建日志对象
    final Logger logger = Logger.getLogger(MyTask.class);

    @Autowired
    protected CronMapper cronMapper;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(() -> process(),
                triggerContext -> {
                    String cron = cronMapper.getCron(1);
                    if (cron.isEmpty()) {
                        logger.error("cron is null");
                    }
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                });
    }

    private void process() {
        logger.info("基于接口的定时任务");
    }
}
