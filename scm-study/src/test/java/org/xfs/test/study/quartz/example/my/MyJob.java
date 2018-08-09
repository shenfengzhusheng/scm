package org.xfs.test.study.quartz.example.my;

import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String jobName=context.getJobDetail().getKey().getName();
		String groupName=context.getJobDetail().getKey().getGroup();
		System.out.println("任务Key:" + jobName + " 正在执行，执行时间: " + Calendar.getInstance().getTime()+":groupName:"+groupName);  
	}

}
