package org.xfs.test.study.quartz.para;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzParaTest {

    public static void main(String[] args) throws Exception {
        SchedulerFactory factory = new StdSchedulerFactory();

        Scheduler scheduler = factory.getScheduler();
        // Date runTime=DateBuilder.nextGivenSecondDate(null, 10);

        JobDetail job = JobBuilder.newJob(MyParaJob.class).withIdentity("job1", "group 1").build();

        job.getJobDataMap().put(MyParaJob.JOB_DATA_MAP_KEY, 10);
        // // 每隔五秒执行，重复4次
        // Trigger trigger=TriggerBuilder.newTrigger().withIdentity("trigger",
        // "group 1").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(5)).startAt(runTime).build();
        // scheduler.scheduleJob(job,trigger);
        //
        // // 再来个有状态的Job，如果为了看单次任务，下面这几句可以先注释掉
        // job=JobBuilder.newJob(MyParaJob.class).withIdentity("job2", "group1").build();
        // job.getJobDataMap().put(MyParaJob.JOB_DATA_MAP_KEY, -10);
        // trigger=TriggerBuilder.newTrigger().withIdentity("trigger2",
        // "group1").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(5)).startAt(runTime).build();
        //
        // scheduler.scheduleJob(job, trigger);
        // scheduler.start();

        CronTrigger cronTrigger =
                TriggerBuilder.newTrigger().withIdentity("trigger", "group")
                        .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
        scheduler.scheduleJob(job, cronTrigger);
        scheduler.start();


        job = JobBuilder.newJob(MyParaJob.class).withIdentity("job2", "group2").build();
        job.getJobDataMap().put(MyParaJob.JOB_DATA_MAP_KEY, -10);
        CronTrigger trigger =
                TriggerBuilder.newTrigger().withIdentity("trigger", "group2")
                        .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?")).build();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        try {
            Thread.sleep(3500L * 1000L);
        } catch (Exception e) {
        }
        scheduler.shutdown(true);
    }

}
