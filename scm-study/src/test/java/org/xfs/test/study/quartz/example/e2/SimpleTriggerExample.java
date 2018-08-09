package org.xfs.test.study.quartz.example.e2;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.xfs.test.base.PrintUtil;
import org.xfs.test.study.quartz.example.e1.HelloJob;

public class SimpleTriggerExample {
	public void run() throws SchedulerException{
		PrintUtil.print("------- Initializing -------------------");
	    // First we must get a reference to a scheduler
		SchedulerFactory sf =new StdSchedulerFactory();
		Scheduler sched=sf.getScheduler();
		PrintUtil.print("------- Initialization Complete --------");
		PrintUtil.print("------- Scheduling Jobs ----------------");
		// jobs can be scheduled before sched.start() has been called

	    // get a "nice round" time a few seconds in the future...	
		Date runtTime=DateBuilder.nextGivenSecondDate(null, 15);
		JobDetail job=JobBuilder.newJob(HelloJob.class).withIdentity("hello job", "group").build();
		SimpleTrigger trigger=(SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runtTime).build();
		Date df=sched.scheduleJob(job, trigger);
		PrintUtil.print(job.getKey() + " will run at: " + df + " and repeat: " + trigger.getRepeatCount() + " times, every "
             + trigger.getRepeatInterval() / 1000 + " seconds");
		
		job=JobBuilder.newJob(HelloJob.class).withIdentity("hello job2", "group").build();
		trigger=(SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(runtTime).build();
		df=sched.scheduleJob(job, trigger);
		PrintUtil.print(job.getKey() + " will run at: " + df + " and repeat: " + trigger.getRepeatCount() + " times, every "
             + trigger.getRepeatInterval() / 1000 + " seconds");
		job=JobBuilder.newJob(HelloJob.class).withIdentity("hello job3", "group").build();
		trigger=(SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger3", "group1").startAt(runtTime).build();
		df=sched.scheduleJob(job, trigger);
		PrintUtil.print(job.getKey() + " will run at: " + df + " and repeat: " + trigger.getRepeatCount() + " times, every "
             + trigger.getRepeatInterval() / 1000 + " seconds");
		
		job=JobBuilder.newJob(HelloJob.class).withIdentity("hello job4", "group").build();
		trigger=(SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger4", "group1").startAt(runtTime).build();
		df=sched.scheduleJob(job, trigger);
		PrintUtil.print(job.getKey() + " will run at: " + df + " and repeat: " + trigger.getRepeatCount() + " times, every "
             + trigger.getRepeatInterval() / 1000 + " seconds");
		job=JobBuilder.newJob(HelloJob.class).withIdentity("hello job5", "group").build();
		trigger=(SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger5", "group1").startAt(runtTime).build();
		df=sched.scheduleJob(job, trigger);
		PrintUtil.print(job.getKey() + " will run at: " + df + " and repeat: " + trigger.getRepeatCount() + " times, every "
             + trigger.getRepeatInterval() / 1000 + " seconds");
		job=JobBuilder.newJob(HelloJob.class).withIdentity("hello job6", "group").build();
		trigger=(SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger6", "group1").startAt(runtTime).build();
		df=sched.scheduleJob(job, trigger);
		PrintUtil.print(job.getKey() + " will run at: " + df + " and repeat: " + trigger.getRepeatCount() + " times, every "
             + trigger.getRepeatInterval() / 1000 + " seconds");
		
		sched.addJob(job, true);
		PrintUtil.print("------- Waiting 30 seconds... --------------");
		try {
			Thread.sleep(30L * 1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 trigger = TriggerBuilder.newTrigger().withIdentity("trigger7", "group1").startAt(runtTime)
			        .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(5).withRepeatCount(20)).build();

		 df = sched.rescheduleJob(trigger.getKey(), trigger);
		 
		 PrintUtil.print("'Manually' triggering job8...");
		 
	}
	
	public static void main(String[] args)throws Exception {
		new SimpleTriggerExample().run();
	}

}
