package org.xfs.core.platform.job.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.xfs.core.platform.job.model.Job;
import org.xfs.core.platform.job.model.Jobs;
import org.xfs.core.platform.job.service.JobServiceI;


// @Service
public class JobServiceImpl implements JobServiceI {
   // private static SchedulerFactory scheulerFactory = new StdSchedulerFactory();

    @Override
    public boolean addJob(Job job, Class<?> clazz) {
        if (job != null) {
//
//            try {
//                Jobs.jobs.put(job.getJobId().toString(), job);
//               // Scheduler sched = scheulerFactory.getScheduler();
//                // 任务名，任务组，任务执行类
//              //  JobDetail jobDetail = JobBuilder.newJob(JobTask.class).withIdentity(job.getJobName(), job.getJobGroup()).build();
//                // 可以传递参数
//                // jobDetail.getJobDataMap().put("param", "railsboy");
//
//                // 触发器
//                // CronTrigger trigger=TriggerBuilder
//                // .newTrigger()
//                // .withIdentity(job.getJobName(),job.getJobGroup())
//                // .startNow()
//                // .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                // .withIntervalInSeconds(10) //时间间隔
//                // .withRepeatCount(5) //重复次数(将执行6次)
//                // )
//                // .build();
//                // jobDetail.getJobDataMap().put(Map<String,Object>map);
//            } catch (SchedulerException e) {
//                e.printStackTrace();
//            }
            return true;
        }
        return false;
    }

    @Override
    public boolean modfiyJob(Job job) {
        if (job != null) {
            // Job oldJob=Jobs.jobs.get(job.toString());
            Jobs.jobs.put(job.getJobId().toString(), job);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeJob(Long jobId) {
        boolean removeFlag = false;
        for (String key : Jobs.jobs.keySet()) {
            Job job = Jobs.jobs.get(key);
            if (job.getJobId() == jobId) {
                Jobs.jobs.remove(key);
                removeFlag = true;
                break;

            }
        }
        return removeFlag;
    }

    @Override
    public List<Job> list(Job job, Integer page, Integer rows) {
        List<Job> list = new ArrayList<Job>();
        if (page == null || page == 0) {
            page = 1;
        }
        if (rows == null || rows == 0) {
            rows = 5;
        }
        Set<String> keys = Jobs.jobs.keySet();
        List<Job> jobs = new ArrayList<Job>();
        for (String key : keys) {
            Job jb = Jobs.jobs.get(key);
            if (jb.getJobGroup().equals(job.getJobGroup())) {
                jobs.add(jb);
            }
        }
        int start = (page - 1) * rows;
        if (start > jobs.size()) {
            return null;
        }
        for (int i = start; i < jobs.size(); i++) {
            if (rows > 0) {
                list.add(jobs.get(i));
            }
            rows--;
        }


        return list;
    }

    @Override
    public Job getJob(Job job) {
        List<Job> list = this.list(job, 1, 1);
        if (!list.isEmpty())
            return list.get(0);
        return null;
    }

    @Override
    public Map<String, Object> startJob(Long jobId) {
        return null;
    }

    @Override
    public Map<String, Object> stopJob(Long jobId) {
        return null;
    }

    @Override
    public Map<String, Object> stopGroupJob(String group) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Object> stopAllJob() {
        // TODO Auto-generated method stub
        return null;
    }

}
