package com.example.cloud.task;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ProjectName: cloud
 * @Package: com.example.cloud.shed
 * @ClassName: SynchronousScheduler
 * @Author: sun-d
 * @Description:
 * @Date: 2021/2/4 18:01
 * @Version: 2.0
 */
@Slf4j
@Component
public class SynchronousScheduler {
    /**
     * 每隔一小时执行
     * [秒] [分] [小时] [日] [月] [周] [年]
     *
     * @Scheduled(cron = "0 0 17 * * ?") 每天17点执行
     * 分布式定时调度配置
     * <p>
     * SchedulerLock 注解一共支持五个参数，分别是
     * name 用来标注一个定时服务的名字，被用于写入数据库作为区分不同服务的标识，如果有多个同名定时任务则同一时间点只有一个执行成功
     * lockAtMostFor 成功执行任务的节点所能拥有独占锁的最长时间，单位是毫秒ms
     * lockAtMostForString 成功执行任务的节点所能拥有的独占锁的最长时间的字符串表达，例如“PT14M”表示为14分钟
     * lockAtLeastFor 成功执行任务的节点所能拥有独占所的最短时间，单位是毫秒ms
     * lockAtLeastForString 成功执行任务的节点所能拥有的独占锁的最短时间的字符串表达，例如“PT14M”表示为14分钟
     */
    @Scheduled(cron = "30 * * * * ?")
    @SchedulerLock(name = "synchronousSchedule", lockAtMostFor = "PT30S", lockAtLeastFor = "PT30S")
    public void SynchronousSchedule() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = df.format(now);
        log.info("Start run schedule to synchronous data===>" + format);
    }

}
