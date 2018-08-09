package org.xfs.test.study.quartz.example.e1;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println(Thread.currentThread().getName());
		System.out.println("Hello world!"+new SimpleDateFormat("yyyy-mm-dd HH:MM:ss.SSS").format(new Date()));
	}

}
