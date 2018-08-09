package org.xfs.test.study.quartz.example.my;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzMultiJobTest {
	
	public static void main(String []args)throws Exception{
		quartz();
	}
	/* 
	-------------------------------------- 
	    0 0 12 * * ?            每天12点触发 
	    0 15 10 ? * *           每天10点15分触发 
	    0 15 10 * * ?           每天10点15分触发 
	    0 15 10 * * ? *         每天10点15分触发 
	    0 15 10 * * ? 2005      2005年每天10点15分触发 
	    0 * 14 * * ?            每天下午的 2点到2点59分每分触发 
	    0 0/5 14 * * ?          每天下午的 2点到2点59分(整点开始，每隔5分触发) 
	    0 0/5 14,18 * * ?       每天下午的 2点到2点59分(整点开始，每隔5分触发) 每天下午的 18点到18点59分(整点开始，每隔5分触发) 
	    0 0-5 14 * * ?          每天下午的 2点到2点05分每分触发 
	    0 10,44 14 ? 3 WED      3月分每周三下午的 2点10分和2点44分触发 
	    0 15 10 ? * MON-FRI     从周一到周五每天上午的10点15分触发 
	    0 15 10 15 * ?          每月15号上午10点15分触发 
	    0 15 10 L * ?           每月最后一天的10点15分触发 
	    0 15 10 ? * 6L          每月最后一周的星期五的10点15分触发 
	    0 15 10 ? * 6L 2002-2005    从2002年到2005年每月最后一周的星期五的10点15分触发 
	    0 15 10 ? * 6#3         每月的第三周的星期五开始触发 
	    0 0 12 1/5 * ?          每月的第一个中午开始每隔5天触发一次 
	    0 11 11 11 11 ?         每年的11月11号 11点11分触发(光棍节) 
	-------------------------------------- 
	 */  
	public static void quartz()throws Exception{
		SchedulerFactory factory=new StdSchedulerFactory();
		Scheduler scheduler=factory.getScheduler();
		
		JobDetail job=JobBuilder.newJob(MyJob.class).withIdentity("job1","group 1").build();
		// @NOTICE  
        // 与SimpleTrigger对比：类不同了，现在的是Trigger的子类CronTrigger；withSchedule中的参数变为CronScheduleBuilder了  
        // CronScheduleBuilder可以通过类似"0/13 * * * * ?"这种表达式来创建定时任务  
        // 当前这个表达式的定义是每个秒是13的倍数，或者是0的时候，都触发任务  
		CronTrigger trigger=TriggerBuilder.newTrigger().withIdentity("trigger", "group 1").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
		
		scheduler.scheduleJob(job, trigger);
		scheduler.start();
		try {  
            // 等待60秒查看效果  
            Thread.sleep(160L * 1000L);  
        } catch (Exception e) {  
        }  
        scheduler.shutdown(true);  
	}
	
	public static void sheduler()throws Exception{
		SchedulerFactory factory=new StdSchedulerFactory();
		
		Scheduler scheduler=factory.getScheduler();
		// @NOTICE 任务的开始时间，nextGivenSecondDate方法表示：当前时间之后，每当秒数是13的倍数都是触发时间，当然只触发一次  
        // 比如：00:00:12秒开始主线程，则13秒就会触发任务，如果00:00:14秒开始主线程，则在26秒触发任务  
		
		Date runTime=DateBuilder.nextGivenSecondDate(null, 13);
		
		JobDetail job=JobBuilder.newJob(MyJob.class).withIdentity("myjob", "group 1").build();
		
		
		Trigger trigger=TriggerBuilder.newTrigger().withIdentity("trigger", "grouby 1").startAt(runTime).build();
		
		scheduler.scheduleJob(job, trigger);
		
		
		// @NOTICE 将同一个Job实现作为另外一个任务注册到scheduler，注意名字要区分  
		
		job=JobBuilder.newJob(MyJob.class).withIdentity("job2", "group 1").build();
		
		trigger=TriggerBuilder.newTrigger().withIdentity("job2", "group 1").build();
		
		scheduler.scheduleJob(job, trigger);
		// @NOTICE 重复执行,job3表示第一次执行完之后，每隔3秒钟执行一次，重复5次，withRepeatCount参数不包括第一次执行那次，即job3总共执行6次  
		job=JobBuilder.newJob(MyJob.class).withIdentity("job3", "group 1").build();
		trigger=TriggerBuilder.newTrigger().withIdentity("trigger3", "group 1").withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(5).withIntervalInSeconds(3)).startAt(runTime).build();
		
		scheduler.scheduleJob(job, trigger);
		scheduler.start();
		try {  
            // 等待20秒  
            Thread.sleep(20L * 1000L);  
        } catch (Exception e) {  
  
        }  
        scheduler.shutdown(true);  
	}
}	
