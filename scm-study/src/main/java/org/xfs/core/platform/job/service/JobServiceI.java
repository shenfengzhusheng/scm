package org.xfs.core.platform.job.service;

import java.util.List;
import java.util.Map;

import org.xfs.core.platform.job.model.Job;

/**
 * 
 * @author Jeken.Liu
 *
 */
public interface JobServiceI {

    /**
     * 添加任务
     * 
     * @param job
     * @return
     */
    boolean addJob(Job job, Class<?> clazz);

    /**
     * 编辑任务
     * 
     * @param job
     * @return
     */
    boolean modfiyJob(Job job);

    /**
     * 删除Job
     * 
     * @param jobId
     * @return
     */
    boolean removeJob(Long jobId);

    /**
     * 查询Job列表
     * 
     * @param job
     * @param page
     * @param rows
     * @return
     */
    List<Job> list(Job job, Integer page, Integer rows);

    /**
     * 获取指定job详情
     * 
     * @param job
     * @return
     */
    Job getJob(Job job);

    Map<String, Object> startJob(Long jobId);

    Map<String, Object> stopJob(Long jobId);

    Map<String, Object> stopGroupJob(String group);


    Map<String, Object> stopAllJob();



}
