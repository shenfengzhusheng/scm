package org.xfs.scm.platform.config.quartz.mapper;

import org.xfs.scm.platform.config.quartz.entity.JobAndTrigger;

import java.util.List;

public interface JobAndTriggerMapper {
    public List<JobAndTrigger> getJobAndTriggerDetails();
}