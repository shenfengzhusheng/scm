package org.xfs.test.study.quartz.para;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.xfs.core.util.DateUtil;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class MyParaJob implements Job {

	private int myCount=1;
	private static int myStaticCount=0;
	
	public static final String JOB_DATA_MAP_KEY="key1";
	public MyParaJob(){}
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String jobName=context.getJobDetail().getKey().getName();
		
		String groupName=context.getJobDetail().getKey().getGroup();
		
		JobDataMap data=context.getJobDetail().getJobDataMap();
		
		int jobDataMapInt=data.getInt(JOB_DATA_MAP_KEY);
		
		
        // 这里注释的内容用于理解DisallowConcurrentExecution这个注解  

		System.out.println(groupName+"任务Key:"+jobName+"正执行,执行时间："+DateUtil.getNowTime());
		
		System.out.println("***private成员变量为:"+myCount+",static 成员变量为："+myStaticCount+",JobDataMap保存的变量为："+jobDataMapInt);
		
		myCount++;
		jobDataMapInt++;
		data.put(JOB_DATA_MAP_KEY, jobDataMapInt);
		myStaticCount--;
	}

}
