package org.xfs.test.study.quartz.example.e1;

import java.util.Date;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleDemo {

	public static void main(String[] args) throws SchedulerException {
		System.out.println("------- Initializing ----------------------");
		SchedulerFactory sf=new StdSchedulerFactory();
		Scheduler sched=sf.getScheduler();
		System.out.println("------- Initialization Complete -----------");

		Date runTime=DateBuilder.evenSecondDateAfterNow();
		System.out.println("------- Scheduling Job  -------------------");
		
		JobDetail job=JobBuilder.newJob(HelloJob.class)
			      .withIdentity("myJob", "group1")
			      .build();
	    // Trigger the job to run on the next round minute

		Trigger trigger=TriggerBuilder.newTrigger().withIdentity("hellJob", "group one").startAt(runTime).build();
	    // Tell quartz to schedule the job using our trigger
		sched.scheduleJob(job,trigger);
		System.out.println(job.getKey() + " will run at: " + runTime);
		 // Start up the scheduler (nothing can actually run until the
	    // scheduler has been started)
		sched.start();
		System.out.println("------- Started Scheduler -----------------");
		try {
			Thread.sleep(10L * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("------- Shutting Down ---------------------");
	    sched.shutdown(true);
	    System.out.println("------- Shutdown Complete -----------------");

		 
	}

}
